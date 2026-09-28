---
name: io-github-aughtone-format-viewable
description: >-
  Convert GeoJSON geometry to something you can draw — an SVG path ("d") or a
  standalone SVG/EPS document, WKT, a raster image, or a toolkit-neutral vector
  path — projecting the coordinates for you. Reach for it instead of
  hand-writing coordinate projection and SVG/WKT path emission. Value types
  (ViewablePath, ViewableGraphic) are toolkit-agnostic; to draw in Compose add
  :viewable-compose. Re-exports :toolbox (projections, geo types).
license: Apache-2.0
metadata:
  version: "4.1.0-alpha1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-viewable

## What it solves

You have GeoJSON (or a bare `GeoGeometry`) and need to *render* it, without pulling in a UI toolkit. This module projects the coordinates and hands you a drawable in whatever shape you need:

- A toolkit-neutral vector path — `geometry.toViewablePath()` → `ViewablePath` (a list of move/line/close commands plus its bounds).
- An SVG path `d` attribute, or a whole `<svg>` document — `path.toSvgPathData()`, `path.toSvgDocument(width, height)`, `geometry.toSvgPathData()`.
- An EPS document — `graphic.toEpsDocument()`.
- A raster image, where the platform supports it — `path.toImage(w, h).toByteArray()` / `.toDataUri()`.
- Well-Known Text — `geometry.toWkt()`.

`ViewablePath` / `ViewableGraphic` are the neutral currency; a companion module, `:viewable-compose`, renders them in Compose.

## How it is meant to be used

```kotlin
import io.github.aughtone.viewable.*
import io.github.aughtone.viewable.geojson.*

// GeoJSON -> projected path -> SVG document
val path = geometry.toViewablePath()                 // default projection: WebMercatorProjection
val svg  = path.toSvgDocument(width = 256, height = 256)

// GeoJSON straight to an SVG "d" string
val d = geometry.toSvgPathData()                      // precision defaults to 3

// GeoJSON to WKT
val wkt = geometry.toWkt()                            // precision defaults to 6

// Build a path by hand
val p = ViewablePath(listOf(PathCommand.MoveTo(0.0, 0.0), PathCommand.LineTo(10.0, 10.0), PathCommand.Close))

// Raster only where supported (not JS/Wasm) — always gate on isImageSupported
if (isImageSupported) {
    val png = path.toImage(width = 200, height = 200).toByteArray(ImageFormat.PNG)
}
```

Give a `GeoProjection` argument to override the projection; it and `WebMercatorProjection`/`EquirectangularProjection` come from `:toolbox`, which this module re-exports.

## Invariants and traps

**Nothing throws — malformed or empty input degrades silently.** There are no `require`/`check` guards. Coordinates with fewer than two numbers are dropped, a feature with a null geometry yields `""` (SVG), an empty `ViewablePath` (path), or `"GEOMETRYCOLLECTION EMPTY"` (WKT), and a path with no computable bounds makes `toSvgDocument` fall back to `viewBox="0 0 1 1"`. If your output is empty or a 1×1 box, the input produced no drawable geometry — the call did not fail, it had nothing to draw.

**The default projection is Web Mercator, in screen space.** Every `toViewablePath`/`toSvgPathData` defaults to `WebMercatorProjection`. That flips Y (larger latitude → smaller Y, the screen convention), normalizes to roughly degree scale rather than EPSG:3857 meters, and is undefined near the poles (about ±85°). Pass `EquirectangularProjection` (x = lon, y = −lat) when you want a plain, predictable mapping.

**Precision defaults differ by format.** SVG path/document precision defaults to **3**; `toWkt` defaults to **6**. Set it explicitly if you need them to match.

**`ViewablePath.winding` defaults to `EvenOdd`, not `NonZero`.** Self-intersecting or hole-containing polygons fill differently under the two rules; set `winding` deliberately.

**A style color of `0` means "none".** `ViewableStyle.fillColor`/`strokeColor` are ARGB `Int`s where `0` renders as no fill / no stroke (not transparent-black-that-still-draws). Give a real ARGB value — e.g. `0xFF000000.toInt()` for opaque black.

**`ViewableRect` is corners, not extents.** It is `(left, top, right, bottom)`; `width`/`height` are computed getters. When constructing one by hand, pass corners.

**Raster support is platform-gated.** `isImageSupported` is `false` on JS/WasmJs; call `toImage`/`toByteArray` only behind that check.

## What moved, and what it used to be called

**A `GeoBoundingBox` is no longer renderable by these functions (4.0.0).** This is the change most likely to break existing code. In aughtone-types ≤ 3.x, `GeoBoundingBox` was a `GeoGeometry`, so you could pass one straight into `toWkt()`, `toSvgPathData()`, or `toViewablePath()` and get back a rectangle: a WKT `POLYGON ((...))`, an SVG closed path, or a `ViewablePath` with a closed 5-vertex ring. In 4.0.0 `GeoBoundingBox` is not a `GeoGeometry`, so **those calls no longer accept it — it will not type-check**, and the `is GeoBoundingBox ->` branches are gone from all three converters. To render a bounding box now, build the polygon yourself from its corners and pass that:

```kotlin
// west,south -> east,south -> east,north -> west,north -> west,south  (closed ring)
val ring = listOf(
    listOf(box.west, box.south), listOf(box.east, box.south),
    listOf(box.east, box.north), listOf(box.west, box.north),
    listOf(box.west, box.south),
)
GeoPolygon(listOf(ring)).toSvgPathData()   // or .toWkt(), .toViewablePath()
```

This module was introduced in the 3.0.0 cycle. No public declarations are deprecated in 4.0.0.

## What it is not for

- **Not an interactive map or a UI widget.** It produces path data, documents, images, and WKT — it does not pan, zoom, tile, or render to a live surface. For Compose rendering use `:viewable-compose`.
- **Not a GeoJSON parser or validator.** It consumes `aughtone-types` geo values; parsing/validating GeoJSON text is elsewhere.
- **Not a projection library.** It applies a `GeoProjection` from `:toolbox`; it does not implement CRS transforms or geodesy of its own.
