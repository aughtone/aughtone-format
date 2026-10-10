// Generates the relative-time patterns that :readable bundles, and the fixture its test checks
// them against, from the Unicode CLDR data in this Node runtime's ICU.
//
//   node tools/cldr/relative-time.mjs
//
// Run it from the repository root. It rewrites two files and prints what it wrote. The CLDR
// version it used is recorded in both. Do not edit the generated files by hand. The few places
// where the output departs from CLDR are listed in ADJUSTMENTS below, each with its reason.
//
// A language is listed below when CLDR's "N units ago" and "in N units" phrases cannot be built
// from one past template, one future template and a single unit word per plural form — because
// the unit takes a different grammatical case in each direction (Czech: "před 5 minutami" but
// "za 5 minut"), or because the phrase changes shape (Arabic drops the count for one and two,
// Swahili writes the unit before the count). Every other bundled language keeps its compact
// template-and-words form in RelativeTime.resources.kt; where its wording differs from CLDR, see
// docs/gap/0002-relative-time-wording-differences-from-cldr.md.
import { writeFileSync } from "node:fs";

const LANGUAGES = ["ar", "cs", "fi", "hu", "hy", "is", "ka", "kk", "lt", "sk", "sl", "sq", "sr", "sw"];
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

const formatter = (language) =>
  new Intl.RelativeTimeFormat(`${language}-u-nu-latn`, { numeric: "always", style: "long" });
// Bidirectional marks are layout hints for mixed-direction text; the library emits plain text.
const clean = (text) => text.replace(/[‎‏؜]/g, "");
// Places where the CLDR data is adjusted, each with its reason. Keep this list short.
const ADJUSTMENTS = {
  // CLDR capitalises "Sekunde" in the Swahili past and no other unit; a phrase that may sit
  // mid-sentence is written in lower case like the rest.
  sw: (text) => text.replace(/^Sekunde /, "sekunde "),
};
const phrase = (language, unit, count, sign) => {
  const text = clean(formatter(language).format(sign * count, unit));
  return ADJUSTMENTS[language] ? ADJUSTMENTS[language](text) : text;
};

function patternFor(language, unit, count, sign) {
  const text = phrase(language, unit, count, sign);
  const at = text.indexOf(String(count));
  return at < 0 ? text : text.slice(0, at) + "{0}" + text.slice(at + String(count).length);
}

function build(language) {
  const rules = new Intl.PluralRules(language);
  const representative = {};
  for (let n = 0; n <= 200; n++) {
    const category = rules.select(n);
    if (!(category in representative)) representative[category] = n;
  }
  const categories = CATEGORY_ORDER.filter((c) => c in representative);
  const table = (sign) =>
    UNITS.map((unit) => categories.map((c) => patternFor(language, unit, representative[c], sign)));
  const set = { language, categories, past: table(-1), future: table(1) };

  // Every whole count must be reproduced by the pattern of its plural category.
  for (const [direction, sign] of [["past", -1], ["future", 1]]) {
    UNITS.forEach((unit, unitIndex) => {
      for (let n = 0; n <= 200; n++) {
        const pattern = set[direction][unitIndex][categories.indexOf(rules.select(n))];
        const built = pattern.replace("{0}", String(n));
        if (built !== phrase(language, unit, n, sign)) {
          throw new Error(`${language} ${direction} ${n} ${unit}: built "${built}", CLDR has "${phrase(language, unit, n, sign)}"`);
        }
      }
    });
  }
  return set;
}

const kotlinString = (text) => JSON.stringify(text).replace(/\$/g, "\\$");
const sets = LANGUAGES.map(build);
const cldr = process.versions.cldr;

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
${sets.map((s) => `    "${s.language}" to RelativeTimePatterns(
        categories = listOf(${s.categories.map((c) => `PluralCategory.${KOTLIN_CATEGORY[c]}`).join(", ")}),
        past = listOf(
${rows(s.past)}
        ),
        future = listOf(
${rows(s.future)}
        ),
    ),`).join("\n")}
)
`;

const fixtureLines = (language) =>
  UNITS.flatMap((unit) =>
    SAMPLE_COUNTS[unit].map((n) => `${unit}|${n}|${phrase(language, unit, n, -1)}|${phrase(language, unit, n, 1)}`));

const fixture = `package io.github.aughtone.readable.relative

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${cldr} by tools/cldr/relative-time.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * What Unicode CLDR says for a sample of counts in each unit, per language, one case per line as
 * \`unit|count|past phrase|future phrase\`.
 */
internal val relativeTimeCldrFixture: Map<String, String> = mapOf(
${LANGUAGES.map((language) => `    "${language}" to """
${fixtureLines(language).join("\n")}
""",`).join("\n")}
)
`;

const base = "readable/src";
const dataPath = `${base}/commonMain/kotlin/io/github/aughtone/readable/relative/RelativeTimePatterns.resources.kt`;
const fixturePath = `${base}/commonTest/kotlin/io/github/aughtone/readable/relative/RelativeTimeCldrFixture.kt`;
writeFileSync(dataPath, data);
writeFileSync(fixturePath, fixture);
const patterns = sets.reduce((sum, s) => sum + 2 * UNITS.length * s.categories.length, 0);
console.log(`CLDR ${cldr}: ${sets.length} languages, ${patterns} patterns`);
console.log(`wrote ${dataPath} (${data.length} characters)`);
console.log(`wrote ${fixturePath} (${fixture.length} characters)`);
