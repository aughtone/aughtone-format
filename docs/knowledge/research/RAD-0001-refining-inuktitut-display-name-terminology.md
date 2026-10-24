# Refining Inuktitut Display-Name Terminology

RAD-0001 · 2026-09-28, revised 2026-09-30
Keywords: Inuktitut weekday names, Inuktut month names, iu display names read poorly,
          CLDR has no Inuktitut calendar data, machine-generated locale strings,
          aippiq pingatsiq sitammiq, day being the second, Tusaalanga glossary,
          South Qikiqtaaluk dialect, which Inuktut dialect to bundle, abbreviated
          weekday forms Inuktitut, published source versus generated text
Compared against: the `iu` weekday and month names bundled in
          `LocaleDayOfWeekNamesSource`/`LocaleMonthNamesSource`; the Unicode CLDR
          `iu` calendar and time-zone data (cldr-json, `cldr-dates-full/main/iu`);
          and the Inuktut Tusaalanga educational glossary
          (https://tusaalanga.ca/glossary, South Qikiqtaaluk dialect), 2026-09-28/30.

## Question

The library ships Inuktitut (`iu`) weekday and month names. Its weekday names were descriptive phrases — "the day being the Nth" — rather than the everyday Inuktut day-word a reader would expect. A community educational source (the Inuktut Tusaalanga glossary, produced for Nunavut language learners) carries the idiomatic day-words and its own month spellings.

This was first framed as "should the library diverge from CLDR toward a community source?". That framing was wrong, and correcting it is the main finding here: **CLDR has no Inuktitut calendar data to diverge from.** The real question is **how the library should choose, record, and verify locale data for a language CLDR does not cover** — which source, which dialect, what provenance, and how far the policy generalizes.

## Trail

### CLDR has no Inuktitut calendar or time-zone names

CLDR's `iu` calendar data is the root fallback only: weekdays are the placeholders `Mon` … `Sun` and months `M01` … `M12`, in every width and context. Its `iu` time-zone data has no metazone names at all. This is also why no platform localizes Inuktitut: `java.time`, Android ICU, Apple Foundation and the browser's `Intl` all draw on CLDR, and CLDR has nothing to give them.

So every Inuktitut name the library bundles is the project's own data. The project's gap record says so directly: its locale strings were machine-generated and not native-speaker verified, and some Inuktitut time-zone entries have shipped with Latin and katakana characters mixed into the syllabics. The old weekday phrases came from that same process.

### The previously bundled forms versus the community forms

Weekdays (full form) — the gap is large:

| day | previously bundled (machine-generated) | Tusaalanga (South Qikiqtaaluk) |
|---|---|---|
| Monday | ᐅᓪᓗᓂ ᐊᑕᐅᓯᕐᒥᐅᑕᐅᕙᑦᑐᓂ | ᓇᒡᒐᔾᔭᐅ (naggajjau) |
| Tuesday | ᐅᓪᓗᓂ ᒪᕐᕉᖕᓂᐅᑕᐅᕙᑦᑐᓂ | ᐊᐃᑉᐱᖅ (aippiq) |
| Wednesday | ᐅᓪᓗᓂ ᐱᖓᓱᓂᐅᑕᐅᕙᑦᑐᓂ | ᐱᖓᑦᓯᖅ (pingatsiq) |
| Thursday | ᐅᓪᓗᓂ ᓯᑕᒪᓂᐅᑕᐅᕙᑦᑐᓂ | ᓯᑕᒻᒥᖅ (sitammiq) |
| Friday | ᐅᓪᓗᓂ ᑕᓪᓕᒪᓂᐅᑕᐅᕙᑦᑐᓂ | ᑕᓪᓕᕐᒥᖅ (tallirmiq) |
| Saturday | ᐅᓪᓗᓂ ᖄᕐᓂᐅᑕᐅᕙᑦᑐᓂ | ᓯᕙᑖᕐᕕᒃ (sivataarvik) |
| Sunday | ᐅᓪᓗᓂ ᐅᓪᓗᖓᑕ ᐱᒋᐊᕐᕕᖓ | ᓈᑦᓰᖑᔭᖅ (naatsiingujaq) |

The community weekdays are built on the same number roots the old phrases spell out (pingatsiq from *pingasut*, three; sitammiq from *sitamat*, four; tallirmiq from *tallimat*, five), so the two agree on meaning: one is the word people use, the other a literal gloss.

Months (full form) — mostly agreement, a few differences:

| month | previously bundled | Tusaalanga | note |
|---|---|---|---|
| January | ᔮᓄᐊᓕ | ᔮᓐᓄᐊᕆ | final r↔l |
| February | ᕖᕝᕗᐊᓕ | ᕖᕝᕗᐊᕆ | final r↔l |
| April | ᐄᐳᓗ | ᐄᐳᕆ | r↔l |
| August | ᐋᒐᓯ | ᐋᒡᒌᓯ | differs |
| Mar, May, Jun, Jul, Sep, Oct, Nov, Dec | — | — | already identical |

### A published source beats unverified generated text

Because there is no CLDR baseline, the comparison is not "standard versus community" but "unverified generated text versus a published, human-authored source". On that comparison the glossary is the better default even before a fluent reader reviews it: its forms are what learners are taught, and its provenance can be named.

### The dialect dimension is still the hard part

Inuktut is not one standardized written language; it spans a dialect continuum across the Arctic with real differences in vocabulary and orthography. The Tusaalanga glossary is explicitly regional — it lets the reader choose among South Qikiqtaaluk (South Baffin) communities, and the forms captured here are from that setting. The `r`↔`l` month differences are exactly the kind of variation dialect selection turns on.

With no neutral standard to fall back on, any bundled form is some dialect's form, whether chosen deliberately or not. The generated phrases were not dialect-neutral; they were simply unattributed. Choosing a named dialect and recording it is the honest version of the same decision.

### Abbreviations have no community source

The glossary carries full forms only. The bundled abbreviations are the earlier generated, number-based forms (ᒪᕐᕉ "two", ᐱᖓ "three", …), which pair with the old descriptive full names but not with the idiomatic ones. Deriving abbreviations from the idiomatic words (a leading syllable or two) is a linguistic judgment that wants a fluent reader, not a mechanical rule.

### Interim step already taken

The weekday **full** names were switched to the glossary day-words and ship in 4.1.0 with attribution. The weekday **abbreviations** and all **month** names are still the earlier generated forms. That is a pragmatic patch of the most visible problem, not the considered decision this RAD exists to reach: it currently mixes registers (an idiomatic full name beside a number-based abbreviation) and applies one dialect to weekdays only.

## Recommendation

Treat the current weekday change as interim and refine `iu` display names as one deliberate piece of work, ideally validated by a fluent Inuktut reader or an authoritative Nunavut language body rather than a single learner-facing glossary:

1. **Decide a dialect policy for `iu`.** Commit to one named dialect (and say which, and why), or ship per-dialect variants if the locale model can carry them. Record the choice so later edits are consistent.
2. **Resolve months and abbreviations together with weekdays**, so full and abbreviated forms share a register and the whole locale is internally consistent.
3. **Record provenance in-code** — which source and dialect each `iu` set comes from, and whether it has been verified — so generated and sourced data can be told apart (the weekday source comment is a start).
4. **Review the other bundled Inuktitut data by the same standard.** The Inuktitut time-zone names are generated too, with known script-mixing errors; they deserve the same source-and-verify pass.
5. **Decide whether this generalizes.** "Where CLDR has no data, bundle only sourced and attributed forms, never unverified generated text" is a policy that could apply to every under-served language the library carries. If it is worth stating once, state it rather than re-deciding per language. This is the open question with the widest blast radius and should not be settled by the `iu` case alone.
6. **Confirm the source's redistribution terms.** The glossary publishes no licence or attribution terms; the weekday forms shipped in 4.1.0 with attribution only, and confirming permitted reuse with the publisher is still open. Contributing verified forms to CLDR itself would settle provenance for good, and would reach every platform rather than only this library.

Until that work happens, no further piecemeal edits to `iu` terms: the value of doing it once, consistently, and with a qualified reader outweighs shipping another partial improvement.
