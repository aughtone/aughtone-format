// Generates the relative-time and duration patterns that :readable bundles, and the fixtures its
// tests check them against, from the Unicode CLDR data in this Node runtime's ICU.
//
//   node tools/cldr/relative-time.mjs
//
// Run it from the repository root. It rewrites four files and prints what it wrote. The CLDR
// version it used is recorded in each. Do not edit the generated files by hand. The few places
// where the output departs from CLDR are listed in ADJUSTMENTS below, each with its reason.
//
// Three kinds of data are written:
//
// - Long relative-time phrases for the languages in LANGUAGES (below).
// - Short relative-time phrases ("vor 5 Min.") for every bundled language but English, whose
//   own short form ("5m") is kept, and Inuktitut, which CLDR has no data for.
// - Duration phrases: short ones ("5 Min.") for the same languages, long ones for the languages
//   in DURATION_LANGUAGES, and the word each language uses for "now", which a duration under
//   half a second reads as.
//
// A language is listed below when CLDR's "N units ago" and "in N units" phrases cannot be built
// from one past template, one future template and a single unit word per plural form — because
// the unit takes a different grammatical case in each direction (Czech: "před 5 minutami" but
// "za 5 minut"), or because the phrase changes shape (Arabic drops the count for one and two,
// Swahili writes the unit before the count). Every other bundled language keeps its compact
// template-and-words form in RelativeTime.resources.kt; where its wording differs from CLDR, see
// docs/knowledge/decisions/ADR-0004-keep-everyday-wordings-that-differ-from-cldr.md.
import { writeFileSync } from "node:fs";

// The South Asian languages are here because their phrases are taken whole from CLDR rather
// than written as templates: CLDR is the only source for them in this library.
const SOUTH_ASIAN = ["bn", "gu", "kn", "ml", "mr", "pa", "ta", "te", "ur"];
const LANGUAGES = ["ar", "cs", "fi", "hu", "hy", "is", "ka", "kk", "lt", "sk", "sl", "sq", "sr", "sw", ...SOUTH_ASIAN];
// Every bundled language with CLDR data, by CLDR identifier. The library's "no", "zh-Hans" and
// "zh-TW" read these as "nb", "zh" and "zh-Hant".
const SHORT_LANGUAGES = [
  "af", "nl", "de", "da", "nb", "nn", "sv", "is", "et", "fi", "vi", "el", "hu", "ro", "tr", "ru", "uk", "be", "pl",
  "cs", "sk", "sl", "hr", "sr", "bg", "mk", "fr", "it", "es", "pt", "ca", "gl", "hi", "ar", "he", "fa", "th", "ko",
  "ja", "zh", "zh-Hant", "zh-HK", "eu", "hy", "ka", "az", "uz", "kk", "lt", "lv", "sq", "id", "ms", "sw", ...SOUTH_ASIAN,
];
// Languages whose long duration words come whole from CLDR; the others are written by hand in
// Duration.resources.kt. Arabic is here because CLDR names one and two without a digit
// ("دقيقتان", two minutes), which a template with a count cannot express.
const DURATION_LANGUAGES = [...SOUTH_ASIAN, "ar"];
// Languages whose duration "now" is written; English is among them, and the region variants
// the library keys separately.
const NOW_LANGUAGES = ["en", "en-ZA", ...SHORT_LANGUAGES];
const UNITS = ["second", "minute", "hour", "day", "week", "month", "year"];
const CATEGORY_ORDER = ["zero", "one", "two", "few", "many", "other"];
const KOTLIN_CATEGORY = { zero: "Zero", one: "One", two: "Two", few: "Few", many: "Many", other: "Other" };
// Counts the formatter can actually produce for each unit: it rolls 60 seconds into a minute,
// 24 hours into a day, a fourth week into days, and a twelfth month into a year.
const SAMPLE_COUNTS = {
  second: [1, 2, 3, 4, 5, 10, 11, 12, 20, 21, 22, 25, 59],
  minute: [1, 2, 3, 4, 5, 10, 11, 12, 20, 21, 22, 25, 59],
  hour: [1, 2, 3, 4, 5, 10, 11, 12, 20, 21, 22, 23],
  day: [1, 2, 3, 4, 5, 6, 25, 29],
  week: [1, 2, 3],
  month: [1, 2, 3, 4, 5, 10, 11],
  year: [1, 2, 3, 4, 5, 10, 11, 12, 20, 21, 22, 25, 100, 101, 102, 111],
};

