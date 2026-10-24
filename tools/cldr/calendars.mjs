// Generates the calendar data :calendars bundles, and the fixture its tests check the calendars
// against.
//
//   node tools/cldr/calendars.mjs <path to cldr-core>
//
// Run it from the repository root. It rewrites three files and prints what it wrote. The CLDR
// and ICU versions it used are recorded in each. Do not edit the generated files by hand.
//
// - The Japanese era list comes from CLDR's supplemental calendar data (the cldr-core package):
//   each era's start date, and its code where CLDR gives one.
// - The Umm al-Qura month lengths come from this Node runtime's ICU, read day by day over the
//   range ICU has a table for (AH 1300 to 1600).
// - The fixture is what ICU says for a spread of ISO dates from 1900 to 2100 in every calendar
//   the module computes, so the arithmetic is checked against an independent implementation.
import { readFileSync, writeFileSync, existsSync, mkdirSync } from "node:fs";
import { join, dirname } from "node:path";

const core = process.argv[2];
const calendarDataPath = core && join(core, "supplemental", "calendarData.json");
if (!calendarDataPath || !existsSync(calendarDataPath)) {
  console.error("usage: node tools/cldr/calendars.mjs <path to cldr-core>");
  process.exit(1);
}
const cldr = JSON.parse(readFileSync(join(core, "package.json"), "utf8")).version;
const icu = process.versions.icu;
const calendarData = JSON.parse(readFileSync(calendarDataPath, "utf8")).supplemental.calendarData;
const kotlinString = (text) => JSON.stringify(text).replace(/\$/g, "\\$");
const banner = (source) => `// ─────────────────────────────────────────────────────────────────────────────
// Generated from ${source} by tools/cldr/calendars.mjs — do not edit by hand; change the
// generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────`;
const write = (path, text) => {
  mkdirSync(dirname(path), { recursive: true });
  writeFileSync(path, text);
  console.log(`wrote ${path} (${text.length} characters)`);
};
const main = "calendars/src/commonMain/kotlin/io/github/aughtone/calendars";
const test = "calendars/src/commonTest/kotlin/io/github/aughtone/calendars";

// ── Japanese eras ────────────────────────────────────────────────────────────
const eras = Object.entries(calendarData.japanese.eras)
  .map(([index, era]) => {
    const [y, m, d] = era._start.split("-").map(Number);
    return { index: Number(index), code: era._code ?? index, y, m, d };
  })
  .sort((a, b) => a.index - b.index);
write(`${main}/data/JapaneseEras.kt`, `package io.github.aughtone.calendars.data

${banner(`Unicode CLDR ${cldr}`)}

/**
 * The Japanese eras, oldest first: each era's code and the proleptic Gregorian date it begins.
 * The code is CLDR's where CLDR gives one ("meiji", "taisho", "showa", "heisei", "reiwa"), and
 * otherwise the era's CLDR number as a string ("0" for Taika).
 */
internal val japaneseEras: List<JapaneseEra> = listOf(
${eras.map((e) => `    JapaneseEra(${kotlinString(e.code)}, ${e.y}, ${e.m}, ${e.d}),`).join("\n")}
)

/** One Japanese era: its [code], and the year, month and day it begins. */
internal class JapaneseEra(val code: String, val startYear: Int, val startMonth: Int, val startDay: Int)
`);

// ── Reading ICU ──────────────────────────────────────────────────────────────
const formatters = new Map();
const icuDate = (calendar, ms) => {
  if (!formatters.has(calendar)) {
    formatters.set(calendar, new Intl.DateTimeFormat(`en-u-ca-${calendar}-nu-latn`, {
      timeZone: "UTC", era: "long", year: "numeric", month: calendar === "hebrew" ? "long" : "numeric", day: "numeric",
    }));
  }
  return Object.fromEntries(formatters.get(calendar).formatToParts(new Date(ms)).filter((p) => p.type !== "literal").map((p) => [p.type, p.value]));
};
const DAY = 86400000;
const iso = (ms) => new Date(ms).toISOString().slice(0, 10);

