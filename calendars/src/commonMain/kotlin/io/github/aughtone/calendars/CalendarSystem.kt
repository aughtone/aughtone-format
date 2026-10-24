package io.github.aughtone.calendars

/**
 * A calendar system, identified by its Unicode CLDR name.
 *
 * The CLDR names are the values iCalendar's `RSCALE` (RFC 7529) uses, so an `RSCALE` value maps
 * to a system with [forCldrName]. Use [calendar] to get the arithmetic for a system.
 *
 * @property cldrName The system's CLDR identifier, as `RSCALE` writes it ("hebrew", "islamic-civil").
 */
enum class CalendarSystem(val cldrName: String) {
    /** The Thai solar calendar: Gregorian months and days, years of the Buddhist Era (ISO year + 543). */
    Buddhist("buddhist"),

    /** The Japanese calendar: Gregorian months and days, years counted within imperial eras. */
    Japanese("japanese"),

    /** The Republic of China (Minguo) calendar: Gregorian months and days, years from 1912. */
    Roc("roc"),

    /** The Hebrew lunisolar calendar, with a leap month (Adar I) in seven years of every nineteen. */
    Hebrew("hebrew"),

    /** The tabular Islamic calendar counted from the Friday epoch (16 July 622, Julian). */
    IslamicCivil("islamic-civil"),

    /** The tabular Islamic calendar counted from the Thursday epoch (15 July 622, Julian). */
    IslamicTabular("islamic-tbla"),

    /** The Umm al-Qura calendar of Saudi Arabia, from its published table. */
    IslamicUmmAlQura("islamic-umalqura"),

    /** The Islamic calendar as observed, whose months begin when the crescent is sighted. */
    Islamic("islamic"),

    /** The Islamic calendar as observed in Saudi Arabia. */
    IslamicSaudi("islamic-rgsa"),

    /** The Persian (Solar Hijri) calendar. */
    Persian("persian"),

    /** The Coptic calendar, counted from the Era of the Martyrs (284 CE). */
    Coptic("coptic"),

    /** The Ethiopic calendar, counted from the Era of Mercy (Amete Mihret, 8 CE). */
    Ethiopic("ethiopic"),

    /** The Ethiopic calendar counted from the Era of the World (Amete Alem): Amete Mihret + 5500. */
    EthiopicAmeteAlem("ethioaa"),

    /** The Indian national (Saka) calendar. */
    Indian("indian"),

    /** The Chinese lunisolar calendar, reckoned at Beijing. */
    Chinese("chinese"),

    /** The Korean lunisolar calendar (Dangi), reckoned at Seoul. */
    Dangi("dangi"),
    ;

    companion object {
        /**
         * The system whose CLDR name is [name], ignoring case, or `null` when no system has it.
         * An iCalendar `RSCALE` value is such a name: `forCldrName("HEBREW")` is [Hebrew].
         */
        fun forCldrName(name: String): CalendarSystem? = entries.firstOrNull { it.cldrName.equals(name, ignoreCase = true) }
    }
}