const formatter = (language, style = "long") =>
  new Intl.RelativeTimeFormat(`${language}-u-nu-latn`, { numeric: "always", style });
const unitFormatter = (language, unit, unitDisplay) =>
  new Intl.NumberFormat(`${language}-u-nu-latn`, { style: "unit", unit, unitDisplay });
// Bidirectional marks are layout hints for mixed-direction text; the library emits plain text.
const clean = (text) => text.replace(/[‎‏؜]/g, "");
// Places where the CLDR data is adjusted, each with its reason. Keep this list short.
const ADJUSTMENTS = {
  // CLDR capitalises "Sekunde" in the Swahili past and no other unit; a phrase that may sit
  // mid-sentence is written in lower case like the rest.
  sw: (text) => text.replace(/^Sekunde /, "sekunde "),
};
const adjust = (language, text) => (ADJUSTMENTS[language] ? ADJUSTMENTS[language](text) : text);
const phrase = (language, unit, count, sign, style = "long") =>
  adjust(language, clean(formatter(language, style).format(sign * count, unit)));
const durationPhrase = (language, unit, count, unitDisplay) =>
  adjust(language, clean(unitFormatter(language, unit, unitDisplay).format(count)));
// The word a language uses for "now": CLDR's phrase for zero seconds, in its words-first form.
const nowWord = (language) =>
  clean(new Intl.RelativeTimeFormat(`${language}-u-nu-latn`, { numeric: "auto" }).format(0, "second"));

const toPattern = (text, count) => {
  const at = text.indexOf(String(count));
  return at < 0 ? text : text.slice(0, at) + "{0}" + text.slice(at + String(count).length);
};

/** The plural categories [language] uses for whole numbers, each with its first count. */
function categoriesOf(language) {
  const rules = new Intl.PluralRules(language);
  const representative = {};
  for (let n = 0; n <= 200; n++) {
    const category = rules.select(n);
    if (!(category in representative)) representative[category] = n;
  }
  return { rules, representative, categories: CATEGORY_ORDER.filter((c) => c in representative) };
}

/**
 * One pattern per unit and plural category from [text] (unit, count → CLDR's text), refused
 * unless every count from 0 to 200 is reproduced by the pattern of its category.
 */
function tableFor(language, label, text) {
  const { rules, representative, categories } = categoriesOf(language);
  const table = UNITS.map((unit) => categories.map((c) => toPattern(text(unit, representative[c]), representative[c])));
  UNITS.forEach((unit, unitIndex) => {
    for (let n = 0; n <= 200; n++) {
      const built = table[unitIndex][categories.indexOf(rules.select(n))].replace("{0}", String(n));
      if (built !== text(unit, n)) throw new Error(`${language} ${label} ${n} ${unit}: built "${built}", CLDR has "${text(unit, n)}"`);
    }
  });
  return { categories, table };
}

function build(language, style = "long") {
  const past = tableFor(language, `${style} past`, (unit, n) => phrase(language, unit, n, -1, style));
  const future = tableFor(language, `${style} future`, (unit, n) => phrase(language, unit, n, 1, style));
  return { language, categories: past.categories, past: past.table, future: future.table };
}

function buildDuration(language, unitDisplay) {
  const { categories, table } = tableFor(language, `duration ${unitDisplay}`, (unit, n) => durationPhrase(language, unit, n, unitDisplay));
  return { language, categories, units: table };
}