// ── Umm al-Qura ──────────────────────────────────────────────────────────────
const UMALQURA_FIRST = 1300, UMALQURA_LAST = 1600;
const lengths = new Map();
let start = null;
for (let ms = Date.UTC(1882, 9, 1, 12); ms < Date.UTC(2175, 2, 1); ms += DAY) {
  const d = icuDate("islamic-umalqura", ms);
  const year = Number(d.year), month = Number(d.month), day = Number(d.day);
  if (year < UMALQURA_FIRST || year > UMALQURA_LAST) continue;
  if (year === UMALQURA_FIRST && month === 1 && day === 1) start = iso(ms);
  const key = `${year}-${month}`;
  lengths.set(key, Math.max(lengths.get(key) ?? 0, day));
}
const masks = [];
for (let year = UMALQURA_FIRST; year <= UMALQURA_LAST; year++) {
  let mask = 0;
  for (let month = 1; month <= 12; month++) {
    const length = lengths.get(`${year}-${month}`);
    if (length !== 29 && length !== 30) throw new Error(`Umm al-Qura ${year}/${month} has ${length} days`);
    if (length === 30) mask |= 1 << (month - 1);
  }
  masks.push(mask);
}
const [startY, startM, startD] = start.split("-").map(Number);
write(`${main}/data/UmmAlQuraTable.kt`, `package io.github.aughtone.calendars.data

${banner(`the Umm al-Qura calendar in ICU ${icu}`)}

/** The first year of the Umm al-Qura table, AH ${UMALQURA_FIRST}. */
internal const val UMM_AL_QURA_FIRST_YEAR: Int = ${UMALQURA_FIRST}

/** The last year of the Umm al-Qura table, AH ${UMALQURA_LAST}. */
internal const val UMM_AL_QURA_LAST_YEAR: Int = ${UMALQURA_LAST}

/** The ISO date of 1 Muharram AH ${UMALQURA_FIRST}, where the table begins: ${start}. */
internal val ummAlQuraStart: IntArray = intArrayOf(${startY}, ${startM}, ${startD})

/**
 * The month lengths of each year in the table, from AH ${UMALQURA_FIRST}: bit \`m - 1\` is set when
 * month \`m\` has 30 days, and clear when it has 29.
 */
internal val ummAlQuraMonthLengths: IntArray = intArrayOf(
${masks.map((m, i) => (i % 12 === 0 ? "    " : "") + `0x${m.toString(16).padStart(3, "0")},` + (i % 12 === 11 || i === masks.length - 1 ? "\n" : " ")).join("")})
`);

// ── Korean (Dangi) month starts ─────────────────────────────────────────────
// The Chinese calendar's table comes from the Hong Kong Observatory's published tables (see
// tools/calendars/chinese-table.mjs), which ICU disagrees with in a few months. No published
// Korean table is available to generate from, so Dangi is computed, and ICU's month starts are
// kept as a test oracle. ICU writes a leap month as "2bis" and the year as relatedYear.
const lunisolarFormatters = new Map();
const lunisolarDate = (calendar, ms) => {
  if (!lunisolarFormatters.has(calendar)) {
    lunisolarFormatters.set(calendar, new Intl.DateTimeFormat(`en-u-ca-${calendar}-nu-latn`, { timeZone: "UTC", year: "numeric", month: "numeric", day: "numeric" }));
  }
  const parts = Object.fromEntries(lunisolarFormatters.get(calendar).formatToParts(new Date(ms)).filter((p) => p.type !== "literal").map((p) => [p.type, p.value]));
  return { year: Number(parts.relatedYear), month: parseInt(parts.month, 10), leap: parts.month.endsWith("bis"), day: Number(parts.day) };
};
const dangiStarts = [];
for (let ms = Date.UTC(1900, 0, 1, 12); ms < Date.UTC(2101, 0, 1); ms += DAY) {
  const d = lunisolarDate("dangi", ms);
  if (d.day === 1) dangiStarts.push(`${iso(ms)}|${d.year}|${d.month}|${d.leap ? 1 : 0}`);
}
write(`${test}/DangiIcuFixture.kt`, `package io.github.aughtone.calendars

${banner(`ICU ${icu}`)}

/**
 * Every month start of the Korean (Dangi) calendar from 1900 to 2100 as ICU gives it, one per line
 * as \`iso date|year|month|leap (1 or 0)\`, the year being the ISO year in which the year begins.
 */
internal val dangiIcuFixture: String = """
${dangiStarts.join("\n")}
"""
`);

