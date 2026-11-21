// Generates the number symbols and currency placement that :readable bundles, and the fixtures
// its tests check them against, from the Unicode CLDR data in this Node runtime's ICU.
//
//   node tools/cldr/number-symbols.mjs
//
// Run it from the repository root. It rewrites four files and prints what it wrote. The CLDR
// version it used is recorded in each. Do not edit the generated files by hand. The places where
// the output departs from CLDR are listed in ADJUSTMENTS below, each with its reason.
//
// For each bundled language, and each region CLDR writes that language in, it records how a
// number is written: the decimal and group symbols, the minus, plus and percent signs, how many
// digits must stand before the first group symbol, and whether digits are grouped the Indian way
// (the last three, then in twos). Symbols are always those of Latin digits, which is what the
// library writes. A region is recorded only where it differs from its language, and a language
// only where it differs from English, so the table stays small and follows the same subtag
// fallback the library looks it up with. A language CLDR has no number data for (Inuktitut, at
// the time of writing) is not recorded and reads as English.
//
// It records, the same way, where an amount's currency symbol and minus sign go: CLDR's standard
// currency pattern for a positive and a negative amount, written as a template with "¤" for the
// symbol, "#" for the digits, "-" for the minus sign, and the literal text between them (a
// no-break space, or nothing). The templates are read with the narrow euro sign, which is a
// symbol character in every locale, so CLDR's currency-spacing rule — a no-break space between a
// symbol that ends in a letter and the digits, as in "CHF 5.00" — does not leak into them; the
// library applies that rule itself, to whatever symbol it shows.
import { writeFileSync } from "node:fs";

// Every bundled language, by the code the library looks it up with. The South Asian languages
// are bundled for their relative-time and duration phrases.
const SOUTH_ASIAN = ["bn", "gu", "kn", "ml", "mr", "pa", "ta", "te", "ur"];
const LANGUAGES = [
  "af", "ar", "az", "be", "bg", "ca", "cs", "da", "de", "el", "en", "es", "et", "eu", "fa", "fi", "fr", "gl", "he",
  "hi", "hr", "hu", "hy", "id", "is", "it", "iu", "ja", "ka", "kk", "ko", "lt", "lv", "mk", "ms", "nb", "nl", "nn",
  "no", "pl", "pt", "ro", "ru", "sk", "sl", "sq", "sr", "sv", "sw", "th", "tr", "uk", "uz", "vi", "zh", ...SOUTH_ASIAN,
];

// Every current two-letter region code ICU can name. A retired code (DD, FX, TP, ZR) is an alias
// of its replacement, which the library is asked for instead.
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

// Bidirectional marks are layout hints for mixed-direction text; the library emits plain text.
const clean = (text) => text.replace(/[‎‏؜]/g, "");
// Places where the CLDR data is adjusted, each with its reason. Keep this list short.
const ADJUSTMENTS = {};

const formatter = (tag, options = {}) => new Intl.NumberFormat(`${tag}-u-nu-latn`, options);
const partOf = (parts, type) => parts.filter((part) => part.type === type).map((part) => part.value)[0] ?? "";

/** How [tag] writes a number, read back from ICU's own output. */
function symbolsOf(tag) {
  const big = formatter(tag).formatToParts(1234567.5);
  const groups = big.filter((part) => part.type === "integer").map((part) => part.value.length);
  const groupedAtFour = formatter(tag).formatToParts(1234).some((part) => part.type === "group");
  const groupedAtFive = formatter(tag).formatToParts(12345).some((part) => part.type === "group");
  const symbols = {
    decimal: partOf(big, "decimal"),
    group: partOf(big, "group"),
    minus: clean(partOf(formatter(tag).formatToParts(-1), "minusSign")),
    plus: clean(partOf(formatter(tag, { signDisplay: "always" }).formatToParts(1), "plusSign")),
    percent: clean(partOf(formatter(tag, { style: "percent" }).formatToParts(0.25), "percentSign")),
    minimumGroupingDigits: groupedAtFour ? 1 : groupedAtFive ? 2 : 3,
    indian: groups.join(",") === "2,2,3",
  };
  return ADJUSTMENTS[tag] ? ADJUSTMENTS[tag](symbols) : symbols;
}

