# NOTICE

Aughtone Format
Copyright 2025-2026 The Aught One Authors

This product is licensed under the Apache License, Version 2.0. A copy of that license is included in the [LICENSE](LICENSE) file at the root of this repository, and is also available at http://www.apache.org/licenses/LICENSE-2.0

## Embedded third-party data

This library compiles localization reference data into its published artifact. That data carries the terms of its own source, which are not the Apache License covering this library's own code. Provenance is recorded in [THIRD-PARTY-NOTICES.md](THIRD-PARTY-NOTICES.md).

- **Unicode CLDR** — the list-join patterns (`List.resources.kt` in the `:readable` module) are derived from the Unicode Common Locale Data Repository (CLDR), published by Unicode, Inc. Most localized time-zone names are now read from the reader's own platform CLDR at runtime and are **not** compiled into this artifact. See [THIRD-PARTY-NOTICES.md](THIRD-PARTY-NOTICES.md).
- **Inuktut Tusaalanga** — the Inuktitut (`iu`) weekday names (`LocaleDayOfWeekNamesSource.kt` in the `:datetime` module) are taken from the Inuktut Tusaalanga glossary, an Inuktut language resource. See [THIRD-PARTY-NOTICES.md](THIRD-PARTY-NOTICES.md).

First-party curated data — for example the relative-time and duration phrasings, and the Inuktitut time-zone names in `TimeZoneNameSupplement.kt` (CLDR has no Inuktitut zone names) — is authored under this project's own copyright above and carries no third-party terms.

## No endorsement

The organizations named above do not endorse this library, and no endorsement is implied. Their names are used only to identify the origin of the embedded data.
