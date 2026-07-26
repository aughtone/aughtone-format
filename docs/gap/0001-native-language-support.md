# 0001 — Indigenous & Native Language Support

- **Type:** Spike / exploration
- **Status:** Not started
- **Created:** 2026-07-25

## Goal

Explore how `aughtone-format` could expand beyond its current supported set to more
indigenous and native languages. OS-level *display* of these scripts is easy; the real
bottleneck is **localization data** (plural rules, date/time patterns, relative-time
formats, number symbols, currency layout) — i.e. how well each language is covered by the
Unicode **CLDR**.

> ⚠️ **Provenance / verification (read first).** The library's existing locale strings are
> machine/AI-generated and **not native-speaker verified**. This already surfaced concrete
> unreliability: four Inuktitut (`iu`) time-zone entries shipped with Latin / katakana
> characters leaking into the syllabics, and earlier Slovak/Latvian entries were garbled.
> **Any Indigenous-language content must be verified by native speakers / a community
> authority before being presented as authoritative.** For Indigenous languages of British
> Columbia, the [First Peoples' Cultural Council (FPCC)](https://fpcc.ca/) is the relevant
> authority and funds community-led language work; CLDR is the reference for the broader set.
> Treat everything below as a starting hypothesis to validate, not fact.

## Tier 1 — Languages with mature CLDR data (best candidates)

Complete/mature CLDR: plural rules, date/time patterns, relative time, number symbols,
currency layout can be sourced directly.

| Language | Tag | Notes |
|---|---|---|
| Cherokee | `chr` | Excellent CLDR coverage; own syllabary script (`Cher`). |
| Māori | `mi` | Full CLDR; standard 2-form plural (one/other); standard date/time. |
| Hawaiian | `haw` | Solid CLDR; Latin script with macron (kahakō) + ʻokina (glottal stop). |
| Navajo | `nv` | Basic-to-moderate CLDR for date/time and number formatting. |
| Samoan / Tongan | `sm` / `to` | Good baseline CLDR for core locale operations. |

## Tier 2 — Manual data curation required (like `iu`)

Unicode script support exists (characters display), but CLDR formatting data is sparse or
missing — resources must be curated manually, as was done for Inuktitut.

| Language | Tag | Notes |
|---|---|---|
| Cree | `cr` | Canadian Aboriginal Syllabics (`Cans`); Plains/Swampy/Woods Cree. Complex animacy-based plurals → fall back to `one/other`. |
| Ojibwe / Anishinaabemowin | `oj` | Syllabics (`Cans`) or Latin double-vowel orthography. |
| Innu-aimun | `moe` | Algonquian family; Latin orthography. |
| Greenlandic / Kalaallisut | `kl` | Fully Latin-based, closely related to Inuktitut; polysynthetic — relative time often a single complex verb form rather than separate words. |

## Key engineering considerations

**A. Complex plural schemes (animacy vs. count).** Standard CLDR plural categories are
count-based (`one/two/few/many/other`). Cree/Ojibwe categorize by *animacy* (grammatically
"alive" vs. inanimate) rather than count. **Recommendation:** for a string/datetime formatter,
stick to the standard `one/other` fallback for quantitative counts — modelling animacy in
string templates gets messy fast.

**B. Script variants & subtags.** Many of these languages switch between native scripts and
Latin orthographies by region. The locale parser must handle explicit **script subtags**:

- `cr-Cans` (Cree syllabics) vs. `cr-Latn` (Cree Latin)
- `iu-Cans` (Inuktitut syllabics) vs. `iu-Latn` (Inuktitut Latin / Qaliujaaqpait)

*(Today's resolvers key mostly on language code, occasionally on region — script-subtag
resolution is a prerequisite to do this properly.)*

**C. Formatting & morphological fallbacks.** Inuktitut and Greenlandic are polysynthetic, so
static templates like `"{0} days ago"` can read awkwardly when time units merge into suffix
verbs. Accepted digital-UI practice is to use simple prepositional forms (e.g. `{0} ᒥᓂᑦ`),
which is the approach the library already takes.

## Open questions for the spike

- Which family to target first (Polynesian `mi`/`haw` via CLDR is lowest-effort; Algonquian
  `cr`/`oj` is highest-value-but-manual)?
- Add **script-subtag resolution** (`xx-Cans` / `xx-Latn`) to the locale lookup chain — how
  invasive across the `Locale*Source` maps and `relativeTimeConfigFor` / duration resolvers?
- Verification workflow: how do we source and confirm strings with native speakers /
  community bodies (e.g. FPCC) before shipping? What provenance metadata do we record?
- Does the "one/other" plural fallback produce acceptable output for the manual-curation set?

---

*Primer contributed by Brill (2026-07-25). Coverage claims above should be re-checked against
the current CLDR release as the first step of the spike.*
