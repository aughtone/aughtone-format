# Aughtone Format: Toolbox

The `:toolbox` module provides the low-level formatting primitives and shared geo utilities the `:datetime`, `:readable`, and `:viewable` modules build on. It is a small grab-bag of primitives, not a framework.

Published as `io.github.aughtone:format-toolbox`. It re-exports `aughtone-types` (its geo types), `kotlinx-datetime`, and `kotlinx-serialization-json` to consumers.

## 🔢 Number and string primitives

```kotlin
import io.github.aughtone.toolbox.format
import io.github.aughtone.toolbox.obfuscateLast

// Round a Double to N decimals as a plain string.
1234.5678.format(2)   // "1234.57"
```

- **`Double.format(precision: Int): String`** — rounds to `precision` decimals. It is a rounding helper, not a locale formatter: `precision <= 0` returns an integer string, a trailing `.0` is stripped (so `2.0.format(2)` is `"2"`, not `"2.00"`), and there are no thousands separators or locale awareness. For grouped, localized, or currency numbers use `:readable`.
- **`String.obfuscateLast(count: Int = 4, obfuscationChar: Char = '•', digitsOnly: Boolean = false): String`** — masks the trailing characters for display. When the string is no shorter than `count` it is returned unchanged; with `digitsOnly = true` only the digits are kept, so `"+1 (555) 123-4567"` becomes `"•••••••4567"`.
- **`String.format(vararg args: Any): String`** — fills numbered `%1`, `%2`, … placeholders (1-based), the multiplatform stand-in for the `String.format` common Kotlin lacks. A missing argument leaves its placeholder verbatim and an extra argument is dropped; keep templates to `%1`–`%9`. It is annotated `@ExperimentalMultiplatform`, so the call site needs `@OptIn(ExperimentalMultiplatform::class)`.
- **`typealias Formatter<T> = (value: T) -> String`** — the shared function type the other modules' factory helpers return.

## 🗺️ Geo utilities

```kotlin
import io.github.aughtone.toolbox.geo.calculateBoundingBox
import io.github.aughtone.toolbox.geo.WebMercatorProjection

val box = polygon.calculateBoundingBox()          // GeoBoundingBox?  — may be null
val (x, y) = WebMercatorProjection.project(lon, lat)
```

- **`GeoJson.calculateBoundingBox(): GeoBoundingBox?`** and **`GeoGeometry.calculateBoundingBox(): GeoBoundingBox?`** — the bounding box of a geometry or feature. Nullable: an empty collection or coordinate list returns `null`, so always null-check.
- **`GeoBoundingBox.merge(other: GeoBoundingBox): GeoBoundingBox`** — the smallest box containing both.
- **`GeoProjection`** — a functional interface, `fun project(longitude: Double, latitude: Double): Pair<Double, Double>`, so you can pass a lambda where you want your own.
- **`WebMercatorProjection`** and **`EquirectangularProjection`** — the two provided projections. Both return Y increasing *downward* (the SVG/screen convention). `WebMercatorProjection` normalizes to roughly degree scale, not EPSG:3857 meters, and is undefined near the poles (about ±85°); reach for `EquirectangularProjection` when you want a plain lon/lat mapping.

The geo value types (`GeoBoundingBox`, `GeoGeometry`, `GeoPoint`, …) are defined in `aughtone-types` and reach consumers through this module's `api(...)` re-export. As of 4.0.0, `GeoBoundingBox` is **no longer a `GeoGeometry`**: it is only the result of `calculateBoundingBox()` and the receiver/argument of `merge(...)`, and can no longer be passed where a `GeoGeometry` is expected.
