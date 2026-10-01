---
name: io-github-aughtone-format-viewable-compose
description: >-
  The Compose member of the aughtone-format family, bridging
  io.github.aughtone:format-viewable to Compose Multiplatform and Jetpack
  Compose. Draw a GeoJSON shape (a country or region outline, a route, a polygon,
  a geofence) or a ViewablePath in Compose; get an ImageVector for an Image or
  Icon, a Painter that scales and centres the shape to fit its box, or a Compose
  Path for your own Canvas drawing. Good for map thumbnails, area previews and
  shape icons without a map SDK. Single fill colour only, with no strokes,
  gradients or multi-layer styled graphics; not a map widget, with no pan, zoom
  or tiles. For SVG, WKT or PNG output without Compose use
  io.github.aughtone:format-viewable. Your app supplies its own Compose
  Multiplatform dependency.
license: Apache-2.0
metadata:
  version: "4.1.0-SNAPSHOT"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-viewable-compose

## What it solves

The Compose Multiplatform bridge for `io.github.aughtone:format-viewable`. It takes a `ViewablePath` (or GeoJSON, which it projects for you) and produces something Compose can draw:

- A raw `androidx.compose.ui.graphics.Path` for a `Canvas`/`DrawScope` — `path.toComposePath()`.
- An `ImageVector` you can hand to `rememberVectorPainter` — `path.toImageVector()`, `geoJson.toImageVector()`.
- A ready `Painter` that scales and centres the shape to the space it is given — `ViewablePathPainter(path)`.

That is the whole module: three extension functions and one `Painter` subclass, all in `io.github.aughtone.viewable.compose`.

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts (common, JVM, Android, and iOS via Kotlin) — here, against Compose Multiplatform; there is no separate Swift or JavaScript API.

## How it is meant to be used

```kotlin
import io.github.aughtone.viewable.compose.toImageVector
import io.github.aughtone.viewable.compose.ViewablePathPainter
import androidx.compose.ui.graphics.Color

// GeoJSON -> ImageVector -> Image  (simplest "just draw it")
@Composable
fun GeoShape(geoJson: GeoJson) {
    val vector = remember(geoJson) { geoJson.toImageVector(fillColor = Color.Blue) }
    Image(rememberVectorPainter(vector), contentDescription = null)
}

// ViewablePath with the fit-to-bounds Painter
@Composable
fun PathShape(path: ViewablePath) {
    val painter = remember(path) { ViewablePathPainter(path, color = Color.Red) }
    Image(painter, contentDescription = null, modifier = Modifier.size(120.dp))
}
```

## Invariants and traps

**`toComposePath` does not scale or translate — drawing its output raw usually shows nothing.** It emits the path's own (projected) coordinates, which for the default Web Mercator projection are large. Symptom: a `Canvas` that draws nothing / off-screen. For "just render it", use `ViewablePathPainter` (scales to fit and centres) or `toImageVector` (translates to the bounds origin). Reach for `toComposePath` only when you are doing your own transform in the `DrawScope`.

**None of these are `@Composable` or memoized.** `toComposePath`, `toImageVector`, and the `ViewablePathPainter` constructor all do real work every time they run (the painter builds its path eagerly at construction). Symptom: jank / rebuilding on every recomposition — wrap them in `remember(key)` yourself.

**`toComposePath` overwrites the passed path's fill type.** When you hand it an existing `Path` to append to, it first sets that path's `fillType` from the receiver's winding rule. Symptom: a reused `Path` silently changes fill rule — pass a fresh `Path` unless you intend that.

**Empty or degenerate paths draw nothing, quietly.** A path with no computable bounds makes `toImageVector` fall back to a 1×1 image and `ViewablePathPainter` report `Size.Unspecified`. A path whose bounds have zero width or height divides by zero in the painter's scale (→ infinite scale, nothing visible) — guarded on the draw size but not the bounds size. Symptom: a blank `Image` where you expected a shape.

**Fill only, single colour, `Color.Black` by default.** There is no stroke, no per-layer style, and no opacity handling at this layer. The winding rule carries through from the `ViewablePath`. Coordinates are narrowed `Double`→`Float`, so very large projected values lose precision. Symptom: a filled silhouette where you wanted an outline, or jitter at extreme coordinates.

**`ViewableGraphic` is not supported here.** Only `ViewablePath` (and GeoJSON, via `toViewablePath`) has a Compose path. Symptom: no conversion function for a multi-layer, styled `ViewableGraphic` — render it as SVG/EPS/image through `io.github.aughtone:format-viewable` instead.

## What moved, and what it used to be called

This module was introduced in the 3.0.0 cycle and carried into 4.0.0 unchanged in API; nothing is deprecated and nothing was renamed.

The one 4.0.0 knock-on comes from upstream: because `GeoBoundingBox` is no longer a `GeoGeometry` (see the `io.github.aughtone:format-viewable` skill), you can no longer pass a bounding box to `GeoJson.toImageVector` as a drawable — build a `GeoPolygon` from its corners first. Everything else here is unaffected.

## What it is not for

- **Not a Compose widget library.** It returns `Path`/`ImageVector`/`Painter` values; you place them in `Image`, `Canvas`, or your own composable. There is no drop-in map or shape composable.
- **Not a styling engine.** Single fill colour only — no strokes, gradients, opacity, or multi-layer `ViewableGraphic` rendering.
- **Not a source of Compose itself.** Compose Multiplatform is an `implementation` dependency here, so it is not exposed transitively — yet its types (`Path`, `ImageVector`, `Color`, `Painter`) appear in this module's public API. A consumer that uses that API needs Compose Multiplatform already present on its own classpath; this module does not carry it through.
