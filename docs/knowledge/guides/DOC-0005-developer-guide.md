# Developer Guide

DOC-0005 · 2026-04-19
Keywords: how to add a language, adding a formatting rule, plural factories u2 u3 u4 u6,
          24-hour master patterns, publishing to Maven Central, release steps

## AI Contribution Workflow

This project utilizes AI agents for development. To maintain architectural integrity and user control, all agents must follow this workflow:

1.  **Analyze**: Understand the request and context.
2.  **Plan**: Draft a step-by-step implementation plan.
3.  **Wait**: Present the plan to the user and stop execution.
4.  **Execute**: Only after receiving explicit approval, perform the proposed changes.
5.  **Verify**: Ensure changes meet the story's acceptance criteria, on its tracker issue.

## 🛠️ Adding New Formatting Rules
When adding support for a new language or formatting feature:

1.  **Functional Pattern**: All formatters must return a `Formatter<T>` (a lambda).
2.  **Resource Map**: Add the rule to the corresponding `.resources.kt` map.
    - **Cache Maintenance**: In `:datetime`, look resources up through `Resources.kt`, which caches each locale's data on first use.
3.  **Linguistic Grouping**: When adding similar languages, use grouping logic (e.g., `listOf("da", "nb").forEach { put(it, sc) }`) to share formatters and keep the codebase lightweight.
4.  **Use BCP 47 Tags**: Always use standard language tags (e.g., `fr`, `en-GB`) as map keys.
5.  **Tests**: Add a test case in `commonTest` (e.g., `FeatureTest.kt`) to verify the new rules, era overrides, or numbering systems across all platforms.

## ⚖️ Implementing Grammatical Plurals
The `:readable` module uses a custom pluralization system to ensure grammatical parity across 55+ languages.
1. **Classification**: Use `pluralCategoryFor(locale, n)` for standard plurals and `ordinalCategoryFor(locale, n)` for ordinals.
2. **Factories**: Use the standard factories defined in `PluralCategory.kt` to build localized unit maps:
   - `u2`: 2 forms (e.g., English, Germanic - singular/plural; also uninflected languages such as Chinese and Japanese, with the same word twice).
   - `u3`: 3 forms (e.g., Slavic - singular/paucal/plural).
   - `u4`: 4 forms (e.g., Hebrew, Inuktitut).
   - `u6`: 6 forms (e.g., Arabic).
3. **Strings**: Pass the grammatical forms in the specific order defined by the factory (e.g., `singular, plural` for `u2`).

## 📅 Datetime Module Conventions
- **24-Hour Master Patterns**: All time patterns in `LocaleTimePatterns.kt` MUST be specified in 24-hour format (`HH:mm` or `HH:mm:ss`). The runtime formatting logic handles conversion to 12-hour format automatically. NEVER add 12-hour patterns to the master map.
- **Clock Hours Exceptions**: If a locale uses a 12-hour clock by default, add its region code or language code to `LocaleClockHoursSource.kt`. If omitted, it defaults to 24-hour.
- **AM/PM Markers**: Ensure `LocaleAmPmStrings.kt` contains markers for all **55 supported languages**, even if they primarily use 24-hour time, to support user-forced 12-hour formatting.
- **Time-zone names** come from the platform's own CLDR at runtime (see [ADR-0005](../decisions/ADR-0005-time-zone-names-from-the-platform.md)). Only languages no platform ships, chiefly Inuktitut, are bundled, in `TimeZoneNameSupplement.kt`.
- **Generated data**: CLDR-derived tables are written by the scripts in `tools/cldr/` and say so at the top. Change the script and run it again; never edit a generated file by hand.

## 🚀 Publishing to Maven Central

### Release Steps
1.  **Release branch**: Cut a release branch from `develop` and set the version in `gradle/libs.versions.toml`.
2.  **Check**: Run `./gradlew check` so every target compiles and passes.
3.  **Merge to `main`**: CI publishes to Maven Central when `main` is pushed. Releases are not published from a local machine.

To test a publication locally, publish to Maven Local with signing skipped:

```bash
./gradlew publishToMavenLocal -Pskip-signing
```

### Multi-Module Publishing
The library consists of multiple artifacts. The `publish` command will automatically handle all enabled modules:
- `:datetime`
- `:readable`
- `:identifiers`
- `:toolbox`
- `:viewable`
- `:viewable-compose`
