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
  version: "4.1.1"
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
import io.github.aughtone.viewable.graphics.*   // toImage, toByteArray, toDataUri, ImageFormat, isImageSupported

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

**The string conversions do not throw — empty input degrades silently.** There are no `require`/`check` guards on the SVG/path/WKT/EPS output. Symptom: an empty `""` (SVG/path) or an `EMPTY` geometry (WKT: `"GEOMETRYCOLLECTION EMPTY"`, `"POLYGON EMPTY"`) means the input had nothing to draw — a feature with a null geometry, or a geometry with no parts. A coordinate that is not a number is written as `NaN`. A `viewBox="0 0 1 1"` means the path has no coordinates at all. A `ViewablePath` built by hand works out its bounds from its commands unless you pass them. A flat line or a single point has no area, so its box is opened up to a square around it and it stays drawable. Numbers are always plain decimals, at most `precision` places, with trailing zeros dropped.

**EPS output matches the SVG and raster output: same way up, fill and stroke both drawn.** A layer with both a fill and a stroke gets both, and the drawing is not mirrored. EPS has no transparency, so a colour's alpha is ignored there; `ViewableGraphic.toSvgDocument` carries it as `fill-opacity` / `stroke-opacity`.

**Bitmap generation throws on JS/Wasm.** `toImage` and `ViewableImage.toByteArray` are implemented on JVM, Android and iOS, but on JS and Wasm they raise `IllegalStateException` ("Bitmap generation is not supported"). Symptom: an `IllegalStateException` in a browser build — gate on `isImageSupported` (false on JS/Wasm) and use the SVG string output there instead.

**`toImage` needs a positive width and height**, and throws `IllegalArgumentException` otherwise on every platform that renders. An image that cannot be encoded throws `IllegalStateException` from `toByteArray`; it never returns empty bytes. JPEG has no transparency, so transparent areas are flattened onto white on JVM, Android and iOS alike — use PNG to keep them. Strokes end flush with the line (butt caps) on every platform.

**On the JVM, `ViewableGraphic.drawTo(Graphics2D, width, height)` leaves your context as it found it.** It draws through a copy of the `Graphics2D`, so the transform, colour, stroke and hints it sets do not leak into your later drawing. The lower-level `ViewablePath.drawTo(Graphics2D, style)` draws on the context you pass and leaves its colour and stroke set to the style's; it applies no transform. Symptom: a later drawing in an unexpected colour or line width — set your own before drawing again.

**The default projection is Web Mercator, in screen space.** Every `toViewablePath`/`toSvgPathData` defaults to `WebMercatorProjection`. That flips Y (larger latitude → smaller Y, the screen convention), normalizes to degree scale rather than EPSG:3857 meters, and holds any latitude beyond about ±85.0511° at that limit, as web maps do. Symptom: a shape mirrored top-to-bottom, wrongly scaled for meters, or flattened along the edge near a pole. Pass `EquirectangularProjection` (x = lon, y = −lat) for a plain mapping.

**Precision defaults differ by format.** SVG path/document precision defaults to **3**; `toWkt` defaults to **6**. Symptom: SVG and WKT of the same geometry disagree in decimal places — set it explicitly if they must match.

**`ViewablePath.winding` defaults to `EvenOdd`, not `NonZero`.** Symptom: self-intersecting or hole-containing polygons fill wrong — set `winding` deliberately.

**A style color of `0` means "none".** `ViewableStyle.fillColor`/`strokeColor` are ARGB `Int`s where `0` renders as no fill / no stroke. Symptom: nothing drawn when you passed `0` — give a real ARGB value, e.g. `0xFF000000.toInt()` for opaque black.

**`ViewableRect` is corners, not extents.** It is `(left, top, right, bottom)`; `width`/`height` are computed getters. Symptom: a rect the wrong size when you passed width/height — construct it from corners.

## What moved, and what it used to be called

**Silent changes in 4.1.1 — same calls, different output.**

- EPS was upside down relative to the SVG and raster output, dropped the stroke of any layer that also had a fill, and could have an integer bounding box smaller than the drawing. All three are fixed.
- `ViewableGraphic.toSvgDocument` dropped a colour's alpha; it now writes `fill-opacity` and `stroke-opacity`.
- An empty geometry gave invalid WKT (`POLYGON ()`); it is now `POLYGON EMPTY`, and likewise for the other types.
- Coordinates near zero came out in exponent notation on JVM, Android and iOS at higher precisions (`POINT (5.0E-4 51.4779)`); every number is now a plain decimal on every platform.
- A `ViewablePath` built without bounds rendered unscaled in a 1×1 box; it now works out its bounds and is scaled to fit. A flat line or a single point gave an infinite scale and a zero-size viewBox; it now renders.
- The default Web Mercator projection holds latitude at about ±85.0511°, so a polygon reaching the south pole is no longer infinite.
- Android flattened JPEG transparency onto black (with the default black fill, an all-black image); JVM strokes extended half the stroke width past each end; `ViewableGraphic.drawTo(Graphics2D, …)` left its transform on the caller's context; `toImage` with a non-positive size failed differently on each platform; Android and iOS could return empty bytes instead of failing.

**`ImageFormat.WEBP` was removed in 4.1.0.** Raster export is `ImageFormat.PNG` or `ImageFormat.JPEG` only; code naming `ImageFormat.WEBP` no longer compiles. It only ever produced real WebP on Android — the JVM returned empty bytes and iOS returned PNG labelled as WebP — so nothing portable relied on it.

**A `GeoBoundingBox` is no longer renderable by these functions (4.0.0).** This is the change most likely to break existing code. Through the 3.x line, `GeoBoundingBox` was a `GeoGeometry`, so you could pass one straight into `toWkt()`, `toSvgPathData()`, or `toViewablePath()` and get back a rectangle. In 4.0.0 `GeoBoundingBox` is not a `GeoGeometry`, so **those calls no longer accept it — it will not type-check**, and the `is GeoBoundingBox ->` branches are gone from all three converters. To render a bounding box now, build the polygon yourself from its corners and pass that:

```kotlin
import io.github.aughtone.types.geo.GeoBoundingBox
import io.github.aughtone.types.geo.GeoPolygon
import io.github.aughtone.viewable.geojson.toSvgPathData

// west,south -> east,south -> east,north -> west,north -> west,south  (closed ring)
fun boxPath(box: GeoBoundingBox): String {
val ring = listOf(
    listOf(box.west, box.south), listOf(box.east, box.south),
    listOf(box.east, box.north), listOf(box.west, box.north),
    listOf(box.west, box.south),
)
return GeoPolygon(listOf(ring)).toSvgPathData()   // or .toWkt(), .toViewablePath()
}
```

This module was introduced in the 3.0.0 cycle. No public declarations are deprecated in 4.0.0.

## What it is not for

- **Not an interactive map or a UI widget.** It produces path data, documents, images, and WKT — it does not pan, zoom, tile, or render to a live surface. For Compose rendering use `io.github.aughtone:format-viewable-compose`.
- **Not a GeoJSON parser or validator.** It consumes geo values; parsing/validating GeoJSON text is elsewhere.
- **Not a projection library.** It applies a `GeoProjection`; it does not implement CRS transforms or geodesy of its own.
