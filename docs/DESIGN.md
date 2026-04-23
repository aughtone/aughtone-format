# Design & UI Rules

- [Ordinality Formatting](file:///Users/bpappin/Workspace/aughtone-format/docs/DESIGN_ORDINALITY.md)

## Human-Readable Philosophy
The `:readable` module prioritizes human perception over mathematical precision.

### 1. Duration Scaling & "Fuzzy" Thresholds
- **Perceptual Grouping**: We scale to weeks between 7 and 21 days because "3 weeks" is more meaningful than "21 days".
- **The Month Boundary**: We stay in "days" for values like **29 days** to avoid rounding to "4 weeks", which often feels like an "almost month" in human context.
- **Immediate Precision**: We use exact thresholds (e.g., 60s -> 1m) but round fractional values to the nearest whole unit for clean UI display.

### 2. Geospatial Clarity
- **Visual Context**: Azimuths include **localized cardinal directions** (e.g., `(NW)`, `(SO)`, `(СЗ)`) because raw degrees are difficult for non-navigators to visualize. Support is maintained across 55 core languages.
- **Coordinate Simplicity**: We provide DD and DMS formats to balance modern GPS needs with traditional navigational standards.
