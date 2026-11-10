// Generates the field patterns :datetime bundles for formatting a chosen set of fields, from the
// Unicode CLDR JSON data (the cldr-dates-full package).
//
//   node tools/cldr/field-formats.mjs <path to cldr-dates-full/main>
//
// Run it from the repository root. It rewrites one file and prints what it wrote. The CLDR
// version it used is recorded in it. Do not edit the generated file by hand.
//
// The locales are the ones :datetime bundles date patterns for, read from its pattern table, so
// the two stay in step. For each it keeps CLDR's availableFormats — a pattern for each skeleton,
// the set of fields it shows — for the skeletons made of the fields the library offers; the
// locale's standard date and time patterns, which ICU's matching also chooses from; the patterns
// that join a date and a time, in their "at" form where CLDR has one, as ICU uses them; and the
// appendItems that add a field no pattern covers, with the field names those use.
import { readFileSync, writeFileSync, existsSync } from "node:fs";
import { join } from "node:path";

const main = process.argv[2];
if (!main || !existsSync(main)) {
  console.error("usage: node tools/cldr/field-formats.mjs <path to cldr-dates-full/main>");
  process.exit(1);
}
const version = JSON.parse(readFileSync(join(main, "..", "package.json"), "utf8")).version;

// Library pattern keys that are not CLDR locale identifiers.
const CLDR_ID = { CA: "en-CA", US: "en", no: "nb" };

const patternTable = readFileSync(
  "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/LocaleDatePatterns.kt",
  "utf8",
);
const keys = [...patternTable.matchAll(/^    "([A-Za-z-]+)" to DatePatterns/gm)].map((m) => m[1]);

// The fields the library offers: era, year, month, day, weekday, hour, minute, second and zone,
// with the zone in any of its forms, since ICU adapts a zone pattern to the form asked for. A
// skeleton with any other letter (quarters, weeks, flexible day periods), and CLDR's alternative
// and plural variants, are left out.
const isWanted = (skeleton) => /^[GyMLdEchHKkmsvzZ]+$/.test(skeleton);
const LENGTHS = ["full", "long", "medium", "short"];
const APPEND_ITEMS = ["Era", "Year", "Month", "Day", "Day-Of-Week", "Hour", "Minute", "Second", "Timezone"];
const FIELD_NAMES = ["era", "year", "month", "day", "weekday", "hour", "minute", "second", "zone"];

// The packed form: entries separated by U+001E, a key and its value by U+001F. Neither appears in
// CLDR's patterns, and one string per locale costs a fraction of a map literal in compiled code.
const RECORD = "\u001E";
const UNIT = "\u001F";
const pack = (pairs) => pairs.map(([k, v]) => {
  if (k.includes(RECORD) || k.includes(UNIT) || v.includes(RECORD) || v.includes(UNIT)) throw new Error(`separator in ${k}`);
  return `${k}${UNIT}${v}`;
}).join(RECORD);
const kotlinString = (text) => JSON.stringify(text).replace(/\$/g, "\\$").replace(/\u001E/g, "\\u001E").replace(/\u001F/g, "\\u001F");

// The CLDR parents of the bundled regional locales; every other locale's parent is the root, which CLDR JSON calls "und".
const PARENT = { "en-CA": "en-001", "en-GB": "en-001", "en-AU": "en-001", "en-NZ": "en-001", "en-IE": "en-001", "en-IN": "en-001", "en-ZA": "en-001", "en-001": "en", "fr-CA": "fr" };
const parentsOf = (id) => (id === "und" ? ["und"] : [id, ...parentsOf(PARENT[id] ?? "und")]);
const availableFormatsOf = (id) =>
  JSON.parse(readFileSync(join(main, id, "ca-gregorian.json"), "utf8")).main[id].dates.calendars.gregorian.dateTimeFormats.availableFormats;

