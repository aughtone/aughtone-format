package io.github.aughtone.toolbox.geo

import io.github.aughtone.types.geo.*
import kotlin.math.max
import kotlin.math.min

/**
 * Calculates the bounding box of a [GeoJson] object.
 *
 * @return The bounding box enclosing the object's coordinates, or `null` when there
 *   are no usable coordinates — for example a [GeoFeature] with no geometry or an
 *   empty [GeoFeatureCollection].
 */
fun GeoJson.calculateBoundingBox(): GeoBoundingBox? = when (this) {
    is GeoGeometry -> calculateBoundingBox()
    is GeoFeature -> geometry?.calculateBoundingBox()
    is GeoFeatureCollection -> {
        val boxes = features.mapNotNull { it.geometry?.calculateBoundingBox() }
        if (boxes.isEmpty()) null else boxes.reduce { acc, box -> acc.merge(box) }
    }
}

/**
 * Calculates the bounding box of a [GeoGeometry].
 *
 * @return The bounding box enclosing the geometry's coordinates, or `null` when there
 *   are no usable coordinates — for example an empty [GeometryCollection].
 */
fun GeoGeometry.calculateBoundingBox(): GeoBoundingBox? = when (this) {
    is GeoPoint -> GeoBoundingBox(coordinates[0], coordinates[1], coordinates[0], coordinates[1])
    is GeoMultiPoint -> coordinates.toBoundingBox()
    is GeoLineString -> coordinates.toBoundingBox()
    is GeoMultiLineString -> coordinates.flatten().toBoundingBox()
    is GeoPolygon -> coordinates.flatten().toBoundingBox()
    is GeoMultiPolygon -> coordinates.flatten().flatten().toBoundingBox()
    is GeometryCollection -> {
        val boxes = geometries.mapNotNull { it.calculateBoundingBox() }
        if (boxes.isEmpty()) null else boxes.reduce { acc, box -> acc.merge(box) }
    }
}

private fun List<List<Double>>.toBoundingBox(): GeoBoundingBox? {
    if (isEmpty()) return null
    var minX = Double.MAX_VALUE
    var minY = Double.MAX_VALUE
    var maxX = -Double.MAX_VALUE
    var maxY = -Double.MAX_VALUE

    forEach { coords ->
        if (coords.size >= 2) {
            minX = min(minX, coords[0])
            minY = min(minY, coords[1])
            maxX = max(maxX, coords[0])
            maxY = max(maxY, coords[1])
        }
    }
    return if (minX == Double.MAX_VALUE) null else GeoBoundingBox(minX, minY, maxX, maxY)
}

/**
 * Merges two bounding boxes into a single one that contains both.
 *
 * @param other The bounding box to merge with this one.
 * @return A bounding box that encloses both this box and [other].
 */
fun GeoBoundingBox.merge(other: GeoBoundingBox): GeoBoundingBox = GeoBoundingBox(
    west = min(west, other.west),
    south = min(south, other.south),
    east = max(east, other.east),
    north = max(north, other.north)
)
