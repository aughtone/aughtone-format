# Number and Currency Formatting

RAD-0005 · 2026-11-19 · status: open
Keywords: price wraps mid-number, "1 000 €" breaks across lines, non-breaking space, narrow no-break space U+202F, U+00A0, thousands separator, grouping separator, decimal separator, currency symbol placement, CA$ US$, ambiguous dollar sign, minus sign U+2212, percent, CLDR number symbols, regex post-processing of formatted text, phone number wraps, separate numbers module

## Question

Number and currency formatting should be a first-class part of this library, not a side feature. What does the library do today, how far is that from CLDR, and what has to change for numbers and amounts to be correct in every locale we claim — including never breaking across a line in the middle of a value?

The prompt was a familiar workaround: an app rendering formatted text (Markdown in Compose, here) saw French amounts and phone numbers wrap mid-value, "1 000" on one line and "€" on the next. The suggested fix was to run regular expressions over the finished text and swap the spaces for non-breaking ones. That repairs, downstream and for one locale, output this library produced wrong in the first place.

## What happens today

Measured on `develop`, 2026-11-19, by reading the code and its tests:

- **Numbers** are `formatReadable` on every numeric type in `:readable` (`number/Number.functions.kt`). Separators come from a hand-written table, `NumberStyle(decimal, grouping, minimumGroupingDigits, indian)`, walked down the BCP 47 subtag chain to an English default. The arithmetic is sound: digits are worked from the number's text, never rounded through a `Double` where they would lose precision, minimum grouping digits and Indian grouping are handled, and nothing throws.
- **Every space in that table is U+0020.** French, Swedish, Norwegian, Czech, Polish, Russian, Ukrainian, South African English and the rest all group with an ordinary ASCII space, and the tests assert it (`"1 234 567,89"`).
- **Money** is `Money.formatReadable` (`financial/Money.*`). It reuses the number text, then places the symbol with a second hand-written table of languages: prefix, prefix with space, suffix with space. **That space is U+0020 too**, so `"1 234,56 €"` has two break opportunities inside one amount.
- **The symbol is the currency's, not the locale's.** It comes from `Currency.symbol` in the types library, one per currency: CAD and USD are both `"$"`. A Canadian reader shown a US price, or an American shown a Canadian one, cannot tell them apart.
- **The minus sign is always the ASCII hyphen-minus** and goes before the symbol or on the number by a fixed rule.
- **No percent, no compact currency, no spelled-out currency names, no accounting form.** Compact numbers exist (`formatReadableAbbreviated`), but with SI prefixes in every locale: "1.5k" where CLDR writes "1.5K" in English, "1,5 mil" in Spanish, "1500" in German (which does not abbreviate thousands) and "150万" for 1.5 million in Japanese.
- **Phone numbers** (`:identifiers`, `formatPhone`) group with ASCII spaces and hyphens taken from the phone-number metadata — so they are breakable too.
- **The relative-time patterns already carry U+00A0** where CLDR has it, copied verbatim. The fault is specific to the two hand-built tables, not a policy.

## What CLDR does

Measured against Node 24.22.2, ICU 78.3, CLDR 48.0, `Intl.NumberFormat` on 1234567.89, −1234.50 in the local currency, and 25%. `⍽` is U+00A0, `‿` is U+202F, `␠` is U+0020:

| Locale | Number | Currency | Percent |
|---|---|---|---|
| fr | `1‿234‿567,89` | `-1‿234,50⍽€` | `25⍽%` |
| fr-CA | `1⍽234⍽567,89` | `-1⍽234,50⍽$` | `25⍽%` |
| fr-CH | `1'234'567,89` | `-1'234.50⍽CHF` | `25%` |
| de-CH | `1'234'567.89` | `CHF-1'234.50` | `25%` |
| pt-PT | `1⍽234⍽567,89` | `-1234,50⍽€` | `25%` |
| sv, nb | `1⍽234⍽567,89` | `−1⍽234,50⍽kr` (U+2212) | `25⍽%` |
| pl | `1⍽234⍽567,89` | `-1234,50⍽zł` | `25%` |
| ru | `1⍽234⍽567,89` | `-1⍽234,50⍽₽` | `25⍽%` |
| en-ZA | `1⍽234⍽567,89` | `-R⍽1⍽234,50` | `25%` |
| de | `1.234.567,89` | `-1.234,50⍽€` | `25⍽%` |
| nl | `1.234.567,89` | `€⍽-1.234,50` | `25%` |
| en-IN | `12,34,567.89` | `-₹1,234.50` | `25%` |

