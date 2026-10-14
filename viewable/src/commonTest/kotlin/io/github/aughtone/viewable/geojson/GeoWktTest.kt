package io.github.aughtone.viewable.geojson

import io.github.aughtone.types.geo.GeoFeatureCollection
import io.github.aughtone.types.geo.GeoLineString
import io.github.aughtone.types.geo.GeoMultiLineString
import io.github.aughtone.types.geo.GeoMultiPoint
import io.github.aughtone.types.geo.GeoMultiPolygon
import io.github.aughtone.types.geo.GeoPoint
import io.github.aughtone.types.geo.GeoPolygon
import io.github.aughtone.types.geo.GeometryCollection
import kotlin.test.Test
import kotlin.test.assertEquals

class GeoWktTest {

    @Test
    fun pointsAndLinesAreWrittenLongitudeFirst() {
        assertEquals("POINT (-74.006 40.7128)", GeoPoint(-74.006, 40.7128).toWkt())
        assertEquals("LINESTRING (0 0, 10 10)", GeoLineString(listOf(listOf(0.0, 0.0), listOf(10.0, 10.0))).toWkt())
    }

    @Test
    fun coordinatesNearZeroAreWrittenInPlainDecimals() {
        assertEquals("POINT (0.0005 51.4779)", GeoPoint(0.0005, 51.4779).toWkt())
        assertEquals("POINT (-0.000012 0)", GeoPoint(-0.000012, 0.0).toWkt())
    }

    @Test
    fun emptyGeometriesUseTheEmptyKeyword() {
        assertEquals("GEOMETRYCOLLECTION EMPTY", GeoFeatureCollection(emptyList()).toWkt())
        assertEquals("GEOMETRYCOLLECTION EMPTY", GeometryCollection(emptyList()).toWkt())
        assertEquals("POLYGON EMPTY", GeoPolygon(emptyList()).toWkt())
        assertEquals("MULTIPOINT EMPTY", GeoMultiPoint(emptyList()).toWkt())
        assertEquals("MULTILINESTRING EMPTY", GeoMultiLineString(emptyList()).toWkt())
        assertEquals("MULTIPOLYGON EMPTY", GeoMultiPolygon(emptyList()).toWkt())
    }
}
