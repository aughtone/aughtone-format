package io.github.aughtone.datetime.format

/**
 * The DateTimeStyle enum is used to specify the style of date and time formatting.
 *
 * The value can be one of the following:
 *```
 * [NONE]: No style - Empty output.
 * [SHORT]: Short text style, typically numeric. For example, the format might be '12.13.52' or '3:30pm'.
 * [MEDIUM]: Medium text style, with some detail. For example, the format might be 'Jan 12, 1952'.
 * [LONG]: Long text style, with lots of detail. For example, the format might be 'January 12, 1952'.
 * [FULL]: Full text style, with the most detail. For example, the format might be 'Tuesday, April 12, 1952 AD' or '3:30:42pm PST'.
 *```
 */
enum class DateTimeStyle {
    /**
     * Short text style, typically numeric.
     * For example, the format might be '12.13.52' or '3:30pm'.
     */
    SHORT,

    /**
     * Medium text style, with some detail.
     * For example, the format might be 'Jan 12, 1952'.
     */
    MEDIUM,

    /**
     * Long text style, with lots of detail.
     * For example, the format might be 'January 12, 1952'.
     * */
    LONG,

    /**
     * Full text style, with the most detail.
     * For example, the format might be 'Tuesday, April 12, 1952 AD' or '3:30:42pm PST'.
     */
    FULL,

    /** No style - Empty output */
    NONE,

}
