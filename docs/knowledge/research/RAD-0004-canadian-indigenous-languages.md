# Canadian Indigenous Languages

RAD-0004 · 2026-11-07 · status: open
Keywords: submit to CLDR, language authority, Cree Ojibwe Inupiaq Halkomelem Squamish Saanich Kwak'wala Nuu-chah-nulth Haida Secwepemctsín Okanagan St'át'imcets Blackfoot Michif Chipewyan Tłı̨chǫ Gwich'in Mohawk Cayuga Oneida Wendat Innu-aimun Atikamekw Mi'kmaq Wolastoqey, three-letter language tags, hur-CA formats as English, no CLDR data, fallback language for a locale with no data, macrolanguage, syllabics or Latin, who reviews Indigenous-language text

## Question

The types library is adding locales for 25 Canadian Indigenous languages beside Inuktitut (aughtone/aughtone-types#44): Cree, Ojibwe and Inupiaq by their two-letter ISO 639-1 codes, and 22 more by their three-letter ISO 639-3 codes. Place names, heritage markers and localized string maps in Canada carry these tags. When the types library carries them, what should this library write for them, where can that text come from, and what must be decided language by language?

[RAD-0002](RAD-0002-indigenous-and-native-language-support.md) surveyed Indigenous languages in general and noted that Cree, Ojibwe and Innu-aimun need curated data. This record narrows that to the Canadian set the types library is adding, measures what happens today, and lists the decisions each language needs.

## What happens today

Measured on `develop`, 2026-11-07: a locale whose language this library has no data for resolves by its region. `hur-CA`, `cr-CA`, `mic-CA` and `atj-CA` all format as Canadian English — "Saturday, June 1, 2024", "4:05 p.m.", "2 hours", "Jun 1" — and a bare `hur` formats as American English. Nothing tells the caller a fallback happened. Only `iu` has its own text, curated and awaiting review (#21).

Re-measured 2026-11-09 against types 4.2.0, which carries all 25: the output is unchanged. `cre-CA` and `cwd-CA` (Woods Cree) resolve to `cr-CA` and `iku-CA` to `iu-CA`, while a member code the types library keeps separate, such as Plains Cree `crk-CA`, finds no locale, and neither does `iu-Latn-CA`.

That is safe, in that nothing throws, but it is a fallback by accident: an Atikamekw or Innu reader in Quebec gets English, where French is the language they would read beside their own.

## What CLDR has

Checked against Unicode CLDR 48 through Node's ICU. None of the 25 has formatting data: no date or time patterns, no month or weekday names, no relative-time or duration phrases, no number symbols. Only `iu` has plural rules (one, two, other). CLDR's likely subtags give each a default script, and it has an English name for 20 of them.

| Tag | Language | CLDR English name | Default script | Spoken in (Canada) | Macrolanguage |
|---|---|---|---|---|---|
| `iu` | Inuktitut | Inuktitut | Cans | NU, NT, QC, NL | yes: `ike`, `ikt` (types #40) |
| `cr` | Cree | Cree | Cans | QC, ON, MB, SK, AB | yes: 6 members |
| `oj` | Ojibwe | Ojibwa | Cans | ON, MB, SK | yes: 7 members |
| `ik` | Inupiaq | Inupiaq | Latn, region US | NT; also Alaska | yes: 2 members |
| `hur` | Halkomelem | Halkomelem | Latn | BC | |
| `squ` | Squamish | none | Latn | BC | |
| `str` | Straits Salish | Straits Salish | Latn | BC | |
| `kwk` | Kwak'wala | Kwakʼwala | Latn | BC | |
| `nuk` | Nuu-chah-nulth | none | Latn | BC | |
| `hai` | Haida | Haida | Latn | BC | yes: 2 members |
| `shs` | Secwepemctsín | none | Latn | BC | |
| `oka` | nsyilxcən | Okanagan | Latn | BC | |
| `lil` | St'át'imcets | Lillooet | Latn | BC | |
| `bla` | Blackfoot | Siksiká | Latn | AB | |
| `crg` | Michif | Michif | Latn | MB, SK, AB | |
| `chp` | Dene Suline | Chipewyan | Latn | SK, AB, MB, NT | |
| `dgr` | Tłı̨chǫ | Dogrib | Latn | NT | |
| `gwi` | Gwich'in | Gwichʼin | Latn | NT, YT | |
| `moh` | Mohawk | Mohawk | Latn | QC, ON | |
| `cay` | Cayuga | Cayuga | Latn | ON | |
| `one` | Oneida | none | Latn | ON | |
| `wya` | Wendat | none | none | QC | |
| `moe` | Innu-aimun | Innu-aimun | Latn | QC, NL | |
| `atj` | Atikamekw | Atikamekw | Latn | QC | |
| `mic` | Mi'kmaq | Mi'kmaw | Latn | NS, NB, PE, QC, NL | |
| `pqm` | Wolastoqey | Maliseet-Passamaquoddy | Latn | NB | |

The "spoken in" column is approximate and for choosing fallbacks only; it needs confirming for each language. Display names are the types library's concern; the five with no English name in CLDR are a gap there, not here.

## What a language needs here

Every piece of text and every convention the library uses, by module. #21's inventory lists the same for Inuktitut, item by item.

- **`:datetime`:** month and weekday names (full and abbreviated, stand-alone and inside a date); date and time patterns at four styles; the AM/PM marker and the clock convention; era names; interval and field patterns; default digits; time-zone names.
- **`:readable`:** plural rules; ordinals; duration and relative-time phrases; "Today", "Tomorrow", "Yesterday", "now", "Recently" and "Shortly"; list joining; number grouping and decimal symbols; unit names.

The structural parts — patterns, number symbols, the clock, digits — carry no words. The words are where every risk lies.

## Options

**A. Keep the region fallback.** No work, and the output is readable. But it is Canadian English for every language, including those whose readers live in French, and it never says so.

**B. A declared structural fallback per language.** Each language names the language whose patterns, symbols and clock it borrows — French (Canada) for languages spoken mainly in Quebec (`atj`, `moe`, `wya`, perhaps `moh`, `mic`, `cr`), English (Canada) for the rest — and its words fall back to that language too. Cheap, honest if documented, and right for structure. Still no text in the language itself.

**C. Curated text per language, reviewed by speakers.** The Inuktitut model (#21): the library's text written out, reviewed by people with the right knowledge of the language, corrected and attributed. The only route to text in the language. Slow, it depends on reaching reviewers, and for 25 languages it is a programme of work, not a release.

**D. B now, C as each language is reviewed.** Structure and the fallback words from the declared language from the start; each language's own words added only once reviewed, a piece at a time — weekday and month names first, then relative time and durations. A language can be partly curated: names reviewed, phrases still falling back.

**E. C, carried to CLDR with the language authority.** As C, with the review led by the language authority — the body or the people the language's community recognizes as speaking for it — and the reviewed data offered for submission to Unicode CLDR, with that authority asked to help submit it. CLDR is where this library's data for every other language comes from, and what it holds reaches ICU, Android, iOS and the browsers, so the language gains far more than this library's output. A submission needs a contributor committed to the language, which the authority is and this library is not. Once CLDR carries the data, the library imports it as it does every other language's, and only what CLDR does not hold — compass points, "Recently", "Shortly" — stays curated here. Slower again than C, and dependent on CLDR's submission cycle, but it is the only route by which the work outlives this library.

**Not an option: generating the words.** aughtone/aughtone-types#32 tested machine-generated text in the one language where an answer could be checked, and found it reliable only where it reproduced published data. For these languages there is no published data to reproduce, so a generated word is an invention that no test can catch, in a language the maintainers cannot read. An honest fallback is better than a plausible wrong word.

## Leaning

D, with B's fallback decided language by language and recorded, and nothing in a language's own words shipped before review — and each language's C done as E: the language authority consulted, named in the documentation as they agree, and asked to help submit the reviewed data to CLDR. It is not a decision yet: the open questions below decide whether it holds.

## Open questions

- **Fallbacks:** French (Canada) or English (Canada) for each language. The table's "spoken in" column is a starting point; for a language spoken on both sides of the provincial line (`mic`, `moh`, `cr`), which?
- **Macrolanguages:** `cr`, `oj`, `ik` and `hai` cover several varieties with different vocabulary. Whose words does `cr` mean: Plains, Swampy, Woods, Moose, or East Cree? The types library resolves member codes (types #44); this library inherits whatever it decides, and must not write one variety's words for another.
- **Inuvialuktun:** the request labels `ik` Inuvialuktun, but most Inuvialuktun dialects are coded `ikt`, which the types library resolves to `iu`. Settle it there first.
- **Scripts:** CLDR defaults `cr` and `oj` to syllabics and the rest to Latin. This library keys its data on language and region, not script, so `cr-Latn` and `cr-Cans` would get the same text. Is script-keyed data needed before Cree or Ojibwe text is curated? `iu-Latn` (types #41) asks the same for Inuktitut.
- **Plural rules:** CLDR has none for these languages. The library's fallback, one and other, counts the way English does; Algonquian languages mark animacy rather than number, and Inuit languages have a dual. Is one/other acceptable for counts, as RAD-0002 suggests?
- **Polysynthetic phrasing:** relative times and durations in several of these languages are single inflected words, not a number and a unit. Does the library's "{0} hours ago" template hold, as it does for Inuktitut's prepositional form, or does a language need its own phrase shapes?
- **Review:** who reviews each language, how corrections reach the library, and how reviewers are credited. #21 is the first attempt; its outcome should shape the rest.
- **Authority:** which body or people each language's community recognizes as its authority — one per language, or one per variety or nation where a language spans several — and how they want to be named in the documentation.
- **CLDR submission:** CLDR asks a new language to reach its Basic coverage level, and data is entered through its Survey Tool during an open submission period; confirm the current process and what Basic requires before asking an authority to take it on. Which of the library's terms map to CLDR data, and which stay here only.
- **Worksheet:** `LanguageWorksheetTest` writes one worksheet per language to `docs/outbox/worksheets/` on every JVM test run: 141 terms, each in English and French (Canada) with a blank column for the language and whether CLDR holds it (131 do), and lines for the authority, its credit, the variety and writing system, and its agreement to help submit to CLDR. Open: whether 1 and 3 are enough count examples for a language with a dual or other counted forms, or the worksheet should ask for each form by name.
- **Requested autonyms:** the names in types #44 came with the request and are unverified. They belong to the types library's display names, and need the same review.
- **Reporting:** how does a caller learn that a language's text is falling back? A coverage table in the skills, a function that reports it, or both?
