package io.github.aughtone.datetime.format

/**
 * The width of a localized calendar name — how much of the name is shown.
 *
 * A width changes only the length of the rendered name, never which day or
 * month it identifies. It is the choice between "Monday" and "Mon", or
 * "March" and "Mar".
 *
 * Only the two widths the bundled locale data actually carries are exposed. A
 * narrower "narrow" form (for example "M" for Monday) is not available; where a
 * locale would distinguish a stand-alone form from the in-context form, that
 * distinction is not made either — each name has a single value per width.
 *
 * @see DayOfWeek.displayName
 * @see Month.displayName
 */
enum class TextWidth {
    /**
     * The full, spelled-out name: `Monday`, `March`.
     */
    Full,

    /**
     * The abbreviated name: `Mon`, `Mar`. For some locales (for example many
     * CJK scripts) the abbreviated form is identical to [Full].
     */
    Abbreviated,
}