**No CLDR number or currency pattern contains a U+0020.** Every space inside a value is U+00A0 or U+202F, both of which Unicode line breaking (UAX #14) classes as glue: no break on either side. Points the table makes that the code does not:

- French in France groups with U+202F, the narrow no-break space; Canadian French with U+00A0. They are different characters, and the difference is deliberate in CLDR.
- Swedish and Norwegian write negatives with U+2212, the true minus sign.
- Symbol placement is per locale, not per language: `fr-CH` puts CHF after with a decimal point, `de-CH` puts it before with no space, `en-ZA` puts R before with a space, `nl` puts the sign between symbol and digits.
- The percent sign is spaced (non-breaking) in French, German, Swedish and Russian, and not in Polish, Italian or English.

The symbol is per locale as well:

| Locale | CAD | USD |
|---|---|---|
| en-US | `CA$5.00` | `$5.00` |
| en-CA | `$5.00` | `US$5.00` |
| fr-CA | `5,00⍽$` | `5,00⍽$⍽US` |
| fr | `5,00⍽$CA` | — |
| en-AU | — | `USD⍽5.00` |

CLDR also has a narrow symbol (`$` for both) for when the context already says which dollar.

## Options

**1. Leave the library alone; callers post-process.** What the prompt suggested. Rejected: every caller writes locale-specific regular expressions over finished text, which cannot tell a price from a sentence that happens to contain digits and spaces, misses every locale the author did not think of, and still leaves the separators the wrong characters for anyone who copies or compares the text. Repairing output downstream is the opposite of what a formatting library is for.

**2. Correct the two tables in place.** Change U+0020 to the CLDR character in `NumberStyle` and the money rules, add U+2212 where CLDR uses it, fix the `de-CH` and `en-ZA` placement. Small, and fixes the wrapping at once. But it leaves two hand-kept tables that already disagree with CLDR in ways nobody noticed, and nothing to keep them from drifting again.

**3. Generate number symbols and currency patterns from CLDR**, as the date-time and relative-time data already are: decimal and group symbols, minus and percent signs, minimum grouping digits, the standard, accounting and percent patterns, and per-locale currency symbols (standard and narrow) and display names. The hand tables go; the formatting engine (exact digits, Indian grouping, rounding) stays. The output then matches CLDR by construction, and a test can diff it against ICU the way the language data already is.

**4. Ship ASCII output as an explicit choice.** Some callers genuinely want breakable or plain-ASCII text: a CSV column, a log line, a terminal that renders U+202F badly, a test fixture. That is a parameter, not the default — something like a `separators = Typographic | Ascii` option — so the correct text is what a caller gets without asking.

**5. Where it lives.** Numbers and money are in `:readable` today, beside durations and relative times, and share its resource machinery. A separate `:numbers` module would make the subject visible and let a caller take numbers without the rest of `:readable`, at the cost of another artifact and moving public API (deprecation, not deletion). Undecided.

**6. Currency symbol ownership.** The types library's `Currency.symbol` is a fact about the currency, and one symbol cannot be right everywhere. Presentation belongs here: this library would pick the symbol by locale from CLDR and treat `Currency.symbol` as the fallback for a currency CLDR does not name. Nothing has to change in the types library for that; whether its single `symbol` should say "not locale-aware" in its own docs is for that library to decide.

**7. Phone numbers.** Their grouping comes from the phone-number metadata, not CLDR, so it needs its own answer: a no-break rendering (U+00A0 for spaces, U+2011 non-breaking hyphen for hyphens) as a notation or an option on `formatPhone`. A `tel:` link around it stays the caller's business.

## Findings

Measured (above, against the line given):

- The library groups and spaces with U+0020 where CLDR uses U+00A0 or U+202F, in numbers, in money, and in phone numbers.
- CLDR distinguishes `fr` (U+202F) from `fr-CA` (U+00A0); the library does not.
- Symbol placement for `de-CH`, `en-ZA` and others differs from CLDR.
- The library writes one symbol per currency; CLDR writes `CA$`, `US$`, `$CA` and `$ US` where the locale needs to disambiguate.
- Percent formatting does not exist.

Assumed, not verified:

- That Compose's text layout honours UAX #14 for U+00A0 and U+202F on every target, so correcting the characters is sufficient to stop the wrapping. Reasonable — both Android's and Skia's line breakers follow UAX #14 — but untested here.
- That U+202F renders acceptably in the fonts callers use. Some older fonts lack it and fall back; if that turns out to bite, it argues for option 4's choice, not for keeping U+0020.

## Leaning

Option 3, with option 4 as the escape hatch: generate the number and currency data from CLDR, keep the existing digit engine, make typographically correct separators the default and ASCII an explicit request. Fix the phone notation the same way (option 7). Take the currency symbol from CLDR by locale (option 6).

Option 2 is a reasonable first slice if the wrapping needs to stop before the generator exists — it changes the same output and nothing would have to be undone.

Either way this changes the bytes callers get for most non-English locales, so it is a minor release with a changelog entry that says so plainly: anyone comparing formatted text against a literal with an ordinary space will see their tests fail.

This is not a commitment. The module question and the scope below need deciding first.

## Open questions

- **Module.** Stay in `:readable`, or a new `:numbers` (with money), keeping deprecated forwarders in `:readable`?
- **Scope of the first release.** Separators and symbols surely; percent likely. Accounting form `(1 234,50 €)`, spelled-out currency names ("1 234,50 euros"), compact currency ("1,2 M€"), locale-aware compact numbers instead of SI prefixes, ordinals, other numbering systems (Arabic-Indic, Devanagari digits) — which are in?
- **Narrow or standard symbol by default.** CLDR's standard form disambiguates (`US$` in Canada); the narrow form reads cleaner where context makes it obvious. Which is the default, and is the other a parameter?
- **Indigenous-language locales.** `iu` currently follows English (`-CA$1,234.50` in CLDR). Do the curated languages need number data of their own, or does the RAD-0004 fallback cover them?
- **Date-time output.** CLDR puts U+202F before "a.m."/"p.m." in several English locales. Whether the date-time module already emits it was not checked here.
- **Parsing.** Duration parsing is planned; does number and currency parsing belong beside it, and must it accept both the typographic and ASCII spellings?
- **Phone.** A new `PhoneNotation`, or an option orthogonal to notation?
