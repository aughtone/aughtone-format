package io.github.aughtone.viewable.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.aughtone.toolbox.geo.GeoProjection
import io.github.aughtone.toolbox.geo.WebMercatorProjection
import io.github.aughtone.types.geo.GeoJson
import io.github.aughtone.viewable.geojson.toViewablePath

/**
 * Converts a [GeoJson] object into an [ImageVector].
 *
 * @param projection The projection to use when converting coordinates.
 * @param name The name of the [ImageVector].
 * @param fillColor The color to fill the path with.
 * @return An [ImageVector] representing the geographic data.
 */
fun GeoJson.toImageVector(
    projection: GeoProjection = WebMercatorProjection,
    name: String = "GeoJsonPath",
    fillColor: Color = Color.Black
): ImageVector = toViewablePath(projection).toImageVector(name, fillColor)
