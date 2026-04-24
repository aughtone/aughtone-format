# Developer Guide

## AI Contribution Workflow

This project utilizes AI agents for development. To maintain architectural integrity and user control, all agents must follow this workflow:

1.  **Analyze**: Understand the request and context.
2.  **Plan**: Draft a step-by-step implementation plan.
3.  **Wait**: Present the plan to the user and stop execution.
4.  **Execute**: Only after receiving explicit approval, perform the proposed changes.
5.  **Verify**: Ensure changes meet the Acceptance Criteria (ACs).

## 🛠️ Adding New Formatting Rules
When adding support for a new language or formatting feature:

1.  **Functional Pattern**: All formatters must return a `Formatter<T>` (a lambda).
2.  **Resource Map**: Add the rule to the corresponding `.resources.kt` map.
    - **Cache Maintenance**: In `:datetime`, ensure new resource lookups utilize the thread-safe `cachedTextResource` or `cachedEraNamesResource` utilities in `Resources.kt`.
3.  **Linguistic Grouping**: When adding similar languages, use grouping logic (e.g., `listOf("da", "nb").forEach { put(it, sc) }`) to share formatters and keep the codebase lightweight.
4.  **Use BCP 47 Tags**: Always use standard language tags (e.g., `fr`, `en-GB`) as map keys.
5.  **Tests**: Add a test case in `commonTest` (e.g., `FeatureTest.kt`) to verify the new rules, era overrides, or numbering systems across all platforms.

## ⚖️ Implementing Grammatical Plurals
The `:readable` module uses a custom pluralization system to ensure grammatical parity across 55+ languages.
1. **Classification**: Use `pluralCategoryFor(locale, n)` for standard plurals and `ordinalCategoryFor(locale, n)` for ordinals.
2. **Factories**: Use the standard factories defined in `PluralCategory.kt` to build localized unit maps:
   - `u1`: 1 form (e.g., Chinese, Japanese - uninflected).
   - `u2`: 2 forms (e.g., English, Germanic - singular/plural).
   - `u3`: 3 forms (e.g., Slavic - singular/paucal/plural).
   - `u4`: 4 forms (e.g., Hebrew, Inuktitut).
   - `u6`: 6 forms (e.g., Arabic).
3. **Strings**: Pass the grammatical forms in the specific order defined by the factory (e.g., `singular, plural` for `u2`).

## 📅 Datetime Module Conventions
- **24-Hour Master Patterns**: All time patterns in `LocaleTimePatterns.kt` MUST be specified in 24-hour format (`HH:mm` or `HH:mm:ss`). The runtime formatting logic handles conversion to 12-hour format automatically. NEVER add 12-hour patterns to the master map.
- **Clock Hours Exceptions**: If a locale uses a 12-hour clock by default, add its region code or language code to `LocaleClockHoursSource.kt`. If omitted, it defaults to 24-hour.
- **AM/PM Markers**: Ensure `LocaleAmPmStrings.kt` contains markers for all **55 supported languages**, even if they primarily use 24-hour time, to support user-forced 12-hour formatting.

## 🚀 Publishing to Maven Central

### Release Steps
1.  **Version Update**: Increment the version in `gradle/libs.versions.toml`.
2.  **Clean Build**: Run `./gradlew clean build` to ensure all targets compile.
3.  **Publish**:
    ```bash
    ./gradlew publishAllPublicationsToMavenCentralRepository
    ```
    *Note: Use `-Pskip-signing` for local testing.*

### Multi-Module Publishing
The library consists of multiple artifacts. The `publish` command will automatically handle all enabled modules:
- `:datetime`
- `:readable`
- `:toolbox`
- `:datetime-native` (when enabled)
