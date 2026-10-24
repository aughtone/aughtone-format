// Generates the month and weekday names :datetime writes inside a date, where they differ from
// the names it shows on their own, from the Unicode CLDR JSON data (the cldr-dates-full package).
//
//   node tools/cldr/format-names.mjs <path to cldr-dates-full/main>
//
// Run it from the repository root. It rewrites one file and prints what it wrote. The CLDR
// version it used is recorded in it. Do not edit the generated file by hand.
//
// The bundled month and weekday tables hold the name of a month or day on its own, which many
// languages capitalise ("Marzo" as a heading). Inside a date the same languages write it in
// lower case ("5 de marzo de 2024"), and some use another grammatical form ("5 марта"). For each
// bundled language, a set of names (full or abbreviated months or weekdays) is taken whole from
// CLDR's format context when the bundled set capitalises a name that CLDR writes in lower case.
// Full month names are always taken for the languages in GRAMMATICAL_FORMS, whose in-date form
// is a different word.
import { readFileSync, writeFileSync, existsSync } from "node:fs";
import { join } from "node:path";

const main = process.argv[2];
if (!main || !existsSync(main)) {
  console.error("usage: node tools/cldr/format-names.mjs <path to cldr-dates-full/main>");
  process.exit(1);
}
const version = JSON.parse(readFileSync(join(main, "..", "package.json"), "utf8")).version;
const GRAMMATICAL_FORMS = ["cs", "fi", "pl", "ru", "uk"];
// Library keys that are not CLDR locale identifiers.
const CLDR_ID = { no: "nb" };
const MONTH_KEYS = ["january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"];
const DAY_KEYS = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"];

const formats = "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/";
const monthSource = readFileSync(formats + "LocaleMonthNamesSource.kt", "utf8");
const daySource = readFileSync(formats + "LocaleDayOfWeekNamesSource.kt", "utf8");

const bundledMonths = {};
for (const m of monthSource.matchAll(/^    "([A-Za-z-]+)" to lazy \{([\s\S]*?)^    \}/gm)) {
  const width = (name) => {
    const block = m[2].match(new RegExp(`${name} = MonthNames\\(([\\s\\S]*?)\\)`));
    // A table built from a constant (English) or from numbers has no names to compare.
    const names = block && MONTH_KEYS.map((k) => block[1].match(new RegExp(`${k} = "([^"]*)"`))?.[1]);
    return names && names.every((n) => n != null) ? names : null;
  };
  bundledMonths[m[1]] = { full: width("full"), abbreviated: width("abbreviated") };
}
const bundledDays = {};
for (const m of daySource.matchAll(/^    "([A-Za-z-]+)" to lazy \{([\s\S]*?)\) \},/gm)) {
  const width = (name) => {
    const block = m[2].match(new RegExp(`${name} = DayOfWeekNames\\(listOf\\(([^)]*)\\)`));
    return block && [...block[1].matchAll(/"([^"]*)"/g)].map((x) => x[1]);
  };
  bundledDays[m[1]] = { full: width("full"), abbreviated: width("abbreviated") };
}

const startsUpper = (text, id) => text[0] !== text[0].toLocaleLowerCase(id);
const startsLower = (text, id) => text[0] !== text[0].toLocaleUpperCase(id);
// True when the bundled set capitalises a name that CLDR's format context writes in lower case.
const capitalisesWhereCldrDoesNot = (ours, cldr, id) => ours != null && ours.some((name, i) => startsUpper(name, id) && startsLower(cldr[i], id));

const kotlinString = (text) => JSON.stringify(text).replace(/\$/g, "\\$");
const entries = [];
const languages = [...new Set([...Object.keys(bundledMonths), ...Object.keys(bundledDays)])].sort();
for (const key of languages) {
  const id = CLDR_ID[key] ?? key;
  const file = join(main, id, "ca-gregorian.json");
  if (!existsSync(file)) continue;
  const gregorian = JSON.parse(readFileSync(file, "utf8")).main[id].dates.calendars.gregorian;
  // CLDR fills a missing locale with placeholders such as "M01"; those are not names.
  const months = (width) => Object.values(gregorian.months.format[width]);
  const days = (width) => DAY_KEYS.map((k) => gregorian.days.format[width][k]);
  if (months("wide")[0] === "M01") continue;
  const sets = {
    monthsFull: GRAMMATICAL_FORMS.includes(key) || capitalisesWhereCldrDoesNot(bundledMonths[key]?.full, months("wide"), id) ? months("wide") : null,
    monthsAbbreviated: capitalisesWhereCldrDoesNot(bundledMonths[key]?.abbreviated, months("abbreviated"), id) ? months("abbreviated") : null,
    daysFull: capitalisesWhereCldrDoesNot(bundledDays[key]?.full, days("wide"), id) ? days("wide") : null,
    daysAbbreviated: capitalisesWhereCldrDoesNot(bundledDays[key]?.abbreviated, days("abbreviated"), id) ? days("abbreviated") : null,
  };
  if (Object.values(sets).every((s) => s == null)) continue;
  const field = (name, list) => list && `${name} = listOf(${list.map(kotlinString).join(", ")})`;
  const fields = Object.entries(sets).map(([name, list]) => field(name, list)).filter(Boolean);
  entries.push(`    "${key}" to InDateNames(\n        ${fields.join(",\n        ")},\n    ),`);
}

const out = `package io.github.aughtone.datetime.format.resources.formats

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${version} by tools/cldr/format-names.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Month and weekday names in the form a language writes them inside a date, where that differs
 * from the name on its own: lower case in Spanish, French or Italian ("5 de marzo de 2024", where
 * a heading reads "Marzo"), another grammatical form in Russian ("5 марта", not "Март").
 *
 * Each list replaces the bundled set of the same width inside a date; \`null\` keeps the bundled
 * set. Months run January to December and weekdays Monday to Sunday. The names are CLDR's
 * format-context names.
 */
internal class InDateNames(
    val monthsFull: List<String>? = null,
    val monthsAbbreviated: List<String>? = null,
    val daysFull: List<String>? = null,
    val daysAbbreviated: List<String>? = null,
)

/** In-date names keyed by language code, for the languages whose in-date names differ. */
internal val localeInDateNames: Map<String, InDateNames> = mapOf(
${entries.join("\n")}
)
`;
const path = formats + "LocaleInDateNames.kt";
writeFileSync(path, out);
console.log(`CLDR ${version}: ${entries.length} languages, wrote ${path} (${out.length} characters)`);
