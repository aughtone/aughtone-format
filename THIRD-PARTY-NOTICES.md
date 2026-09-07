# Third-Party Notices

This library embeds localization reference data whose terms are independent of the Apache License, Version 2.0 that covers this library's own code. See [NOTICE.md](NOTICE.md) for what is embedded.

## Unicode CLDR

Localized time-zone names and abbreviations compiled into `TimeZoneNamesLookup.kt` (in the `:datetime` module) are derived from the Unicode Common Locale Data Repository (CLDR), published by Unicode, Inc.

Source: https://cldr.unicode.org/

CLDR is distributed under the Unicode License (https://www.unicode.org/license.txt). This library reproduces values derived from CLDR to provide localized formatting and claims no rights in the underlying data. Consult the Unicode License for its terms before redistributing the data itself.

## First-party curated data — not from CLDR

Data authored for this project is maintained under this project's own copyright and is **not** extracted from CLDR or any other third-party source. This includes, for example, the relative-time and duration phrasings across supported locales. It carries no third-party terms.