const kotlinString = (text) => JSON.stringify(text).replace(/\$/g, "\\$");
const sets = LANGUAGES.map((language) => build(language));
const shortSets = SHORT_LANGUAGES.map((language) => build(language, "short"));
const durationSets = DURATION_LANGUAGES.map((language) => buildDuration(language, "long"));
const shortDurationSets = SHORT_LANGUAGES.map((language) => buildDuration(language, "short"));
const nowWords = NOW_LANGUAGES.map((language) => [language, nowWord(language)]);
const cldr = process.versions.cldr;

const relativeEntry = (s) => `    "${s.language}" to RelativeTimePatterns(
        categories = listOf(${s.categories.map((c) => `PluralCategory.${KOTLIN_CATEGORY[c]}`).join(", ")}),
        past = listOf(
${rows(s.past)}
        ),
        future = listOf(
${rows(s.future)}
        ),
    ),`;
const durationEntry = (s) => `    "${s.language}" to durationEntry(
        listOf(${s.categories.map((c) => `PluralCategory.${KOTLIN_CATEGORY[c]}`).join(", ")}),
${s.units.map((byCategory, i) => `        /* ${UNITS[i].padEnd(6)} */ listOf(${byCategory.map(kotlinString).join(", ")}),`).join("\n")}
    ),`;
const rows = (table) =>
  table.map((byCategory, i) => `            /* ${UNITS[i].padEnd(6)} */ listOf(${byCategory.map(kotlinString).join(", ")}),`).join("\n");

const data = `package io.github.aughtone.readable.relative

import io.github.aughtone.readable.PluralCategory

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${cldr} by tools/cldr/relative-time.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Whole relative-time phrases for one language, as Unicode CLDR publishes them: one pattern for
 * each unit, direction and plural form, with \`{0}\` where the count goes.
 *
 * @property categories The plural forms the language distinguishes for whole numbers, in the
 *   order the pattern lists use.
 * @property past Patterns for a time in the past, by unit (second, minute, hour, day, week,
 *   month, year) and then by plural form in [categories] order.
 * @property future The same for a time in the future.
 */
internal class RelativeTimePatterns(
    val categories: List<PluralCategory>,
    val past: List<List<String>>,
    val future: List<List<String>>,
)

/**
 * The languages whose relative-time phrases cannot be built from one template and one unit word
 * per plural form — the unit takes a different grammatical case after "ago" than after "in", or
 * the phrase changes shape altogether — keyed by language code. Every other bundled language is
 * described compactly in \`RelativeTime.resources.kt\`.
 */
internal val cldrRelativeTimePatterns: Map<String, RelativeTimePatterns> = mapOf(
${sets.map(relativeEntry).join("\n")}
)

/**
 * Short relative-time phrases ("vor 5 Min.", "il y a 5 min"), as Unicode CLDR publishes them,
 * for every bundled language but English, whose own short form is written by hand, and
 * Inuktitut, which CLDR has no data for. Keyed by CLDR identifier: the library reads its "no",
 * "zh-Hans" and "zh-TW" as "nb", "zh" and "zh-Hant".
 */
internal val cldrShortRelativeTimePatterns: Map<String, RelativeTimePatterns> = mapOf(
${shortSets.map(relativeEntry).join("\n")}
)
`;

const fixtureLines = (language, style = "long") =>
  UNITS.flatMap((unit) =>
    SAMPLE_COUNTS[unit].map((n) => `${unit}|${n}|${phrase(language, unit, n, -1, style)}|${phrase(language, unit, n, 1, style)}`));
const durationFixtureLines = (language, unitDisplay) =>
  UNITS.flatMap((unit) => SAMPLE_COUNTS[unit].map((n) => `${unit}|${n}|${durationPhrase(language, unit, n, unitDisplay)}`));
