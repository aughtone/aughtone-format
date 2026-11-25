// Generates the scripts :readable reads a locale's data in, from the Unicode CLDR likely-subtags
// data in this Node runtime's ICU.
//
//   node tools/cldr/locale-scripts.mjs
//
// Run it from the repository root. It rewrites one file and prints what it wrote. The CLDR version
// it used is recorded in it. Do not edit the generated file by hand.
//
// A locale's data is chosen by its script before its region, so that Traditional Chinese never
// reads Simplified data. A locale that names no script is still written in one: the script its
// region implies, or its language's default. Chinese in Macau, Taiwan or the United States is
// Traditional; in Singapore, or with no region, Simplified. This records both, for every bundled
// language that some region writes in a script other than its default. A language every region
// writes in one script needs no entry, because its script never decides between two tables.
import { writeFileSync } from "node:fs";

// Every bundled language, by the code the library looks it up with, as in number-symbols.mjs.
const SOUTH_ASIAN = ["bn", "gu", "kn", "ml", "mr", "pa", "ta", "te", "ur"];
const LANGUAGES = [
  "af", "ar", "az", "be", "bg", "ca", "cs", "da", "de", "el", "en", "es", "et", "eu", "fa", "fi", "fr", "gl", "he",
  "hi", "hr", "hu", "hy", "id", "is", "it", "iu", "ja", "ka", "kk", "ko", "lt", "lv", "mk", "ms", "nb", "nl", "nn",
  "no", "pl", "pt", "ro", "ru", "sk", "sl", "sq", "sr", "sv", "sw", "th", "tr", "uk", "uz", "vi", "zh", ...SOUTH_ASIAN,
];

// Every current two-letter region code ICU can name, as in number-symbols.mjs.
const regionNames = new Intl.DisplayNames("en", { type: "region", fallback: "none" });
const REGIONS = [];
for (let a = 65; a <= 90; a++) {
  for (let b = 65; b <= 90; b++) {
    const code = String.fromCharCode(a, b);
    try {
      const current = Intl.getCanonicalLocales(`und-${code}`)[0] === `und-${code}`;
      if (current && regionNames.of(code)) REGIONS.push(code);
    } catch {
      // Not a region code.
    }
  }
}

const defaults = [];
const regional = [];
for (const language of LANGUAGES) {
  const script = new Intl.Locale(language).maximize().script;
  if (!script) throw new Error(`${language}: CLDR gives no likely script`);
  const differing = [];
  for (const region of REGIONS) {
    const likely = new Intl.Locale(`${language}-${region}`).maximize();
    if (likely.language !== language || likely.region !== region) continue;
    if (likely.script && likely.script !== script) differing.push([`${language}-${region}`, likely.script]);
  }
  if (differing.length === 0) continue;
  defaults.push([language, script]);
  regional.push(...differing);
}

const cldr = process.versions.cldr;
const out = "readable/src/commonMain/kotlin/io/github/aughtone/readable/LocaleScripts.resources.kt";
const text = `package io.github.aughtone.readable

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${cldr} by tools/cldr/locale-scripts.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * The script each bundled language is written in when neither the locale nor its region names
 * another, from CLDR's likely subtags. Only languages that some region writes in another script
 * appear, because for the rest the script never decides between two tables.
 */
internal val defaultScriptByLanguage: Map<String, String> = mapOf(
${defaults.map(([k, v]) => `    "${k}" to "${v}",`).join("\n")}
)

/**
 * The script a language is written in for a region, where that differs from
 * [defaultScriptByLanguage], from CLDR's likely subtags: \`"zh-TW"\` is \`"Hant"\`.
 */
internal val scriptByLanguageRegion: Map<String, String> = mapOf(
${regional.map(([k, v]) => `    "${k}" to "${v}",`).join("\n")}
)
`;
writeFileSync(out, text);
console.log(`${out}: ${defaults.length} languages, ${regional.length} regions (CLDR ${cldr})`);
