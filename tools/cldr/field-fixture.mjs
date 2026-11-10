// Generates the fixture the :datetime field-pattern test compares against: the shape of what
// Unicode CLDR, through this Node runtime's ICU, writes for each set of fields the library offers,
// in every locale :datetime bundles.
//
//   node tools/cldr/field-fixture.mjs
//
// Run it from the repository root. A shape is the order of the fields, whether each is a number
// (padded or not) or a name, and the literal text between them. It is what the skeleton matching
// decides, without the names, which the library keeps some of its own of. Do not edit the
// generated file by hand.
import { readFileSync, writeFileSync } from "node:fs";

const patternTable = readFileSync(
  "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/LocaleDatePatterns.kt",
  "utf8",
);
// CLDR has no Inuktitut data of its own; ICU and the CLDR JSON fill the gap from different
// parents, so it is left out.
const keys = [...patternTable.matchAll(/^    "([A-Za-z-]+)" to DatePatterns/gm)].map((m) => m[1]).filter((k) => k !== "iu");
const TAG = { CA: "en-CA", US: "en-US" };

// The skeletons: every date field set, every time field set on each clock with and without a
// zone, and a selection of the two together.
const dates = [];
for (const y of ["", "y"]) for (const m of ["", "M", "MM", "MMM", "MMMM"]) for (const e of ["", "E", "EEEE"]) for (const d of ["", "d"]) {
  const skeleton = y + m + e + d;
  if (skeleton) dates.push(skeleton);
}
dates.push("Gy", "GyMMMd", "GyMMMMEEEEd", "GyMd");
const times = [];
for (const h of ["", "h", "H"]) for (const m of ["", "m"]) for (const s of ["", "s"]) for (const z of ["", "z", "zzzz"]) {
  if (!h && !m && !s) continue;
  times.push(h + m + s + z);
}
const both = [];
for (const d of ["yMMMd", "MMMEd", "yMd", "E", "d", "MMMMd", "yMMMMEEEEd", "EEEEd"]) {
  for (const t of ["h", "hm", "Hm", "hmz", "Hms", "Hmzzzz"]) both.push(d + t);
}
const skeletons = [...dates, ...times, ...both];

/** The Intl options that ask ICU for [skeleton]'s fields. */
function optionsFor(skeleton) {
  const o = { timeZone: "America/Toronto" };
  const runs = skeleton.match(/(.)\1*/g);
  for (const run of runs) {
    const n = run.length;
    switch (run[0]) {
      case "G": o.era = n === 4 ? "long" : "short"; break;
      case "y": o.year = "numeric"; break;
      case "M": o.month = ["numeric", "2-digit", "short", "long"][n - 1]; break;
      case "d": o.day = "numeric"; break;
      case "E": o.weekday = n === 4 ? "long" : "short"; break;
      case "h": o.hour = "numeric"; o.hourCycle = "h12"; break;
      case "H": o.hour = "numeric"; o.hourCycle = "h23"; break;
      case "m": o.minute = "numeric"; break;
      case "s": o.second = "numeric"; break;
      case "z": o.timeZoneName = n === 4 ? "long" : "short"; break;
    }
  }
  return o;
}

const CLASS = { era: "G", year: "y", month: "M", day: "d", weekday: "E", hour: "h", minute: "m", second: "s", dayPeriod: "a", timeZoneName: "z" };
const literal = (text) => text.replace(/[‎‏؜]/g, "").replace(/[   ]/g, " ");

/** The shape of ICU's output: `<Mt>` a month name, `<dn>` a day number, `<mN>` a padded minute. */
function shape(parts) {
  return parts.map((p) => {
    if (p.type === "literal") return literal(p.value);
    const c = CLASS[p.type];
    if (!c) throw new Error(`part ${p.type}`);
    if (c === "a" || c === "z" || c === "G" || c === "E") return `<${c}t>`;
    if (!/^\d+$/.test(p.value)) return `<${c}t>`;
    if (c === "y") return p.value.length === 2 ? "<yY>" : "<yn>";
    return p.value.length >= 2 && p.value.startsWith("0") ? `<${c}N>` : `<${c}n>`;
  }).join("");
}

// Saturday 1 June 2024, 04:05:06 in Toronto: every number below ten, so padding shows.
const sample = new Date("2024-06-01T08:05:06Z");
const lines = [];
for (const key of keys) {
  // The Gregorian calendar, which Persian and Thai do not default to, and Latin digits.
  const tag = `${TAG[key] ?? key}-u-ca-gregory-nu-latn`;
  for (const skeleton of skeletons) {
    const parts = new Intl.DateTimeFormat(tag, optionsFor(skeleton)).formatToParts(sample);
    lines.push(`${key}|${skeleton}|${shape(parts)}`);
  }
}

const out = `package io.github.aughtone.datetime.format

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${process.versions.cldr} (through ICU ${process.versions.icu}) by
// tools/cldr/field-fixture.mjs — do not edit by hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * The shape of what CLDR writes for a set of fields, one case per line:
 * \`locale|skeleton|shape\`. In a shape a field is \`<\` its class letter, then \`t\` for a name,
 * \`n\` for a number, \`N\` for a zero-padded number or \`Y\` for a two-digit year, then \`>\`; the
 * text between fields is CLDR's, with every kind of space written as a plain one.
 */
internal val fieldCldrFixture: String = """
${lines.join("\n")}
"""
`;
const path = "datetime/src/jvmTest/kotlin/io/github/aughtone/datetime/format/FieldCldrFixture.kt";
writeFileSync(path, out);
console.log(`CLDR ${process.versions.cldr}: ${lines.length} cases for ${keys.length} locales, wrote ${path}`);
