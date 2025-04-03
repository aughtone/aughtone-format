package io.github.aughtone.datetime.format

/**
 * The RelativeStyle enum is used to specify the style of relative duration.
 *
 * The value can be one of the following:
 *```
 * [None]: No style - Empty output.
 * [Short]: Short text style, typically numeric. For example, the format might be '5d'.
 * [Long]: Long text style, with lots of detail. For example, the format might be '5 days'.
 *```
 */
enum class RelativeStyle {
    /**
     * Short text style, typically numeric.
     * For example, the format might be '5d'.
     */
    Short,

    /**
     * Long text style, with lots of detail.
     * For example, the format might be '5 days'.
     * */
    Long,

    /** No style - Empty output */
    None,

}
