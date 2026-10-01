---
name: io-github-aughtone-format-toolbox
description: >-
  The low-level helpers of the aughtone-format family, for Kotlin Multiplatform
  common code where String.format and java.text are missing. Round a Double to a
  few decimal places as a string; fill numbered placeholders %1, %2 in a template
  string, a minimal multiplatform String.format substitute; mask all but the last
  few characters of any string, such as an account or phone number (•••• 4567);
  compute the bounding box (bbox, extent) of GeoJSON geometry and merge boxes;
  project longitude/latitude to x/y screen or SVG coordinates with a Web Mercator
  or equirectangular projection, or your own. Not a locale number formatter (no
  thousands separators, currency, or padding to fixed decimals; use
  io.github.aughtone:format-readable), not printf or ICU message formatting, and
  not a GIS library (no CRS transforms or geodesic distance). To draw GeoJSON as
  SVG, WKT or an image use io.github.aughtone:format-viewable; to mask a card
  number with issuer grouping use io.github.aughtone:format-identifiers.
license: Apache-2.0
metadata:
  version: "4.1.0"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-toolbox

## What it solves

The low-level pieces the other `aughtone-format` modules (and their callers) reuse:

- Round a `Double` to a few decimals as a plain string — `1234.5678.format(2)` → `"1234.57"`.
- Fill numbered placeholders in a template string — `"hello %1".format("world")` → `"hello world"`. This is the multiplatform stand-in for the `String.format(...)` that Kotlin common does not have.
- Mask the trailing characters of a string for display — `"…123-4567".obfuscateLast(digitsOnly = true)` → `"•••••••4567"`.
- Compute the bounding box of a GeoJSON geometry or feature, and merge boxes — `geometry.calculateBoundingBox()`, `boxA.merge(boxB)`.
- Turn longitude/latitude into projected screen/SVG coordinates through the `GeoProjection` functional interface, with `WebMercatorProjection` and `EquirectangularProjection` provided.

It is a grab-bag of primitives, not a framework: reach here before writing decimal rounding, last-N masking, or projection/bbox arithmetic by hand.

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts (common, JVM, Android, and iOS via Kotlin); there is no separate Swift or JavaScript API.

## How it is meant to be used

```kotlin
import io.github.aughtone.toolbox.format
import io.github.aughtone.toolbox.obfuscateLast
import io.github.aughtone.toolbox.geo.calculateBoundingBox
import io.github.aughtone.toolbox.geo.WebMercatorProjection

1234.5678.format(2)                       // "1234.57"
"4111111111111111".obfuscateLast()        // "••••••••••••1111"  (default count = 4)

val box = polygon.calculateBoundingBox()  // GeoBoundingBox?  — may be null
val (x, y) = WebMercatorProjection.project(lon, lat)

// GeoProjection is a fun interface — pass a lambda where you want your own:
val identity = GeoProjection { lon, lat -> lon to lat }
```

`String.format(vararg args)` needs an opt-in: it is annotated `@ExperimentalMultiplatform`, so the call site needs `@OptIn(ExperimentalMultiplatform::class)`.

## Invariants and traps

**`calculateBoundingBox()` returns `GeoBoundingBox?` — nullable.** It is `null` for an empty feature collection, an empty geometry collection, or coordinates that never yield two usable numbers. Symptom: a `NullPointerException` at the call site, or an empty/defaulted box downstream — always null-check; do not assume a box comes back.

**`Double.format` is a rounding helper, not a locale formatter, and it does not pad.** `precision <= 0` returns an integer string (no decimal point at all), and a trailing `.0` is stripped — so `2.0.format(2)` yields `"2"`, **not** `"2.00"`. Symptom: fewer decimals than you asked for, or a whole number where you expected `"x.00"`. There are no thousands separators and no locale awareness; for grouped, localized, or currency numbers use `io.github.aughtone:format-readable` or a platform API.

**`String.format` is a literal placeholder filler, not `printf`/ICU.** Placeholders are `%1`, `%2`, … (1-based) and are substituted by simple replacement. Symptom: a raw `%3` left in the output means a *missing* argument; an extra argument vanishes silently; and because `%1` is replaced before `%10`, two-digit indexes past `%9` mis-substitute. Keep templates to `%1`–`%9`.

**`obfuscateLast` has two quiet surprises.** When the string is no longer than `count` it returns the **original unchanged** — symptom: a short value comes back with nothing masked. With `digitsOnly = true` it keeps only the digits and drops every other character, so `"+1 (555) 123-4567"` becomes `"•••••••4567"` — symptom: the original punctuation and spacing are gone, not preserved.

**Projections flip the Y axis and are screen-space, not geodesic.** Both projections return Y increasing *downward* (SVG/screen convention), so a higher latitude gives a smaller Y. `WebMercatorProjection` normalizes to roughly degree scale (`180/π`), **not** EPSG:3857 meters, and Web Mercator is undefined near the poles (about ±85°). Symptom: a shape mirrored top-to-bottom, at the wrong scale for meters, or degenerate above ~85° latitude. Pass `EquirectangularProjection` (x = lon, y = −lat) for a plain mapping.

## What moved, and what it used to be called

**`GeoBoundingBox` is no longer a `GeoGeometry` (4.0.0).** The geo type hierarchy was restructured in 4.0.0: `GeoBoundingBox` used to be a member of the `GeoGeometry` sealed hierarchy, and code could pass a bounding box wherever a geometry was accepted. It no longer type-checks. In this module `GeoBoundingBox` is now purely the *result* of `calculateBoundingBox()` and the receiver/argument of `GeoBoundingBox.merge(...)`; the old `is GeoBoundingBox -> this` branch inside `GeoGeometry.calculateBoundingBox()` is gone. (Rendering a bbox as a polygon also went from `io.github.aughtone:format-viewable` — see that module's skill.)

**The email normalizer left this module in 3.1.1.** The package `io.github.aughtone.toolbox.normalize` (`Email.normalize.kt`, `EmailPolicy`, `NormalizedEmail`, `EmailNormalizationError`) is gone; code importing `io.github.aughtone.toolbox.normalize.*` no longer compiles. That API now lives in a separate library — coordinate `io.github.aughtone.normalize:email`, package `io.github.aughtone.normalize`. There is no email API in `:toolbox` today.

## What it is not for

- **Not a locale/currency number formatter.** `Double.format` rounds; it does not group digits, localize, or render currency. Human-facing numbers, sizes, and money belong to `io.github.aughtone:format-readable`.
- **Not a string-templating or i18n engine.** `String.format` fills numbered slots and nothing more — no format specifiers, width, precision, or message plurals.
- **Not a GIS toolkit.** The geo surface is bounding-box math plus two projections. Coordinate-reference-system transforms, geodesic distance, and general spatial operations are out of scope.