/** Where [tag] puts the currency symbol and the minus sign, as a positive and a negative template. */
function currencyPatternOf(tag, code = "XXX") {
  const template = (amount) =>
    formatter(tag, { style: "currency", currency: code, currencyDisplay: "narrowSymbol" })
      .formatToParts(amount)
      .map((part) => {
        if (part.type === "currency") return "¤";
        if (part.type === "minusSign") return "-";
        if (["integer", "group", "decimal", "fraction"].includes(part.type)) return "#";
        return clean(part.value);
      })
      .join("")
      .replace(/#+/g, "#");
  // CLDR can give money its own separators (Austrian German groups numbers with a space and
  // amounts with a dot). They are recorded only where they differ from the locale's number style.
  // Grouping can differ as well: Malayalam groups numbers the Indian way and amounts in threes.
  const moneyParts = (amount) =>
    formatter(tag, { style: "currency", currency: code, currencyDisplay: "narrowSymbol" }).formatToParts(amount);
  const money = moneyParts(1234567.5);
  const number = symbolsOf(tag);
  const decimal = partOf(money, "decimal");
  const group = partOf(money, "group");
  const indian = money.filter((part) => part.type === "integer").map((part) => part.value.length).join(",") === "2,2,3";
  const grouped = (amount) => moneyParts(amount).some((part) => part.type === "group");
  const minimumGroupingDigits = grouped(1234) ? 1 : grouped(12345) ? 2 : 3;
  return {
    positive: template(1234.5),
    negative: template(-1234.5),
    decimal: decimal === number.decimal ? null : decimal,
    group: group === number.group ? null : group,
    indian: indian === number.indian ? null : indian,
    minimumGroupingDigits: minimumGroupingDigits === number.minimumGroupingDigits ? null : minimumGroupingDigits,
  };
}

const same = (a, b) => JSON.stringify(a) === JSON.stringify(b);

/** The symbol [code] shows in [tag] when asked for its narrow form. */
const narrowSymbolIn = (tag, code) =>
  clean(formatter(tag, { style: "currency", currency: code, currencyDisplay: "narrowSymbol" })
    .formatToParts(1).find((part) => part.type === "currency").value);
const hasLetter = (text) => /\p{L}/u.test(text);

// The generic pattern of a locale is read from several currencies whose narrow symbol is a
// symbol character there, and the one most of them agree on is taken: a currency with a pattern
// of its own in that locale (below) is then outvoted, and a symbol that is letters in that locale
// ("XXX" in Japanese) is not asked at all.
const PROBES = ["XXX", "USD", "GBP", "EUR", "ILS", "INR", "NGN", "UAH"];
function genericCurrencyPatternOf(tag) {
  const votes = new Map();
  for (const code of PROBES) {
    if (hasLetter(narrowSymbolIn(tag, code))) continue;
    const key = JSON.stringify(currencyPatternOf(tag, code));
    votes.set(key, (votes.get(key) ?? 0) + 1);
  }
  if (votes.size === 0) throw new Error(`${tag}: no probe currency has a symbol narrow symbol`);
  return JSON.parse([...votes].sort((a, b) => b[1] - a[1])[0][0]);
}
/** Every bundled language and every region whose [of] differs from its language's. */
function differing(of, root) {
  const found = [];
  for (const language of LANGUAGES) {
    const own = of(language);
    if (!same(own, root)) found.push([language, own]);
    for (const region of REGIONS) {
      const tag = `${language}-${region}`;
      const regional = of(tag);
      if (!same(regional, own)) found.push([tag, regional]);
    }
  }
  return found;
}
const english = symbolsOf("en");
const entries = differing(symbolsOf, english);
const englishCurrency = genericCurrencyPatternOf("en");
const currencyEntries = differing(genericCurrencyPatternOf, englishCurrency);

// A few currencies have a pattern or separators of their own in some locales: English in
// Switzerland, Sweden, Poland and other countries outside the euro writes euro amounts the
// English way ("€1,234.50") and its own currency the local way ("CHF 1'234.50"). Those are found
// by comparing each two-digit currency's separators and sign order with the generic pattern's in
// every tag above, then recorded, for every tag, wherever the library's lookup would otherwise get
// them wrong. The lookup walks the subtag chain and, at each tag, takes the currency's own
// pattern before the generic one.
const generalByTag = new Map(currencyEntries);
const overrides = new Map();
const lookup = (tag, code) => {
  for (let t = tag; t; t = t.includes("-") ? t.slice(0, t.lastIndexOf("-")) : "") {
    if (overrides.has(`${t}:${code}`)) return overrides.get(`${t}:${code}`);
    if (generalByTag.has(t)) return generalByTag.get(t);
  }
  return englishCurrency;
};
const signature = (tag, code) =>
  formatter(tag, { style: "currency", currency: code, currencyDisplay: "narrowSymbol" })
    .formatToParts(-1234567.5)
    .map((part) => (part.type === "currency" ? "¤" : ["integer", "fraction"].includes(part.type) ? "#" : part.type === "literal" ? "" : part.value))
    .join("");
const twoDigit = Intl.supportedValuesOf("currency").filter(
  (code) => new Intl.NumberFormat("en", { style: "currency", currency: code }).resolvedOptions().maximumFractionDigits === 2);
const knownTags = ["en", ...LANGUAGES, ...entries.map(([tag]) => tag), ...currencyEntries.map(([tag]) => tag)];
const genericSignature = (tag) => {
  const p = lookup(tag, "");
  const number = symbolsOf(tag);
  return `${p.negative}|${p.decimal ?? number.decimal}|${p.group ?? number.group}|${p.indian ?? number.indian}|${p.minimumGroupingDigits ?? number.minimumGroupingDigits}`;
};
const ownSignature = (tag, code) => {
  const p = currencyPatternOf(tag, code);
  const number = symbolsOf(tag);
  return `${p.negative.replace(/[^¤#-]/g, "")}|${p.decimal ?? number.decimal}|${p.group ?? number.group}|${p.indian ?? number.indian}|${p.minimumGroupingDigits ?? number.minimumGroupingDigits}`;
};
const looseSignature = (signature) => signature.replace(/^[^|]*/, (template) => template.replace(/[^¤#-]/g, ""));
const overrideCodes = twoDigit.filter((code) =>
  knownTags.some((tag) => looseSignature(genericSignature(tag)) !== ownSignature(tag, code)));
for (const code of overrideCodes) {
  for (const language of LANGUAGES) {
    for (const tag of [language, ...REGIONS.map((region) => `${language}-${region}`)]) {
      const own = ownSignature(tag, code);
      if (own === looseSignature(genericSignature(tag)) && !overrides.has(`${language}:${code}`)) continue;
      // The template is read with this currency's narrow symbol, so it must be a symbol
      // character here for CLDR's spacing rule to stay out of it, as the euro sign is.
      if (hasLetter(narrowSymbolIn(tag, code))) throw new Error(`${tag}: ${code} has its own pattern but a letter symbol`);
      const actual = currencyPatternOf(tag, code);
      if (!same(actual, lookup(tag, code))) overrides.set(`${tag}:${code}`, actual);
    }
  }
}
for (const [tag, { positive, negative }] of [["en", englishCurrency], ...currencyEntries, ...overrides]) {
  for (const template of [positive, negative]) {
    // Each of the symbol, the digits and the minus sign at most once, and nothing else but spaces.
    const once = ["¤", "#", "-"].every((c) => template.split(c).length <= 2);
    if (!once || !/^[¤#\-\u00a0\u202f ]*$/.test(template)) {
      throw new Error(`${tag}: unexpected currency template ${JSON.stringify(template)}`);
    }
  }
}

const kotlinString = (text) =>
  `"${[...text].map((c) => {
    const code = c.codePointAt(0);
    if (c === '"' || c === "\\" || c === "$") return `\\${c}`;
    return code >= 0x20 && code < 0x7f ? c : `\\u${code.toString(16).toUpperCase().padStart(4, "0")}`;
  }).join("")}"`;
const styleOf = (s) => {
  const named = [];
  if (s.minus !== english.minus) named.push(`minus = ${kotlinString(s.minus)}`);
  if (s.plus !== english.plus) named.push(`plus = ${kotlinString(s.plus)}`);
  if (s.percent !== english.percent) named.push(`percent = ${kotlinString(s.percent)}`);
  if (s.minimumGroupingDigits !== 1) named.push(`minimumGroupingDigits = ${s.minimumGroupingDigits}`);
  if (s.indian) named.push("indian = true");
  return `NumberStyle(${[kotlinString(s.decimal), kotlinString(s.group), ...named].join(", ")})`;
};

const cldr = process.versions.cldr;
const header = `// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR ${cldr} by tools/cldr/number-symbols.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────`;

const data = `package io.github.aughtone.readable.number

${header}

/**
 * How each bundled language, and each region that writes it differently, writes a number, as
 * Unicode CLDR publishes it for Latin digits. Keyed by BCP 47 tag (\`"fr"\`, \`"fr-CA"\`). A
 * region appears only where it differs from its language, and a language only where it differs
 * from [englishNumberStyle], so a lookup walks the subtag chain and ends there.
 */
internal val cldrNumberStyles: Map<String, NumberStyle> = mapOf(
${entries.map(([tag, s]) => `    "${tag}" to ${styleOf(s)},`).join("\n")}
)

/** How English writes a number, and any language CLDR has no number data for. */
internal val englishNumberStyle: NumberStyle = ${styleOf(english)}
`;

// The fixture: ICU's own output for a sample of numbers in every recorded locale and every
// bundled language, one case per line as `kind|value|precision|text`. A kind of `d` is a Double,
// `l` a Long. Values that ICU writes as a signed zero ("-0") are avoided: the library writes zero
// without a sign, deliberately.
const SAMPLES = [
  ["d", "0.0", 1], ["d", "5.0", 1], ["d", "-5.0", 1], ["d", "0.25", 2], ["d", "1234.0", 1], ["d", "-1234.5", 1],
  ["d", "12345.678", 2], ["d", "-1234567.891", 2], ["d", "1234567.5", 0], ["l", "1234", 0], ["l", "-12345", 0],
  ["l", "1234567", 0], ["l", "-1234567890123", 0], ["l", "9223372036854775807", 0],
];
const sampleText = (tag, [kind, value, precision]) => {
  const options = { maximumFractionDigits: precision, roundingMode: "halfExpand" };
  const number = kind === "l" ? BigInt(value) : Number(value);
  return `${kind}|${value}|${precision}|${clean(formatter(tag, options).format(number))}`;
};
const fixtureTags = [...new Set([...LANGUAGES, ...entries.map(([tag]) => tag), ...currencyEntries.map(([tag]) => tag)])].sort();
const fixture = `package io.github.aughtone.readable.number

${header}

/**
 * What Unicode CLDR writes for a sample of numbers, per BCP 47 tag, one case per line as
 * \`kind|value|precision|text\`: \`d\` for a Double, \`l\` for a Long.
 */
internal val numberCldrFixture: Map<String, String> = mapOf(
${fixtureTags.map((tag) => `    "${tag}" to """
${SAMPLES.map((sample) => sampleText(tag, sample)).join("\n")}
""",`).join("\n")}
)
`;

const currencyPatternKotlin = (p) => {
  const named = [];
  if (p.decimal !== null) named.push(`decimal = ${kotlinString(p.decimal)}`);
  if (p.group !== null) named.push(`group = ${kotlinString(p.group)}`);
  if (p.minimumGroupingDigits !== null) named.push(`minimumGroupingDigits = ${p.minimumGroupingDigits}`);
  if (p.indian !== null) named.push(`indian = ${p.indian}`);
  return `CurrencyPattern(${[kotlinString(p.positive), kotlinString(p.negative), ...named].join(", ")})`;
};
const currencyData = `package io.github.aughtone.readable.financial

${header}

/**
 * Where each bundled language, and each region that does it differently, puts an amount's
 * currency symbol and minus sign, from Unicode CLDR's standard currency pattern. Keyed by BCP 47
 * tag like [io.github.aughtone.readable.number.cldrNumberStyles], and looked up the same way,
 * ending at [englishCurrencyPattern]. See [CurrencyPattern] for the template letters.
 */
internal val cldrCurrencyPatterns: Map<String, CurrencyPattern> = mapOf(
${currencyEntries.map(([tag, p]) => `    "${tag}" to ${currencyPatternKotlin(p)},`).join("\n")}
)

/**
 * The currencies that have a pattern or separators of their own in a locale, keyed by BCP 47 tag
 * and ISO 4217 code (\`"en-CH:EUR"\`). English in Switzerland writes francs the Swiss way
 * (\`"CHF 1'234.50"\`) and euros the English way (\`"€1,234.50"\`). A lookup takes these before
 * [cldrCurrencyPatterns] at each tag of the subtag chain.
 */
internal val cldrCurrencyPatternOverrides: Map<String, CurrencyPattern> = mapOf(
${[...overrides].map(([key, p]) => `    "${key}" to ${currencyPatternKotlin(p)},`).join("\n")}
)

/** Where English, and any language CLDR has no currency pattern for, puts the symbol and sign. */
internal val englishCurrencyPattern: CurrencyPattern = ${currencyPatternKotlin(englishCurrency)}
`;

// The money fixture: ICU's own text for a sample of amounts in a few currencies, per tag, one
// case per line as `currency|digits|amount|symbol|text`, where `symbol` is the symbol ICU shows
// for that currency in that locale. The library's own choice of symbol is a separate question, so
// the test formats with ICU's symbol and checks only placement, spacing and the number.
const CURRENCIES = [["EUR", 2], ["USD", 2], ["CHF", 2], ["JPY", 0], ["GBP", 2]];
const AMOUNTS = ["1234.5", "-1234.5", "0", "1234567.25", "-5"];
const moneyCase = (tag, [code, digits], amount) => {
  const f = formatter(tag, { style: "currency", currency: code, roundingMode: "halfEven" });
  const parts = f.formatToParts(Number(amount));
  const symbol = clean(parts.filter((part) => part.type === "currency").map((part) => part.value).join(""));
  // A raw Kotlin string reads "$" as the start of a template, so it is written as ${'$'}.
  const raw = (text) => text.replaceAll("$", () => "${'$'}");
  return raw(`${code}|${digits}|${amount}|${symbol}|${clean(f.format(Number(amount)))}`);
};
const moneyFixture = `package io.github.aughtone.readable.financial

${header}

/**
 * What Unicode CLDR writes for a sample of amounts, per BCP 47 tag, one case per line as
 * \`currency|digits|amount|symbol|text\`. \`symbol\` is the symbol ICU shows in that locale.
 */
internal val moneyCldrFixture: Map<String, String> = mapOf(
${fixtureTags.map((tag) => `    "${tag}" to """
${CURRENCIES.flatMap((currency) => AMOUNTS.map((amount) => moneyCase(tag, currency, amount))).join("\n")}
""",`).join("\n")}
)
`;

const base = "readable/src";
const dataPath = `${base}/commonMain/kotlin/io/github/aughtone/readable/number/NumberStyles.resources.kt`;
const fixturePath = `${base}/commonTest/kotlin/io/github/aughtone/readable/number/NumberCldrFixture.kt`;
const currencyDataPath = `${base}/commonMain/kotlin/io/github/aughtone/readable/financial/CurrencyPatterns.resources.kt`;
const moneyFixturePath = `${base}/commonTest/kotlin/io/github/aughtone/readable/financial/MoneyCldrFixture.kt`;
const outputs = [[dataPath, data], [fixturePath, fixture], [currencyDataPath, currencyData], [moneyFixturePath, moneyFixture]];
for (const [path, text] of outputs) writeFileSync(path, text);
console.log(`CLDR ${cldr}: ${entries.length} number styles, ${currencyEntries.length} currency patterns and ${overrides.size} currency overrides (${overrideCodes.join(", ")}) across ${LANGUAGES.length} languages, ${fixtureTags.length} fixture tags`);
for (const [path, text] of outputs) console.log(`wrote ${path} (${text.length} characters)`);
