// Generates the date and time interval patterns that :datetime bundles, from the Unicode CLDR
// JSON data (the cldr-dates-full package).
//
//   node tools/cldr/interval-formats.mjs <path to cldr-dates-full/main>
//
// Run it from the repository root. It rewrites one file and prints what it wrote. The CLDR
// version it used is recorded in it. Do not edit the generated file by hand.
//
// The locales are the ones :datetime bundles date patterns for, read from its pattern table, so
// the two stay in step. For each, it keeps the interval patterns of the skeletons the library's
// date and time styles resolve to — numeric and text dates with and without a weekday, and hours
// and minutes on each clock — plus the locale's interval fallback, which joins two complete
// values when no pattern applies.
import { readFileSync, writeFileSync, existsSync } from "node:fs";
import { join } from "node:path";

const main = process.argv[2];
if (!main || !existsSync(main)) {
  console.error("usage: node tools/cldr/interval-formats.mjs <path to cldr-dates-full/main>");
  process.exit(1);
}
const version = JSON.parse(readFileSync(join(main, "..", "package.json"), "utf8")).version;

// Times: hours and minutes on each clock. Dates: every skeleton with a year, a month and a day,
// with or without a weekday, so a style can find its own skeleton or the nearest one.
const isWanted = (skeleton) => skeleton === "hm" || skeleton === "Hm" || /^yM+(E+)?d$/.test(skeleton);
const STYLES = ["short", "medium", "long", "full"];
// Library pattern keys that are not CLDR locale identifiers.
const CLDR_ID = { CA: "en-CA", US: "en", no: "nb" };

const patternTable = readFileSync(
  "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/LocaleDatePatterns.kt",
  "utf8",
);
const keys = [...patternTable.matchAll(/^    "([A-Za-z-]+)" to DatePatterns/gm)].map((m) => m[1]);

const kotlinString = (text) => JSON.stringify(text).replace(/\$/g, "\\$");
const entries = [];
for (const key of keys) {
  const id = CLDR_ID[key] ?? key;
  const file = join(main, id, "ca-gregorian.json");
  if (!existsSync(file)) throw new Error(`no CLDR data for ${key} (${id})`);
  const gregorian = JSON.parse(readFileSync(file, "utf8")).main[id].dates.calendars.gregorian;
  const intervals = gregorian.dateTimeFormats.intervalFormats;
  const styles = STYLES.map((style) =>
    `            "${style}" to (${kotlinString(gregorian.dateSkeletons[style])} to ${kotlinString(gregorian.dateFormats[style])}),`);
  const skeletons = Object.keys(intervals).filter(isWanted).map((s) => {
    // Keys such as "d-alt-variant" are alternatives CLDR offers beside the default; only the
    // default, one field letter, is kept.
    const byField = Object.entries(intervals[s])
      .filter(([field]) => field.length === 1)
      .map(([field, pattern]) => `'${field}' to ${kotlinString(pattern)}`);
    return `            "${s}" to mapOf(${byField.join(", ")}),`;
  });
  entries.push(`    "${key}" to IntervalFormats(
        fallback = ${kotlinString(intervals.intervalFormatFallback)},
        dateStyles = mapOf(
${styles.join("\n")}
        ),
        patterns = mapOf(
${skeletons.join("\n")}
        ),
    ),`);
}

const out = `package io.github.aughtone.datetime.format.resources.formats

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${version} by tools/cldr/interval-formats.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * A locale's interval formats, as Unicode CLDR publishes them.
 *
 * @property fallback Joins two complete values when no pattern applies: \`{0}\` is the start and
 *   \`{1}\` the end ("{0} – {1}" in English).
 * @property dateStyles For each date style ("short", "medium", "long", "full"), CLDR's skeleton
 *   for it and CLDR's own date pattern, so the library can tell whether its pattern for the style
 *   is CLDR's and, if it is, use CLDR's skeleton rather than guess one from the letters.
 * @property patterns Interval patterns by skeleton ("yMMMd", "hm", …) and then by the greatest
 *   field that differs between the two ends ('y', 'M', 'd', 'a', 'h', 'H', 'm'). A pattern
 *   repeats a field to mark where the end begins: "MMM d – d, y".
 */
internal class IntervalFormats(
    val fallback: String,
    val dateStyles: Map<String, Pair<String, String>>,
    val patterns: Map<String, Map<Char, String>>,
)

/** Interval formats keyed like the date patterns in [localeDatePatterns]. */
internal val localeIntervalFormats: Map<String, IntervalFormats> = mapOf(
${entries.join("\n")}
)
`;
const path = "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/LocaleIntervalFormats.kt";
writeFileSync(path, out);
console.log(`CLDR ${version}: ${keys.length} locales, wrote ${path} (${out.length} characters)`);
