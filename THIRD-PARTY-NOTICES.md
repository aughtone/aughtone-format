# Third-Party Notices

This library embeds localization reference data whose terms are independent of the Apache License, Version 2.0 that covers this library's own code. See [NOTICE.md](NOTICE.md) for what is embedded.

## Unicode CLDR

Values derived from the Unicode Common Locale Data Repository (CLDR), published by Unicode, Inc., are compiled into the published artifact in `List.resources.kt` (in the `:readable` module) — the locale list-join patterns.

Most localized time-zone names are read from the reader's own platform CLDR at runtime (`java.time`, Android ICU, Apple `NSTimeZone`, JavaScript/Wasm `Intl`) and are not compiled into this artifact.

Source: https://cldr.unicode.org/

CLDR is distributed under the Unicode License (https://www.unicode.org/license.txt). This library reproduces values derived from CLDR to provide localized formatting and claims no rights in the underlying data. Consult the Unicode License for its terms before redistributing the data itself.

## Inuktut Tusaalanga

The Inuktitut (`iu`) weekday names in `LocaleDayOfWeekNamesSource.kt` (in the `:datetime` module) are taken from the Inuktut Tusaalanga glossary, an Inuktut language resource.

Source: https://tusaalanga.ca/glossary

## First-party curated data — not from CLDR

Data authored for this project is maintained under this project's own copyright and is **not** extracted from CLDR or any other third-party source. This includes, for example, the relative-time and duration phrasings across supported locales, and the Inuktitut time-zone names in `TimeZoneNameSupplement.kt` (in the `:datetime` module) — CLDR carries no Inuktitut time-zone names, so these were curated for this project. It carries no third-party terms.