const entries = [];
let skeletonCount = 0;
for (const key of keys) {
  const id = CLDR_ID[key] ?? key;
  const file = join(main, id, "ca-gregorian.json");
  if (!existsSync(file)) throw new Error(`no CLDR data for ${key} (${id})`);
  const gregorian = JSON.parse(readFileSync(file, "utf8")).main[id].dates.calendars.gregorian;
  const formats = gregorian.dateTimeFormats;
  const atTime = gregorian["dateTimeFormats-atTime"]?.standard ?? {};
  const fields = JSON.parse(readFileSync(join(main, id, "dateFields.json"), "utf8")).main[id].dates.fields;

  // ICU tries a locale's own patterns before those it inherits, and the JSON is resolved, so
  // each level's patterns are those that differ from its parent's, sorted, the parent's after.
  const chain = parentsOf(id).map((locale) => availableFormatsOf(locale));
  const seen = new Set();
  const patterns = [];
  chain.forEach((level, index) => {
    const parent = chain[index + 1];
    const own = Object.keys(level).filter((k) => isWanted(k) && !seen.has(k) && (!parent || parent[k] !== level[k])).sort();
    for (const k of own) { seen.add(k); patterns.push([k, chain[0][k]]); }
  });
  skeletonCount += patterns.length;
  const text = (value) => (typeof value === "string" ? value : value._value);
  const joins = LENGTHS.map((length) => text(atTime[length] ?? formats[length]));
  const standard = [...LENGTHS.map((l) => text(gregorian.dateFormats[l])), ...LENGTHS.map((l) => text(gregorian.timeFormats[l]))];
  const appendItems = APPEND_ITEMS.map((item) => [item, formats.appendItems[item]]);
  const names = FIELD_NAMES.map((field) => [field, fields[field].displayName]);
  // Whether CLDR writes the stand-alone month and weekday names in lower case ("июнь"), which a
  // pattern with \`L\` or \`c\` asks for inside a phrase; the library's own stand-alone names are
  // capitalised for headings.
  const lower = (name) => name.charAt(0) !== name.charAt(0).toUpperCase();
  const standAloneLowerCase = [lower(gregorian.months["stand-alone"].wide["6"]), lower(gregorian.days["stand-alone"].wide.sat)];

  entries.push(`    "${key}" to FieldFormats(
        patterns = ${kotlinString(pack(patterns))},
        standard = listOf(${standard.map(kotlinString).join(", ")}),
        dateTime = listOf(${joins.map(kotlinString).join(", ")}),
        appendItems = ${kotlinString(pack(appendItems))},
        fieldNames = ${kotlinString(pack(names))},
        standAloneMonthsLowerCase = ${standAloneLowerCase[0]},
        standAloneDaysLowerCase = ${standAloneLowerCase[1]},
    ),`);
}

const out = `package io.github.aughtone.datetime.format.resources.formats

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${version} by tools/cldr/field-formats.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * A locale's patterns for a chosen set of fields, as Unicode CLDR publishes them.
 *
 * The maps are packed into strings — entries separated by U+001E, a key and its value by U+001F —
 * and read on first use, because a map literal of this size costs many times more in compiled code.
 *
 * @property patterns CLDR's availableFormats: a pattern for each skeleton, the set of fields it
 *   shows ("MMMd" to "MMM d"), in the order ICU tries them — the locale's own, then each parent's.
 * @property standard The locale's full, long, medium and short date patterns, then its time
 *   patterns, which the matching chooses from as well, under the skeleton their letters spell.
 * @property dateTime The patterns that join a date (\`{1}\`) and a time (\`{0}\`), for the full,
 *   long, medium and short date lengths, in their "at" form where CLDR has one ("{1} 'at' {0}").
 * @property appendItems How a field no pattern covers is added (\`{0}\` the pattern, \`{1}\` the
 *   field, \`{2}\` its name), keyed by CLDR's field names ("Era", "Day-Of-Week", …).
 * @property fieldNames The display name of each field ("era", "year", … "zone"), which an
 *   appendItem may name.
 * @property standAloneMonthsLowerCase Whether CLDR writes stand-alone month names in lower case
 *   ("июнь 2024 г."), as a pattern with \`L\` writes them inside a phrase.
 * @property standAloneDaysLowerCase The same for stand-alone weekday names (\`c\`).
 */
internal class FieldFormats(
    private val patterns: String,
    val standard: List<String>,
    val dateTime: List<String>,
    private val appendItems: String,
    private val fieldNames: String,
    val standAloneMonthsLowerCase: Boolean,
    val standAloneDaysLowerCase: Boolean,
) {
    val patternsBySkeleton: Map<String, String> by lazy { unpack(patterns) }
    val appendItemsByField: Map<String, String> by lazy { unpack(appendItems) }
    val fieldNamesByField: Map<String, String> by lazy { unpack(fieldNames) }

    private fun unpack(packed: String): Map<String, String> =
        packed.split('\\u001E').associate { entry -> entry.substringBefore('\\u001F') to entry.substringAfter('\\u001F') }
}

/** Field patterns keyed like the date patterns in [localeDatePatterns]. */
internal val localeFieldFormats: Map<String, FieldFormats> = mapOf(
${entries.join("\n")}
)
`;
const path = "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/LocaleFieldFormats.kt";
writeFileSync(path, out);
console.log(`CLDR ${version}: ${keys.length} locales, ${skeletonCount} skeletons, wrote ${path} (${out.length} characters)`);
