package io.github.aughtone.viewable.geojson

import io.github.aughtone.toolbox.format
import io.github.aughtone.toolbox.geo.GeoProjection
import io.github.aughtone.toolbox.geo.WebMercatorProjection
import io.github.aughtone.types.geo.*

/**
 * Extension functions to convert GeoJSON objects and geometries into SVG path data strings.
 */

/**
 * Converts a [GeoJson] object to an SVG path data string ("d" attribute).
 *
 * @param projection The projection to use. Defaults to [WebMercatorProjection].
 * @param precision The number of decimal places to include in the output.
 * @return A string containing SVG path instructions (M, L, Z).
 */
fun GeoJson.toSvgPathData(
    projection: GeoProjection = WebMercatorProjection,
    precision: Int = 3,
): String = when (this) {
    is GeoGeometry -> toSvgPathData(projection, precision)
    is GeoFeature -> geometry?.toSvgPathData(projection, precision) ?: ""
    is GeoFeatureCollection -> features.joinToString(" ") { it.toSvgPathData(projection, precision) }
}

/**
 * Converts a [GeoGeometry] to an SVG path data string ("d" attribute).
 *
 * @param projection The projection to use. Defaults to [WebMercatorProjection].
 * @param precision The number of decimal places to include in the output.
 * @return A string containing SVG path instructions (M, L, Z).
 */
fun GeoGeometry.toSvgPathData(
    projection: GeoProjection = WebMercatorProjection,
    precision: Int = 3,
): String = when (this) {
    is GeoPoint -> coordinates.toSvgPointData(projection, precision)
    is GeoMultiPoint -> coordinates.joinToString(" ") { it.toSvgPointData(projection, precision) }
    is GeoLineString -> coordinates.toSvgLineData(projection, precision)
    is GeoMultiLineString -> coordinates.joinToString(" ") { it.toSvgLineData(projection, precision) }
    is GeoPolygon -> coordinates.toSvgPolygonData(projection, precision)
    is GeoMultiPolygon -> coordinates.joinToString(" ") { it.toSvgPolygonData(projection, precision) }
    is GeometryCollection -> geometries.joinToString(" ") { it.toSvgPathData(projection, precision) }
    is GeoBoundingBox -> listOf(
        listOf(
            listOf(west, south),
            listOf(east, south),
            listOf(east, north),
            listOf(west, north),
            listOf(west, south)
        )
    ).toSvgPolygonData(projection, precision)
}

private fun List<Double>.toSvgPointData(projection: GeoProjection, precision: Int): String {
    if (size < 2) return ""
    val (x, y) = projection.project(this[0], this[1])
    return "M${x.format(precision)},${y.format(precision)}l0,0"
}

private fun List<List<Double>>.toSvgLineData(projection: GeoProjection, precision: Int): String {
    if (isEmpty()) return ""
    val sb = StringBuilder()
    forEachIndexed { index, coords ->
        if (coords.size >= 2) {
            val (x, y) = projection.project(coords[0], coords[1])
            if (index == 0) {
                sb.append("M")
            } else {
                sb.append("L")
            }
            sb.append(x.format(precision)).append(",").append(y.format(precision))
        }
    }
    return sb.toString()
}

private fun List<List<List<Double>>>.toSvgPolygonData(projection: GeoProjection, precision: Int): String {
    if (isEmpty()) return ""
    return joinToString(" ") { ring ->
        val lineData = ring.toSvgLineData(projection, precision)
        if (lineData.isNotEmpty()) lineData + "Z" else ""
    }.trim()
}
