---
skill-id: io.github.aughtone.format-viewable
spec-version: "1.0"
type: "Aughtone AI-Skill"
name: "Aughtone Format - Viewable"
scope: "Platform-agnostic vector graphics representation and rendering instructions, including GeoJSON-to-SVG paths."
compatibility: "Kotlin Multiplatform (KMP)"
author: "Aughtone"
---

# 📖 Aughtone Format - Viewable AI-Skill

This module provides a platform-agnostic representation of vector graphics, layers, and styles, alongside utilities to convert geographic data (GeoJSON) and primitives into standard SVG paths.

## 🎨 The AI Toolbox

### 1. Primitives & Path Representation
Represent shapes in a platform-agnostic manner to draw across different platforms (JVM, Android, iOS, JS, WasmJS).

- **Core Classes**:
    - `ViewablePath(commands: List<PathCommand>, winding: WindingRule, bounds: ViewableRect?)`
    - `PathCommand`: `MoveTo(x, y)`, `LineTo(x, y)`, `Close`
    - `WindingRule`: `NonZero`, `EvenOdd`
    - `ViewableRect(left, top, right, bottom)`
    - `ViewableStyle(fillColor: Int, strokeColor: Int, strokeWidth: Double)`
    - `ViewableLayer(path: ViewablePath, style: ViewableStyle)`
    - `ViewableGraphic(layers: List<ViewableLayer>, bounds: ViewableRect?)`

### 2. SVG Paths Generation (`geojson`, `svg`)
Convert GeoJSON coordinates or abstract paths directly to SVG command strings.

- **Primary APIs**:
    - `GeoJson.toSvgPathData(projection: GeoProjection, precision: Int): String`
    - `GeoGeometry.toSvgPathData(projection: GeoProjection, precision: Int): String`
    - `ViewablePath.toSvgPathData(precision: Int): String`
    - `ViewablePath.toSvgDocument(width: Int?, height: Int?, fillColor: Int, precision: Int): String`
- **Preference**: Use `GeoJson.toSvgPathData()` to render geospatial boundaries or maps to SVG components. Use `ViewablePath.toSvgDocument()` to generate standalone SVG files/contents.

### 3. Bitmap & Image Export (`graphics`)
Render vector paths to static image buffers where platform support exists.

- **Primary APIs**:
    - `ViewablePath.toImage(width: Int, height: Int, fillColor: Int): ViewableImage`
    - `ViewableGraphic.toImage(width: Int, height: Int): ViewableImage`
    - `ViewableImage.toByteArray(format: ImageFormat): ByteArray`
    - `ViewableImage.toDataUri(format: ImageFormat): String`
    - `isImageSupported: Boolean` (Checks if image generation is supported on the target platform).
- **Preference**: ALWAYS check `isImageSupported` before attempting bitmap generation. Unsupported platforms (JS, WasmJS) will throw `IllegalStateException`.

## ⚖️ Compliance & Standards
- **W3C SVG 1.1**: Output SVG strings conform to the W3C SVG paths specification (M, L, Z).
- **GeoJSON (RFC 7946)**: Supports all GeoJSON geometries (Point, MultiPoint, LineString, MultiLineString, Polygon, MultiPolygon, GeometryCollection).

## 🔒 Serialization & Immutability
- **Immutability**: All primitives and model objects are immutable data classes.
- **Side-Effects**: Drawing and conversion operations are stateless and thread-safe.
