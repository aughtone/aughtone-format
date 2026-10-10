package io.github.aughtone.viewable.geojson

import io.github.aughtone.toolbox.format
import io.github.aughtone.types.geo.*

/**
 * Extension functions to convert GeoJSON objects to Well-Known Text (WKT).
 */

/**
 * Converts a [GeoJson] object to a Well-Known Text (WKT) representation.
 *
 * A [GeoFeature] renders its geometry, or `GEOMETRYCOLLECTION EMPTY` when it has
 * none; a [GeoFeatureCollection] renders as a `GEOMETRYCOLLECTION`. A geometry with no
 * parts renders with the `EMPTY` keyword (`POLYGON EMPTY`). Only longitude and latitude are
 * written; an altitude is left out.
 *
 * @param precision The maximum number of decimal places for each coordinate; trailing zeros
 *   are dropped. Defaults to `6`.
 * @return The WKT string for this object.
 */
fun GeoJson.toWkt(precision: Int = 6): String = when (this) {
    is GeoGeometry -> toWkt(precision)
    is GeoFeature -> geometry?.toWkt(precision) ?: "GEOMETRYCOLLECTION EMPTY"
    is GeoFeatureCollection -> wkt("GEOMETRYCOLLECTION", features) { it.toWkt(precision) }
}

/**
 * Writes a WKT geometry with its [parts] in parentheses, or as `<tag> EMPTY` when it has none —
 * `POLYGON ()` is not valid WKT.
 */
private fun <T> wkt(tag: String, parts: List<T>, write: (T) -> String): String =
    if (parts.isEmpty()) "$tag EMPTY" else "$tag (${parts.joinToString(", ", transform = write)})"

/**
 * Converts a [GeoGeometry] to its Well-Known Text (WKT) representation.
 *
 * @param precision The maximum number of decimal places for each coordinate; trailing zeros
 *   are dropped. Defaults to `6`.
 * @return The WKT string for this geometry (e.g. `POINT (...)`, `POLYGON (...)`).
 */
fun GeoGeometry.toWkt(precision: Int = 6): String = when (this) {
    is GeoPoint -> "POINT (${coordinates.toWktPoints(precision)})"
    is GeoMultiPoint -> wkt("MULTIPOINT", coordinates) { "(${it.toWktPoints(precision)})" }
    is GeoLineString -> "LINESTRING (${coordinates.toWktLine(precision)})"
    is GeoMultiLineString -> wkt("MULTILINESTRING", coordinates) { "(${it.toWktLine(precision)})" }
    is GeoPolygon -> if (coordinates.isEmpty()) "POLYGON EMPTY" else "POLYGON (${coordinates.toWktPolygon(precision)})"
    is GeoMultiPolygon -> wkt("MULTIPOLYGON", coordinates) { "(${it.toWktPolygon(precision)})" }
    is GeometryCollection -> wkt("GEOMETRYCOLLECTION", geometries) { it.toWkt(precision) }
}

private fun List<Double>.toWktPoints(precision: Int): String {
    if (size < 2) return ""
    return "${this[0].format(precision)} ${this[1].format(precision)}"
}

private fun List<List<Double>>.toWktLine(precision: Int): String {
    return joinToString(", ") { it.toWktPoints(precision) }
}

private fun List<List<List<Double>>>.toWktPolygon(precision: Int): String {
    return joinToString(", ") { "(${it.toWktLine(precision)})" }
}
