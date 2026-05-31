---
skill-id: io.github.aughtone.format-viewable-compose
spec-version: "1.0"
type: "Aughtone AI-Skill"
name: "Aughtone Format - Viewable Compose"
scope: "Compose Multiplatform UI integration for viewable paths and GeoJSON objects, providing Painters, Paths, and ImageVectors."
compatibility: "Kotlin Multiplatform (KMP), Compose Multiplatform"
author: "Aughtone"
---

# 📖 Aughtone Format - Viewable Compose AI-Skill

This module integrates `:viewable` models with Compose Multiplatform, enabling seamless rendering of vector graphics, shapes, and maps in Jetpack / Compose Multiplatform applications.

## 🎨 The AI Toolbox

### 1. Vector Path Conversion
Convert platform-agnostic `ViewablePath` objects directly into Compose assets.

- **Primary APIs**:
    - `ViewablePath.toComposePath(path: Path): Path`
    - `ViewablePath.toImageVector(name: String, fillColor: Color): ImageVector`
- **Preference**: Use `toImageVector()` to construct static vector resources that fit inside an `Icon` or standard Compose vector drawing scope. Use `toComposePath()` if you need direct path drawing or low-level canvas control.

### 2. GeoJSON to Compose Assets
Directly render spatial coordinates (boundaries, routes, features) into Compose vector shapes.

- **Primary APIs**:
    - `GeoJson.toImageVector(projection: GeoProjection, name: String, fillColor: Color): ImageVector`
- **Preference**: Directly call this function to render maps, overlays, or markers from GeoJSON sources without manual path building.

### 3. Path Painters
Render vector graphics inside Compose components with automatic centering and scale-to-fit logic.

- **Primary APIs**:
    - `ViewablePathPainter(viewablePath: ViewablePath, color: Color)`
- **Contract**:
    - Extends Compose `Painter`.
    - Automatically centers the path and scales it uniformly to fit the canvas, utilizing the path's intrinsic bounds.

## ⚖️ Compliance & Standards
- **Compose Multiplatform**: Operates using standard Compose graphics structures (`Path`, `ImageVector`, `Painter`).

## 🤖 Agent Onboarding (Usage Rules)
1. **Viewport & Sizing**: When using `toImageVector()`, ensure that `ViewablePath` has valid bounds. If bounds are null, the viewport defaults to 1x1.
2. **Color Selection**: Default drawing color is `Color.Black` unless specified. Use Compose theme-aware colors (e.g., `MaterialTheme.colorScheme.onSurface`) in actual UIs.
