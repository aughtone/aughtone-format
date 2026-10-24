package io.github.aughtone.datetime.format

/**
 * The DateTimeStyle enum is used to specify the style of date and time formatting.
 *
 * The examples are for English (United States); every locale has its own patterns.
 *```
 * [None]: No style - Empty output.
 * [Short]: Short text style, typically numeric. For example '1/12/1952' or '3:30 PM'.
 * [Medium]: Medium text style, with some detail. For example 'Jan 12, 1952' or '3:30:42 PM'.
 * [Long]: Long text style, with lots of detail. For example 'January 12, 1952' or '3:30:42 PM PST'.
 * [Full]: Full text style, with the most detail. For example 'Saturday, January 12, 1952' or '3:30:42 PM Pacific Standard Time'.
 *```
 */
enum class DateTimeStyle {
    /**
     * Short text style, typically numeric.
     * For example '1/12/1952' or '3:30 PM'.
     */
    Short,

    /**
     * Medium text style, with some detail.
     * For example 'Jan 12, 1952' or '3:30:42 PM'.
     */
    Medium,

    /**
     * Long text style, with lots of detail. A time adds the zone's abbreviation.
     * For example 'January 12, 1952' or '3:30:42 PM PST'.
     * */
    Long,

    /**
     * Full text style, with the most detail. A date adds the weekday; a time adds the zone's full
     * name. The era is not part of any style: pass `showEra = true` to the date formatters for it.
     * For example 'Saturday, January 12, 1952' or '3:30:42 PM Pacific Standard Time'.
     */
    Full,

    /** No style - Empty output */
    None,

}
