package io.github.aughtone.toolbox.geo

import kotlin.math.*

/**
 * A functional interface for projecting geographic coordinates (WGS84) to a 2D Cartesian grid.
 */
fun interface GeoProjection {
    /**
     * Projects a geographic coordinate to a 2D point.
     *
     * @param longitude The longitude in decimal degrees.
     * @param latitude The latitude in decimal degrees.
     * @return A [Pair] representing the (x, y) coordinates on the projected plane.
     */
    fun project(longitude: Double, latitude: Double): Pair<Double, Double>
}

/**
 * Equirectangular (Plate Carrée) projection.
 *
 * This is the simplest projection where x = longitude and y = latitude.
 * It is often used for simple thematic maps but distorts area and shape significantly
 * as distance from the equator increases.
 */
object EquirectangularProjection : GeoProjection {
    override fun project(longitude: Double, latitude: Double): Pair<Double, Double> {
        // We negate latitude for the Y axis because screen space (SVG) increases downwards.
        // We use 0.0 + (-latitude) to avoid -0.0 which can cause issues in comparisons.
        val y = if (latitude == 0.0) 0.0 else -latitude
        return longitude to y
    }
}

/**
 * Web Mercator projection (EPSG:3857).
 *
 * This is the standard projection used by almost all web mapping services (Google Maps, OSM, etc.).
 * It preserves angles and shapes locally but significantly distorts size near the poles.
 *
 * Note: This implementation normalizes the output range to approximately [-180, 180] for X
 * and a similar range for Y to maintain a logical scale relative to degrees.
 */
object WebMercatorProjection : GeoProjection {
    private const val RADIUS = 180.0 / PI

    override fun project(longitude: Double, latitude: Double): Pair<Double, Double> {
        val x = longitude
        // Standard Mercator formula: ln(tan(π/4 + φ/2))
        val latRad = latitude * PI / 180.0
        val rawY = ln(tan(PI / 4.0 + latRad / 2.0)) * RADIUS
        
        // We negate y because screen space increases downwards.
        val y = if (rawY == 0.0) 0.0 else -rawY
        return x to y
    }
}
