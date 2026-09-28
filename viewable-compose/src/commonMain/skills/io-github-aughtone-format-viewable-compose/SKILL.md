---
name: io-github-aughtone-format-viewable-compose
description: >-
  Draw GeoJSON — or an aughtone-format ViewablePath — in Compose Multiplatform:
  convert it to a Compose Path for a Canvas, an ImageVector, or a fit-to-bounds
  Painter. Reach for it when you have aughtone-format viewable geometry and want
  to render it in Compose. Re-exports :viewable (toViewablePath and friends);
  you supply your own Compose Multiplatform dependency.
license: Apache-2.0
metadata:
  version: "4.1.0-alpha1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-viewable-compose

## What it solves

The Compose Multiplatform bridge for `:viewable`. It takes a `ViewablePath` (or GeoJSON, which it projects for you) and produces something Compose can draw:

- A raw `androidx.compose.ui.graphics.Path` for a `Canvas`/`DrawScope` — `path.toComposePath()`.
- An `ImageVector` you can hand to `rememberVectorPainter` — `path.toImageVector()`, `geoJson.toImageVector()`.
- A ready `Painter` that scales and centres the shape to the space it is given — `ViewablePathPainter(path)`.

That is the whole module: three extension functions and one `Painter` subclass, all in `io.github.aughtone.viewable.compose`.

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

`geoJson.toViewablePath(...)` and the rest of `:viewable` are available transitively — this module re-exports it with `api(...)`.

## Invariants and traps

**`toComposePath` does not scale or translate — drawing its output raw usually shows nothing.** It emits the path's own (projected) coordinates, which for the default Web Mercator projection are large. Dropping that straight into a small `Canvas` draws off-screen. For "just render it", use `ViewablePathPainter` (scales to fit and centres) or `toImageVector` (translates to the bounds origin). Reach for `toComposePath` only when you are doing your own transform in the `DrawScope`.

**None of these are `@Composable` or memoized.** `toComposePath`, `toImageVector`, and the `ViewablePathPainter` constructor all do real work every time they run (the painter builds its path eagerly at construction). Wrap them in `remember(key)` yourself; the library does nothing to help recomposition.

**Empty or degenerate paths draw nothing, quietly.** A path with no computable bounds makes `toImageVector` fall back to a 1×1 image and `ViewablePathPainter` report `Size.Unspecified`. A path whose bounds have zero width or height divides by zero in the painter's scale (→ infinite scale, nothing visible) — it is guarded on the draw size but not on the bounds size.

**Fill only, single colour, `Color.Black` by default.** There is no stroke, no per-layer style, and no opacity handling at this layer — only a single fill colour. The winding rule carries through from the `ViewablePath`. Coordinates are narrowed `Double`→`Float`, so very large projected values lose precision.

**`ViewableGraphic` is not supported here.** Only `ViewablePath` (and GeoJSON, via `toViewablePath`) has a Compose path. A multi-layer, styled `ViewableGraphic` has no Compose conversion in this module — render it as SVG/EPS/image through `:viewable` instead.

## What moved, and what it used to be called

This module was introduced in the 3.0.0 cycle and carried into 4.0.0 unchanged in API; nothing is deprecated and nothing was renamed.

The one 4.0.0 knock-on comes from upstream: because `GeoBoundingBox` is no longer a `GeoGeometry` (see the `:viewable` skill), you can no longer pass a bounding box to `GeoJson.toImageVector` as a drawable — build a `GeoPolygon` from its corners first. Everything else here is unaffected.

## What it is not for

- **Not a Compose widget library.** It returns `Path`/`ImageVector`/`Painter` values; you place them in `Image`, `Canvas`, or your own composable. There is no drop-in map or shape composable.
- **Not a styling engine.** Single fill colour only — no strokes, gradients, opacity, or multi-layer `ViewableGraphic` rendering.
- **Not a source of Compose itself.** Compose Multiplatform is an `implementation` dependency here but its types (`Path`, `ImageVector`, `Color`, `Painter`) are in this module's public API, so a consumer must add their own Compose Multiplatform dependency (built against 1.12.0) — it is not guaranteed transitively.
