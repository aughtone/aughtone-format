# Currency is never null in money formatting

ADR-0002 · 2026-05-30 · Status: accepted
Keywords: Money without a currency, null currency, nullable Currency in Money, aughtone-types 3.0.0,
          testNullCurrencyFallback removed, minor-unit digits

## Context

From `aughtone-types` 3.0.0 the `Currency` inside `Money` is non-nullable.

## Decision

- Money formatting in `:readable` assumes a currency is always present, and the null checks (`currency?.digits`, `currency != null`) are gone.
- The test for a null-currency fallback is removed, because the case can no longer be written.

## Consequences

The formatter always knows how many minor-unit digits to show. Code that built a `Money` without a currency no longer compiles, which is the point.
