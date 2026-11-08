# Canadian Indigenous Language Support

PRD-0001 · 2026-11-07
Keywords: format dates and durations in Cree, Ojibwe, Mi'kmaq, Mohawk, Halkomelem and other Canadian Indigenous languages, three-letter language tags, locale with no data falls back to English, fallback to French for Quebec languages, speaker-reviewed text, which languages are curated

## Problem

The types library is adding locales for 25 Canadian Indigenous languages beside Inuktitut (aughtone/aughtone-types#44). Place names, heritage markers and localized string maps in Canada carry their tags, and an application showing them formats dates, times, durations and relative times beside them. Today this library writes Canadian English for every one of them, by accident of its region lookup, and says nothing ([RAD-0004](../research/RAD-0004-canadian-indigenous-languages.md)). None has data in Unicode CLDR, so there is no published text to generate from: anything in a language's own words has to be written and reviewed by people who know it.

## Who needs it

- An application that shows Canadian place and heritage content in the language of its name, and dates or durations beside it.
- A reader of one of these languages, who should see text in their language where it exists, and otherwise a deliberate, documented fallback — French where they would read French, English where they would read English — never an unannounced default.
- The maintainers, who cannot read these languages and need a way to accept text from those who can.

## Requirements

1. **Every tag formats.** Each of the 26 languages, by its tag and with `-CA` (and `ik-US`), formats in every `:datetime` and `:readable` API without throwing, as soon as the types library carries it.
2. **Fallback by decision, not by accident.** Each language has a declared fallback — French (Canada) or English (Canada) — for everything it has no reviewed text of its own for, decided per language and recorded.
3. **A language's own words only after review.** Text in a language ships only once reviewed by people with the right knowledge of it, with the reviewers credited as they ask. Nothing is machine-generated. A language may be partly curated — its month and weekday names reviewed, its phrases still falling back.
4. **One language, one variety.** Where a tag is a macrolanguage (`cr`, `oj`, `ik`, `hai`, `iu`), the text is one named variety's, recorded, and no other variety reads it as its own.
5. **Normalization follows the types library.** A language with a two-letter code is keyed by it, and its three-letter codes reach the same data; a language with only a three-letter code keeps it.
6. **Coverage is visible.** A caller can see, for each language, what is the language's own text and what falls back: at least in the skills, with the decision recorded on whether an API reports it too.
7. **Tested per language.** Each language has tests for its dates, times, durations, relative times and field formatting, pinning its own text and its fallback.

## Out of scope

Display names (a language's or a territory's name in another language) are the types library's. Script-keyed data (`cr-Latn` beside `cr-Cans`) is an open question in RAD-0004 and becomes a requirement only if it is decided to be one. Plural rules beyond one and other wait on RAD-0004's question.

## Stories

The stories are sub-issues of #31, which covers Indigenous and native languages generally. Inuktitut's story is #21, which this set consolidates.

| Language | Tag | Story |
|---|---|---|
| Inuktitut | `iu` | #21 |
| Cree | `cr` | #40 |
| Ojibwe (Anishinaabemowin) | `oj` | #41 |
| Inupiaq | `ik` | #42 |
| Halkomelem | `hur` | #43 |
| Squamish | `squ` | #44 |
| Straits Salish (SENĆOŦEN) | `str` | #45 |
| Kwak'wala | `kwk` | #46 |
| Nuu-chah-nulth | `nuk` | #47 |
| Haida | `hai` | #48 |
| Secwepemctsín (Shuswap) | `shs` | #49 |
| nsyilxcən (Okanagan) | `oka` | #50 |
| St'át'imcets (Lillooet) | `lil` | #51 |
| Blackfoot (Siksiká) | `bla` | #52 |
| Michif | `crg` | #53 |
| Dene Suline (Chipewyan) | `chp` | #54 |
| Tłı̨chǫ (Dogrib) | `dgr` | #55 |
| Gwich'in | `gwi` | #56 |
| Mohawk (Kanien'kéha) | `moh` | #57 |
| Cayuga | `cay` | #58 |
| Oneida | `one` | #59 |
| Wendat (Wyandot) | `wya` | #60 |
| Innu-aimun | `moe` | #61 |
| Atikamekw | `atj` | #62 |
| Mi'kmaq | `mic` | #63 |
| Wolastoqey (Maliseet-Passamaquoddy) | `pqm` | #64 |
