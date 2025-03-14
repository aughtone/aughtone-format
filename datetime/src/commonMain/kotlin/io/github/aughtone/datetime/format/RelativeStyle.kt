package io.github.aughtone.datetime.format

/**
 * The RelativeStyle enum is used to specify the style of relative duration.
 *
 * The value can be one of the following:
 *```
 * [NONE]: No style - Empty output.
 * [SHORT]: Short text style, typically numeric. For example, the format might be '5d'.
 * [LONG]: Long text style, with lots of detail. For example, the format might be '5 days'.
 *```
 */
enum class RelativeStyle {
    /**
     * Short text style, typically numeric.
     * For example, the format might be '5d'.
     */
    SHORT,

    /**
     * Long text style, with lots of detail.
     * For example, the format might be '5 days'.
     * */
    LONG,

    /** No style - Empty output */
    NONE,

}
