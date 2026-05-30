package io.github.aughtone.viewable.geojson

import io.github.aughtone.toolbox.geo.GeoProjection
import io.github.aughtone.toolbox.geo.WebMercatorProjection
import io.github.aughtone.types.geo.GeoBoundingBox
import io.github.aughtone.types.geo.GeoFeature
import io.github.aughtone.types.geo.GeoFeatureCollection
import io.github.aughtone.types.geo.GeoGeometry
import io.github.aughtone.types.geo.GeoJson
import io.github.aughtone.types.geo.GeoLineString
import io.github.aughtone.types.geo.GeoMultiLineString
import io.github.aughtone.types.geo.GeoMultiPoint
import io.github.aughtone.types.geo.GeoMultiPolygon
import io.github.aughtone.types.geo.GeoPoint
import io.github.aughtone.types.geo.GeoPolygon
import io.github.aughtone.types.geo.GeometryCollection
import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewablePath
import io.github.aughtone.viewable.calculateBounds
import kotlin.collections.plus


/**
 * Converts a [GeoJson] object into a [io.github.aughtone.viewable.ViewablePath].
 *
 * @param projection The projection to use when converting coordinates.
 * @return A [io.github.aughtone.viewable.ViewablePath] containing the projected vector commands.
 */
fun GeoJson.toViewablePath(
    projection: GeoProjection = WebMercatorProjection
): ViewablePath = when (this) {
    is GeoGeometry -> toViewablePath(projection)
    is GeoFeature -> geometry?.toViewablePath(projection) ?: ViewablePath(emptyList())
    is GeoFeatureCollection -> {
        val allCommands = features.flatMap { it.geometry?.toViewablePath(projection)?.commands ?: emptyList() }
        ViewablePath(allCommands, bounds = allCommands.calculateBounds())
    }
}

/**
 * Converts a [GeoGeometry] into a [ViewablePath].
 *
 * @param projection The projection to use when converting coordinates.
 * @return A [ViewablePath] containing the projected vector commands.
 */
fun GeoGeometry.toViewablePath(
    projection: GeoProjection = WebMercatorProjection
): ViewablePath {
    val commands = when (this) {
        is GeoPoint -> coordinates.toPointCommands(projection)
        is GeoMultiPoint -> coordinates.flatMap { it.toPointCommands(projection) }
        is GeoLineString -> coordinates.toLineCommands(projection)
        is GeoMultiLineString -> coordinates.flatMap { it.toLineCommands(projection) }
        is GeoPolygon -> coordinates.toPolygonCommands(projection)
        is GeoMultiPolygon -> coordinates.flatMap { it.toPolygonCommands(projection) }
        is GeometryCollection -> geometries.flatMap { it.toViewablePath(projection).commands }
        is GeoBoundingBox -> listOf(
            listOf(
                listOf(west, south),
                listOf(east, south),
                listOf(east, north),
                listOf(west, north),
                listOf(west, south)
            )
        ).toPolygonCommands(projection)
    }
    return ViewablePath(commands, bounds = commands.calculateBounds())
}


internal fun List<Double>.toPointCommands(projection: GeoProjection): List<PathCommand> {
    if (size < 2) return emptyList()
    val (x, y) = projection.project(this[0], this[1])
    return listOf(PathCommand.MoveTo(x, y), PathCommand.LineTo(x, y))
}

internal fun List<List<Double>>.toLineCommands(projection: GeoProjection): List<PathCommand> {
    if (isEmpty()) return emptyList()
    return mapIndexed { index, coords ->
        val (x, y) = projection.project(coords[0], coords[1])
        if (index == 0) PathCommand.MoveTo(x, y) else PathCommand.LineTo(x, y)
    }
}

internal fun List<List<List<Double>>>.toPolygonCommands(projection: GeoProjection): List<PathCommand> {
    return flatMap { ring ->
        val commands = ring.toLineCommands(projection)
        if (commands.isNotEmpty()) commands + PathCommand.Close else emptyList()
    }
}
