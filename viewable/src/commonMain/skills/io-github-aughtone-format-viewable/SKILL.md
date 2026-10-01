---
name: io-github-aughtone-format-viewable
description: >-
  The drawing-output member of the aughtone-format family, toolkit-neutral with
  no UI dependency, turning GeoJSON geometry (points, lines, polygons, features)
  into something you can draw or store without a map SDK. Convert GeoJSON to an
  SVG path d attribute or a complete SVG document; export an EPS file; convert to
  WKT (Well-Known Text) for PostGIS and other spatial databases; render a PNG or
  JPEG image as bytes or a data URI on Android, iOS and desktop (not in the
  browser); get a neutral vector path of move, line and close commands with its
  bounds; style fill, stroke and stroke width on layered graphics. Coordinates
  are projected for you, Web Mercator by default or equirectangular. Good for map
  thumbnails, shape previews and outline icons. Not an interactive map, tile
  renderer or GeoJSON parser. To draw the result in Compose use
  io.github.aughtone:format-viewable-compose; the projections come from
  io.github.aughtone:format-toolbox.
license: Apache-2.0
metadata:
  version: "4.1.0-SNAPSHOT"
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

`ViewablePath` / `ViewableGraphic` are the neutral currency; a companion module, `io.github.aughtone:format-viewable-compose`, renders them in Compose.

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts (common, JVM, Android, and iOS via Kotlin); there is no separate Swift or JavaScript API.

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

Pass a `GeoProjection` argument to override the projection; `WebMercatorProjection` and `EquirectangularProjection` are available for that.

## Invariants and traps

**The string conversions do not throw — malformed or empty input degrades silently.** There are no `require`/`check` guards on the SVG/path/WKT output. Symptom: an empty `""` (SVG/path), `"GEOMETRYCOLLECTION EMPTY"` (WKT), or a `viewBox="0 0 1 1"` box means the input produced no drawable geometry — the call did not fail, it had nothing to draw (coordinates with fewer than two numbers are dropped; a feature with a null geometry yields nothing).

**Bitmap generation throws on JS/Wasm.** `toImage` and `ViewableImage.toByteArray` are implemented on JVM, Android and iOS, but on JS and Wasm they raise `IllegalStateException` ("Bitmap generation is not supported"). Symptom: an `IllegalStateException` in a browser build — gate on `isImageSupported` (false on JS/Wasm) and use the SVG string output there instead.

**The default projection is Web Mercator, in screen space.** Every `toViewablePath`/`toSvgPathData` defaults to `WebMercatorProjection`. That flips Y (larger latitude → smaller Y, the screen convention), normalizes to roughly degree scale rather than EPSG:3857 meters, and is undefined near the poles (about ±85°). Symptom: a shape mirrored top-to-bottom, wrongly scaled for meters, or degenerate above ~85°. Pass `EquirectangularProjection` (x = lon, y = −lat) for a plain mapping.

**Precision defaults differ by format.** SVG path/document precision defaults to **3**; `toWkt` defaults to **6**. Symptom: SVG and WKT of the same geometry disagree in decimal places — set it explicitly if they must match.

**`ViewablePath.winding` defaults to `EvenOdd`, not `NonZero`.** Symptom: self-intersecting or hole-containing polygons fill wrong — set `winding` deliberately.

**A style color of `0` means "none".** `ViewableStyle.fillColor`/`strokeColor` are ARGB `Int`s where `0` renders as no fill / no stroke. Symptom: nothing drawn when you passed `0` — give a real ARGB value, e.g. `0xFF000000.toInt()` for opaque black.

**`ViewableRect` is corners, not extents.** It is `(left, top, right, bottom)`; `width`/`height` are computed getters. Symptom: a rect the wrong size when you passed width/height — construct it from corners.

## What moved, and what it used to be called

**`ImageFormat.WEBP` was removed in 4.1.0.** Raster export is `ImageFormat.PNG` or `ImageFormat.JPEG` only; code naming `ImageFormat.WEBP` no longer compiles. It only ever produced real WebP on Android — the JVM returned empty bytes and iOS returned PNG labelled as WebP — so nothing portable relied on it.

**A `GeoBoundingBox` is no longer renderable by these functions (4.0.0).** This is the change most likely to break existing code. Through the 3.x line, `GeoBoundingBox` was a `GeoGeometry`, so you could pass one straight into `toWkt()`, `toSvgPathData()`, or `toViewablePath()` and get back a rectangle. In 4.0.0 `GeoBoundingBox` is not a `GeoGeometry`, so **those calls no longer accept it — it will not type-check**, and the `is GeoBoundingBox ->` branches are gone from all three converters. To render a bounding box now, build the polygon yourself from its corners and pass that:

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

- **Not an interactive map or a UI widget.** It produces path data, documents, images, and WKT — it does not pan, zoom, tile, or render to a live surface. For Compose rendering use `io.github.aughtone:format-viewable-compose`.
- **Not a GeoJSON parser or validator.** It consumes geo values; parsing/validating GeoJSON text is elsewhere.
- **Not a projection library.** It applies a `GeoProjection`; it does not implement CRS transforms or geodesy of its own.