// ── Fixture ──────────────────────────────────────────────────────────────────
const CALENDARS = ["buddhist", "japanese", "roc", "hebrew", "islamic-civil", "islamic-tbla", "islamic-umalqura", "persian", "coptic", "ethiopic", "ethioaa", "indian"];
const HEBREW_MONTHS = { Tishri: [1, 0], Heshvan: [2, 0], Kislev: [3, 0], Tevet: [4, 0], Shevat: [5, 0], "Adar I": [5, 1], Adar: [6, 0], "Adar II": [6, 0], Nisan: [7, 0], Iyar: [8, 0], Sivan: [9, 0], Tamuz: [10, 0], Av: [11, 0], Elul: [12, 0] };
const JAPANESE_ERA_CODES = { Meiji: "meiji", "Taishō": "taisho", "Shōwa": "showa", Heisei: "heisei", Reiwa: "reiwa" };

const sample = new Set();
for (let ms = Date.UTC(1900, 0, 1, 12); ms <= Date.UTC(2100, 11, 31, 12); ms += 61 * DAY) sample.add(ms);
sample.add(Date.UTC(1900, 0, 1, 12));
sample.add(Date.UTC(2100, 11, 31, 12));

const row = (calendar, ms) => {
  const d = icuDate(calendar, ms);
  let year = Number(d.year), month = Number(d.month), leap = 0, era = "";
  const day = Number(d.day);
  if (calendar === "hebrew") {
    const mapped = HEBREW_MONTHS[d.month];
    if (!mapped) throw new Error(`unknown Hebrew month ${d.month}`);
    [month, leap] = mapped;
  }
  if (calendar === "roc" && d.era !== "Minguo") year = 1 - year;
  if (calendar === "japanese") {
    era = JAPANESE_ERA_CODES[d.era];
    if (!era) throw new Error(`no code for Japanese era ${d.era} at ${iso(ms)}`);
  }
  return `${iso(ms)}|${year}|${month}|${leap}|${day}|${era}`;
};

const fixture = CALENDARS.map((calendar) => {
  const dates = new Set(sample);
  // Every month boundary from 2019 to 2027: each first day and the day before it.
  for (let ms = Date.UTC(2019, 0, 1, 12); ms < Date.UTC(2028, 0, 1); ms += DAY) {
    if (Number(icuDate(calendar, ms).day) === 1) { dates.add(ms); dates.add(ms - DAY); }
  }
  const lines = [...dates].sort((a, b) => a - b).map((ms) => row(calendar, ms));
  return `    ${kotlinString(calendar)} to """
${lines.join("\n")}
""",`;
});
write(`${test}/CalendarCldrFixture.kt`, `package io.github.aughtone.calendars

${banner(`ICU ${icu} (Unicode CLDR ${process.versions.cldr})`)}

/**
 * What ICU says for a spread of ISO dates from 1900 to 2100, and for every month boundary from
 * 2019 to 2027, in each calendar, keyed by CLDR calendar name: one case per line as
 * \`iso date|year|month|leap (1 or 0)|day|era\`. The era is given only for the Japanese calendar.
 * ROC years before 1912 are on one continuous axis (1911 is year 0), as the library counts them.
 */
internal val calendarCldrFixture: Map<String, String> = mapOf(
${fixture.join("\n")}
)
`);
console.log(`CLDR ${cldr}, ICU ${icu}: ${eras.length} Japanese eras, Umm al-Qura ${UMALQURA_FIRST}–${UMALQURA_LAST} from ${start}, ${CALENDARS.length} calendars in the fixture`);
