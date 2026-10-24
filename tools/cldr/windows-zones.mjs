// Generates the Windows time-zone name to IANA identifier mapping that :datetime bundles, from
// the Unicode CLDR JSON data: the cldr-core package's supplemental/windowsZones.json, and the
// cldr-bcp47 package's bcp47/timezone.json for each zone's current IANA name and its aliases.
//
//   node tools/cldr/windows-zones.mjs <path to cldr-core> <path to cldr-bcp47>
//
// Run it from the repository root. It rewrites one file and prints what it wrote. The CLDR
// version it used is recorded in it. Do not edit the generated file by hand.
//
// CLDR maps each Windows name per territory: "001" is the default, and a territory's entry can
// list several zones, the first of which is that territory's own. CLDR names zones by its own
// stable identifiers, some of which are older IANA names ("Asia/Calcutta"); the output uses each
// zone's current IANA name ("Asia/Kolkata"), and lists the older names and other aliases so a
// caller holding any of them finds the zone.
import { readFileSync, writeFileSync, existsSync } from "node:fs";
import { join } from "node:path";

const [core, bcp47] = process.argv.slice(2);
const source = core && join(core, "supplemental", "windowsZones.json");
const aliasSource = bcp47 && join(bcp47, "bcp47", "timezone.json");
if (!source || !existsSync(source) || !aliasSource || !existsSync(aliasSource)) {
  console.error("usage: node tools/cldr/windows-zones.mjs <path to cldr-core> <path to cldr-bcp47>");
  process.exit(1);
}
const version = JSON.parse(readFileSync(join(core, "package.json"), "utf8")).version;
const zoneData = JSON.parse(readFileSync(source, "utf8")).supplemental.windowsZones.mapTimezones.map((m) => m.mapZone);

// Every spelling of a zone, to its current IANA name.
const ianaOf = new Map();
for (const entry of Object.values(JSON.parse(readFileSync(aliasSource, "utf8")).keyword.u.tz)) {
  if (typeof entry !== "object" || !entry._alias) continue;
  const names = entry._alias.split(" ");
  const iana = entry._iana ?? names[0];
  for (const name of [...names, iana]) ianaOf.set(name, iana);
}
const current = (id) => ianaOf.get(id) ?? id;
const zones = zoneData.map((z) => ({ ...z, _type: z._type.split(" ").map(current).join(" ") }));

const byName = new Map();
for (const { _other: name, _territory: territory, _type: ids } of zones) {
  if (!byName.has(name)) byName.set(name, []);
  byName.get(name).push([territory, ids]);
}
for (const [name, entries] of byName) {
  if (!entries.some(([t]) => t === "001")) throw new Error(`no default (001) zone for "${name}"`);
}

const kotlinString = (text) => JSON.stringify(text).replace(/\$/g, "\\$");
const rows = [...byName].map(([name, entries]) =>
  `    ${kotlinString(name)} to listOf(${entries.map(([t, ids]) => `${kotlinString(t)} to ${kotlinString(ids)}`).join(", ")}),`);

// The other spellings of every zone the mapping lists, to its current IANA name.
const listed = new Set(zones.flatMap((z) => z._type.split(" ")));
const aliases = [...ianaOf].filter(([name, iana]) => name !== iana && listed.has(iana)).sort(([a], [b]) => (a < b ? -1 : 1));

const out = `package io.github.aughtone.datetime.format.resources.timezone

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${version} by tools/cldr/windows-zones.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * CLDR's mapping from Windows time-zone names to IANA identifiers: for each Windows name, the
 * territories it is mapped for ("001" being the default) and the IANA identifiers listed for
 * each, separated by spaces, the territory's own zone first. Identifiers are current IANA names.
 */
internal val windowsZoneMapping: Map<String, List<Pair<String, String>>> = mapOf(
${rows.join("\n")}
)

/**
 * Other spellings of the zones in [windowsZoneMapping] — older IANA names and links such as
 * "Asia/Calcutta" or "US/Eastern" — to the current IANA name the mapping uses.
 */
internal val windowsZoneAliases: Map<String, String> = mapOf(
${aliases.map(([a, i]) => `    ${kotlinString(a)} to ${kotlinString(i)},`).join("\n")}
)
`;
const path = "datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/timezone/WindowsZoneMapping.kt";
writeFileSync(path, out);
console.log(`CLDR ${version}: ${byName.size} Windows names, ${zones.length} mappings, ${aliases.length} aliases, wrote ${path} (${out.length} characters)`);
