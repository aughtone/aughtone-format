# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- **AughtOne AI-Skill**: Integrated the **AughtOne AI-Skill Publishing Standard** across all modules (`readable`, `toolbox`, `datetime`).
- **Readable Module Expansion**:
    - **Duration Formatting**: Smart-scaling durations (s, m, h, d, w, mo, y) with human-centric rounding and 51-language support.
    - **Geospatial Formatting**: Localized formatting for `Altitude`, `Azimuth` (cardinal directions), and `Coordinates` (DD/DMS).
    - **Numeric Standardization**: Standardized `toReadable*` and `formatReadable` naming conventions.
    - **Functional Architecture**: Refactored all formatters to use "baked" lambdas (`Formatter<T>`) for high performance and immutability.
- **Ordinality System**: Established the functional resource map infrastructure for locale-aware formatting across 51 languages.
- **Project Structure**: Created the `:readable` and `:toolbox` modules.
