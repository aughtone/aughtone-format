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
3.  **Linguistic Grouping**: When adding similar languages, use grouping logic (e.g., `listOf("da", "nb").forEach { put(it, sc) }`) to share formatters and keep the codebase lightweight.
4.  **Use BCP 47 Tags**: Always use standard language tags (e.g., `fr`, `en-GB`) as map keys.
5.  **Tests**: Add a test case in `commonTest` to verify the new rules across all platforms (JVM, JS, WASM, iOS).

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
