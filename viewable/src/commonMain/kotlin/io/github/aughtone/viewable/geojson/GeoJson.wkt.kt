package io.github.aughtone.viewable.geojson

import io.github.aughtone.toolbox.format
import io.github.aughtone.types.geo.*

/**
 * Extension functions to convert GeoJSON objects to Well-Known Text (WKT).
 */

fun GeoJson.toWkt(precision: Int = 6): String = when (this) {
    is GeoGeometry -> toWkt(precision)
    is GeoFeature -> geometry?.toWkt(precision) ?: "GEOMETRYCOLLECTION EMPTY"
    is GeoFeatureCollection -> "GEOMETRYCOLLECTION (${features.joinToString(", ") { it.toWkt(precision) }})"
}

fun GeoGeometry.toWkt(precision: Int = 6): String = when (this) {
    is GeoPoint -> "POINT (${coordinates.toWktPoints(precision)})"
    is GeoMultiPoint -> "MULTIPOINT (${coordinates.joinToString(", ") { "(${it.toWktPoints(precision)})" }})"
    is GeoLineString -> "LINESTRING (${coordinates.toWktLine(precision)})"
    is GeoMultiLineString -> "MULTILINESTRING (${coordinates.joinToString(", ") { "(${it.toWktLine(precision)})" }})"
    is GeoPolygon -> "POLYGON (${coordinates.toWktPolygon(precision)})"
    is GeoMultiPolygon -> "MULTIPOLYGON (${coordinates.joinToString(", ") { "(${it.toWktPolygon(precision)})" }})"
    is GeometryCollection -> "GEOMETRYCOLLECTION (${geometries.joinToString(", ") { it.toWkt(precision) }})"
    is GeoBoundingBox -> "POLYGON ((${west.format(precision)} ${south.format(precision)}, ${east.format(precision)} ${south.format(precision)}, ${east.format(precision)} ${north.format(precision)}, ${west.format(precision)} ${north.format(precision)}, ${west.format(precision)} ${south.format(precision)}))"
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
