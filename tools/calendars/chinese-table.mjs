// Generates the Chinese calendar table :calendars bundles, and the fixture its tests check the
// Chinese calendar against, from the Hong Kong Observatory's Gregorian–Lunar calendar conversion
// tables for 1901 to 2100.
//
//   node tools/calendars/chinese-table.mjs
//
// Run it from the repository root. It fetches one table per year and rewrites two files. The
// tables name each month on its first day ("2nd Lunar Month"); a leap month repeats the name of
// the month before it.
import { writeFileSync } from "node:fs";

const FIRST = 1901, LAST = 2100;
const DAY = 86400000;
const pad = (n) => String(n).padStart(2, "0");

const starts = []; // { ms, month, leap }
for (let y = FIRST; y <= LAST; y++) {
  const response = await fetch(`https://www.hko.gov.hk/en/gts/time/calendar/text/files/T${y}e.txt`);
  if (!response.ok) throw new Error(`no table for ${y}: HTTP ${response.status}`);
  for (const line of (await response.text()).split(/\r?\n/)) {
    const m = line.match(/^(\d{4})\/(\d{1,2})\/(\d{1,2})\s+(\d+)\w\w Lunar month/i);
    if (!m) continue;
    const month = Number(m[4]);
    const leap = starts.length > 0 && starts[starts.length - 1].month === month;
    starts.push({ ms: Date.UTC(Number(m[1]), Number(m[2]) - 1, Number(m[3]), 12), month, leap });
  }
}
const iso = (ms) => new Date(ms).toISOString().slice(0, 10);

// Each Chinese year, by the ISO year it begins in, packed into one Int: bit i set when its i-th
// month has 30 days, bits 13–16 the month the leap month follows (0 for none), bits 17–22 the day
// of the ISO year New Year falls on (0 for 1 January). A year needs the start of the next, so the
// table ends the year before the last the Observatory publishes.
const words = [];
const firstYear = FIRST, lastYear = LAST - 1;
for (let year = firstYear; year <= lastYear; year++) {
  const begin = starts.findIndex((s) => s.month === 1 && !s.leap && new Date(s.ms).getUTCFullYear() === year);
  const end = starts.findIndex((s, i) => i > begin && s.month === 1 && !s.leap);
  if (begin < 0 || end < 0) throw new Error(`no complete year ${year}`);
  let mask = 0, leapMonth = 0;
  for (let i = begin; i < end; i++) {
    const length = Math.round((starts[i + 1].ms - starts[i].ms) / DAY);
    if (length !== 29 && length !== 30) throw new Error(`${year}: a month of ${length} days at ${iso(starts[i].ms)}`);
    if (length === 30) mask |= 1 << (i - begin);
    if (starts[i].leap) leapMonth = starts[i].month;
  }
  const newYear = Math.round((starts[begin].ms - Date.UTC(year, 0, 1, 12)) / DAY);
  words.push(mask | (leapMonth << 13) | (newYear << 17));
}

const banner = `// ─────────────────────────────────────────────────────────────────────────────
// Generated from the Hong Kong Observatory's Gregorian–Lunar calendar conversion tables by
// tools/calendars/chinese-table.mjs — do not edit by hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────`;
const rows = words.map((w, i) => (i % 8 === 0 ? "    " : "") + `0x${w.toString(16).padStart(6, "0")},` + (i % 8 === 7 || i === words.length - 1 ? "\n" : " ")).join("");
writeFileSync("calendars/src/commonMain/kotlin/io/github/aughtone/calendars/data/ChineseTable.kt", `package io.github.aughtone.calendars.data

${banner}

/** The first year of the Chinese calendar table. */
internal const val CHINESE_TABLE_FIRST_YEAR: Int = ${firstYear}

/** The last year of the Chinese calendar table. */
internal const val CHINESE_TABLE_LAST_YEAR: Int = ${lastYear}

/**
 * The Chinese calendar's years from ${firstYear}, one Int each, by the ISO year the year begins in:
 * bit i is set when the year's i-th month, in order, has 30 days (the leap month counted after
 * its namesake); bits 13–16 hold the month the leap month follows, 0 for none; and bits 17–22 the
 * day of the ISO year New Year falls on, 0 for 1 January.
 */
internal val chineseYears: IntArray = intArrayOf(
${rows})
`);
writeFileSync("calendars/src/commonTest/kotlin/io/github/aughtone/calendars/ChineseCalendarFixture.kt", `package io.github.aughtone.calendars

${banner}

/**
 * Every Chinese month start from ${FIRST} to ${LAST} in the Hong Kong Observatory's tables, one per
 * line as \`iso date|month|leap (1 or 0)\`.
 */
internal val chineseCalendarFixture: String = """
${starts.map((s) => `${iso(s.ms)}|${s.month}|${s.leap ? 1 : 0}`).join("\n")}
"""
`);
console.log(`${starts.length} month starts, table ${firstYear}–${lastYear} (${words.length} years)`);
