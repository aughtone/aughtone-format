package io.github.aughtone.viewable

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ViewableGraphicTest {

    private val triangle = ViewablePath(
        commands = listOf(
            PathCommand.MoveTo(0.0, 0.0), PathCommand.LineTo(10.0, 0.0),
            PathCommand.LineTo(10.0, 10.0), PathCommand.Close,
        ),
        bounds = ViewableRect(0.0, 0.0, 10.0, 10.0),
    )

    private fun graphic(style: ViewableStyle, path: ViewablePath = triangle) =
        ViewableGraphic(listOf(ViewableLayer(path, style)))

    @Test
    fun svgCarriesTheAlphaOfTranslucentFillAndStroke() {
        val svg = graphic(ViewableStyle(fillColor = 0x80FF0000.toInt(), strokeColor = 0x40000000, strokeWidth = 1.0)).toSvgDocument()
        assertTrue(svg.contains("""fill="#ff0000" fill-opacity="0.5""""), svg)
        assertTrue(svg.contains("""stroke="#000000" stroke-opacity="0.25""""), svg)
    }

    @Test
    fun svgOmitsOpacityForOpaqueColours() {
        val svg = graphic(ViewableStyle(fillColor = 0xFFFF0000.toInt(), strokeColor = 0xFF000000.toInt(), strokeWidth = 1.0)).toSvgDocument()
        assertEquals(
            """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 10 10"><path d="M0,0L10,0L10,10Z" fill="#ff0000" stroke="#000000" stroke-width="1" fill-rule="evenodd"/></svg>""",
            svg,
        )
    }

    @Test
    fun epsKeepsThePathForTheStrokeAfterFilling() {
        val eps = graphic(ViewableStyle(fillColor = 0xFFFF0000.toInt(), strokeColor = 0xFF000000.toInt(), strokeWidth = 1.0)).toEpsDocument()
        // "fill" consumes the current path, so it has to run inside its own gsave/grestore.
        assertTrue(eps.contains("gsave\n1 0 0 setrgbcolor\neofill\ngrestore\n0 0 0 setrgbcolor\n1 setlinewidth\nstroke\n"), eps)
    }

    @Test
    fun epsIsNotMirroredTopToBottom() {
        // The path's y grows downward; PostScript's grows upward. The top edge (y = 0) has to land
        // at the top of the bounding box (y = 10), and the bottom corner at its bottom.
        val eps = graphic(ViewableStyle(fillColor = 0xFFFF0000.toInt())).toEpsDocument()
        assertTrue(eps.contains("newpath\n0 10 moveto\n10 10 lineto\n10 0 lineto\nclosepath\n"), eps)
    }

    @Test
    fun epsBoundingBoxEnclosesFractionalBounds() {
        val path = ViewablePath(
            commands = listOf(PathCommand.MoveTo(5.9, -47.8), PathCommand.LineTo(10.5, -45.8), PathCommand.LineTo(5.9, -45.8), PathCommand.Close),
            bounds = ViewableRect(5.9, -47.8, 10.5, -45.8),
        )
        val eps = graphic(ViewableStyle(fillColor = 0xFF000000.toInt()), path).toEpsDocument()
        assertTrue(eps.contains("%%BoundingBox: 5 -48 11 -45\n"), eps)
        assertTrue(eps.contains("%%HiResBoundingBox: 5.9 -47.8 10.5 -45.8\n"), eps)
    }

    @Test
    fun aPathBuiltByHandWorksOutItsOwnBounds() {
        val path = ViewablePath(listOf(PathCommand.MoveTo(5.0, 10.0), PathCommand.LineTo(105.0, 60.0), PathCommand.Close))
        assertEquals(ViewableRect(5.0, 10.0, 105.0, 60.0), path.bounds)
        assertTrue(path.toSvgDocument().contains("""viewBox="5 10 100 50""""), path.toSvgDocument())
        // Bounds that are passed in are kept as given.
        assertEquals(ViewableRect(0.0, 0.0, 1.0, 1.0), path.copy(bounds = ViewableRect(0.0, 0.0, 1.0, 1.0)).bounds)
    }

    @Test
    fun aFlatLineStillGetsAViewBoxWithRoomToDrawIn() {
        val horizontal = ViewablePath(listOf(PathCommand.MoveTo(0.0, 5.0), PathCommand.LineTo(10.0, 5.0)))
        val svg = graphic(ViewableStyle(strokeColor = 0xFF000000.toInt(), strokeWidth = 1.0), horizontal).toSvgDocument()
        // The zero height is opened up to match the width, centred on the line.
        assertTrue(svg.contains("""viewBox="0 0 10 10""""), svg)

        val point = ViewablePath(listOf(PathCommand.MoveTo(3.0, 4.0)))
        assertTrue(point.toSvgDocument().contains("""viewBox="2.5 3.5 1 1""""), point.toSvgDocument())
    }
}
