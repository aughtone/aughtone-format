# Formatting API naming

ADR-0001 · 2026-05-30 · Status: accepted
Keywords: toReadable or formatReadable, what to call a formatting function, toReadableString
          deprecated, readableRelative renamed, Kotlin to-prefix means conversion

## Context

Formatting extension functions used a mix of `toReadable*`, `readable*` and other names. In Kotlin a `to` prefix conventionally means a conversion (`toInt()`, `toInstant()`), which is not what formatting a value for a person to read does.

## Decision

- Every formatting API in `:readable` is named `formatReadable*`: `formatReadable`, `formatReadableOrdinal`, `formatReadableRelative`, `formatReadableDataSize`, `formatReadableDuration`.
- The older `toReadable*` functions are deprecated, kept as stubs with `@Deprecated(message, replaceWith)` so an IDE migrates callers automatically.

## Consequences

Callers get one recognisable name for every formatter, and existing code keeps compiling with a warning and a quick-fix. The deprecated stubs remain until a major release removes them.