const fixtureMap = (name, doc, languages, lines) => `/** ${doc} */
internal val ${name}: Map<String, String> = mapOf(
${languages.map((language) => `    "${language}" to """
${lines(language).join("\n")}
""",`).join("\n")}
)
`;

const fixture = `package io.github.aughtone.readable.relative

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${cldr} by tools/cldr/relative-time.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

${fixtureMap("relativeTimeCldrFixture", "What Unicode CLDR says for a sample of counts in each unit, per language, one case per line as `unit|count|past phrase|future phrase`.", LANGUAGES, (l) => fixtureLines(l))}
${fixtureMap("shortRelativeTimeCldrFixture", "As [relativeTimeCldrFixture], for CLDR's short phrases.", SHORT_LANGUAGES, (l) => fixtureLines(l, "short"))}`;

const durationData = `package io.github.aughtone.readable.duration

import io.github.aughtone.readable.PluralCategory

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${cldr} by tools/cldr/relative-time.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Duration phrases for one language, as Unicode CLDR publishes them: one pattern for each unit
 * and plural form, with \`{0}\` where the count goes.
 *
 * @property categories The plural forms the language distinguishes for whole numbers, in the
 *   order the pattern lists use.
 * @property units Patterns by unit (second, minute, hour, day, week, month, year) and then by
 *   plural form in [categories] order.
 */
internal class DurationPatterns(
    val categories: List<PluralCategory>,
    val units: List<List<String>>,
)

private fun durationEntry(categories: List<PluralCategory>, vararg units: List<String>) = DurationPatterns(categories, units.toList())

/** Long duration phrases for the languages whose words come whole from CLDR, keyed by language code. */
internal val cldrDurationPatterns: Map<String, DurationPatterns> = mapOf(
${durationSets.map(durationEntry).join("\n")}
)

/**
 * Short duration phrases ("5 Min.", "5 min"), for every bundled language but English and
 * Inuktitut, keyed by CLDR identifier as the short relative-time phrases are.
 */
internal val cldrShortDurationPatterns: Map<String, DurationPatterns> = mapOf(
${shortDurationSets.map(durationEntry).join("\n")}
)

/** The word each language uses for "now", which a duration under half a second reads as. */
internal val cldrDurationNow: Map<String, String> = mapOf(
${nowWords.map(([language, word]) => `    "${language}" to ${kotlinString(word)},`).join("\n")}
)
`;

const durationFixture = `package io.github.aughtone.readable.duration

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${cldr} by tools/cldr/relative-time.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

${fixtureMap("durationCldrFixture", "What Unicode CLDR writes for a sample of durations, per language, one case per line as `unit|count|phrase`.", DURATION_LANGUAGES, (l) => durationFixtureLines(l, "long"))}
${fixtureMap("shortDurationCldrFixture", "As [durationCldrFixture], for CLDR's short phrases.", SHORT_LANGUAGES, (l) => durationFixtureLines(l, "short"))}`;

const base = "readable/src";
const dataPath = `${base}/commonMain/kotlin/io/github/aughtone/readable/relative/RelativeTimePatterns.resources.kt`;
const fixturePath = `${base}/commonTest/kotlin/io/github/aughtone/readable/relative/RelativeTimeCldrFixture.kt`;
const durationDataPath = `${base}/commonMain/kotlin/io/github/aughtone/readable/duration/DurationPatterns.resources.kt`;
const durationFixturePath = `${base}/commonTest/kotlin/io/github/aughtone/readable/duration/DurationCldrFixture.kt`;
const outputs = [[dataPath, data], [fixturePath, fixture], [durationDataPath, durationData], [durationFixturePath, durationFixture]];
for (const [path, text] of outputs) writeFileSync(path, text);
console.log(`CLDR ${cldr}: ${sets.length} long and ${shortSets.length} short relative-time languages, ` +
  `${durationSets.length} long and ${shortDurationSets.length} short duration languages`);
for (const [path, text] of outputs) console.log(`wrote ${path} (${text.length} characters)`);
