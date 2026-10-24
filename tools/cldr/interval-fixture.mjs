// Generates the fixture the :datetime interval tests compare against: what Unicode CLDR, through
// this Node runtime's ICU, writes for a sample of time and date ranges in every locale :datetime
// bundles, together with the single values at each end.
//
//   node tools/cldr/interval-fixture.mjs
//
// Run it from the repository root. The test compares a range only where the library's single
// values match ICU's, so a difference in month names or widths that the library keeps on purpose
// does not count against its ranges. Do not edit the generated file by hand.
import { readFileSync, writeFileSync } from "node:fs";

const patternTable = readFileSync(
  "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/LocaleDatePatterns.kt",
  "utf8",
);
const keys = [...patternTable.matchAll(/^    "([A-Za-z-]+)" to DatePatterns/gm)].map((m) => m[1]);
const TAG = { CA: "en-CA", US: "en-US" };

const at = (date, time) => new Date(`${date}T${time}:00Z`);
const TIMES = [["09:00", "09:30"], ["09:00", "11:30"], ["09:00", "17:00"], ["22:00", "02:00"]];
const DATES = [["2024-03-03", "2024-03-05"], ["2024-03-03", "2024-04-02"], ["2024-12-30", "2025-01-02"]];
const clean = (text) => text.replace(/[‎‏؜]/g, "");

const lines = [];
for (const key of keys) {
  const tag = `${TAG[key] ?? key}-u-nu-latn`;
  for (const [clock, cycle] of [["12", "h12"], ["24", "h23"]]) {
    const f = new Intl.DateTimeFormat(tag, { timeStyle: "short", hourCycle: cycle, timeZone: "UTC" });
    for (const [a, b] of TIMES) {
      const s = at("2024-03-05", a), e = at("2024-03-05", b);
      lines.push([key, `time${clock}`, a, b, clean(f.format(s)), clean(f.format(e)), clean(f.formatRange(s, e))].join("|"));
    }
  }
  for (const style of ["short", "medium", "long"]) {
    const f = new Intl.DateTimeFormat(tag, { dateStyle: style, timeZone: "UTC" });
    for (const [a, b] of DATES) {
      const s = at(a, "12:00"), e = at(b, "12:00");
      lines.push([key, style, a, b, clean(f.format(s)), clean(f.format(e)), clean(f.formatRange(s, e))].join("|"));
    }
  }
}

const out = `package io.github.aughtone.datetime.format

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${process.versions.cldr} (through ICU ${process.versions.icu}) by
// tools/cldr/interval-fixture.mjs — do not edit by hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Ranges as CLDR writes them, one case per line:
 * \`locale|kind|start|end|single start|single end|range\`, where kind is \`time12\`, \`time24\`, or a
 * date style (\`short\`, \`medium\`, \`long\`).
 */
internal val intervalCldrFixture: String = """
${lines.join("\n")}
"""
`;
const path = "datetime/src/commonTest/kotlin/io/github/aughtone/datetime/format/IntervalCldrFixture.kt";
writeFileSync(path, out);
console.log(`CLDR ${process.versions.cldr}: ${lines.length} cases for ${keys.length} locales, wrote ${path}`);
