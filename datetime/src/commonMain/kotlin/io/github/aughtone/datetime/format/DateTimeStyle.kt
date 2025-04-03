package io.github.aughtone.datetime.format

/**
 * The DateTimeStyle enum is used to specify the style of date and time formatting.
 *
 * The value can be one of the following:
 *```
 * [None]: No style - Empty output.
 * [Short]: Short text style, typically numeric. For example, the format might be '12.13.52' or '3:30pm'.
 * [Medium]: Medium text style, with some detail. For example, the format might be 'Jan 12, 1952'.
 * [Long]: Long text style, with lots of detail. For example, the format might be 'January 12, 1952'.
 * [Full]: Full text style, with the most detail. For example, the format might be 'Tuesday, April 12, 1952 AD' or '3:30:42pm PST'.
 *```
 */
enum class DateTimeStyle {
    /**
     * Short text style, typically numeric.
     * For example, the format might be '12.13.52' or '3:30pm'.
     */
    Short,

    /**
     * Medium text style, with some detail.
     * For example, the format might be 'Jan 12, 1952'.
     */
    Medium,

    /**
     * Long text style, with lots of detail.
     * For example, the format might be 'January 12, 1952'.
     * */
    Long,

    /**
     * Full text style, with the most detail.
     * For example, the format might be 'Tuesday, April 12, 1952 AD' or '3:30:42pm PST'.
     */
    Full,

    /** No style - Empty output */
    None,

}
