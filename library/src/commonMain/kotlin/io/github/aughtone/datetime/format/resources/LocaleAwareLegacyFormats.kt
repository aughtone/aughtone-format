package io.github.aughtone.datetime.format.resources

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.dateformats.LocalDateFormats0CA
import io.github.aughtone.datetime.format.resources.dateformats.LocalDateFormats0US
import io.github.aughtone.datetime.format.resources.datetimeformats.CompositLocalDateTimeFormats
import io.github.aughtone.datetime.format.resources.datetimeformats.LocalDateTimeFormats0ISO
import io.github.aughtone.datetime.format.resources.timeformats.LocalTimeFormats0CA
import io.github.aughtone.datetime.format.resources.timeformats.LocalDateFormats0ISO
import io.github.aughtone.datetime.format.resources.timeformats.LocalTimeFormats0ISO
import io.github.aughtone.datetime.format.resources.timeformats.LocalTimeFormats0US
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
@Deprecated("Marked for refactoring")
class LocaleAwareLegacyFormats {
    @Deprecated("Marked for refactoring")
    object Defaults {
        val ISO_TIME = LocalTimeFormats0ISO
        val ISO_DATE = LocalDateFormats0ISO
        val ISO_DATE_TIME = LocalDateTimeFormats0ISO
        @Deprecated("Marked for refactoring")
        internal fun <T> selectFormat(
            data: Map<String, StyledDateTimeFormats<T>>,
            locale: Locale,
            fallbackTo: StyledDateTimeFormats<T>,
        ): StyledDateTimeFormats<T> {
            val language = locale.language
            val region = locale.region

//            println("language: $language, region: $region : ${language}_r${region}")

            return data["${language}_${region}"] ?: data[language] ?: data[""] ?: fallbackTo
        }
    }

    @Deprecated("Marked for refactoring")
    object DateTime {

        fun short(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) =
            Defaults.selectFormat(
                datetime,
                locale = locale,
                Defaults.ISO_DATE_TIME
            ).short(locale, timeZone, twentyFourHour)

        fun medium(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) =
            Defaults.selectFormat(
                datetime,
                locale = locale,
                Defaults.ISO_DATE_TIME
            ).medium(locale, timeZone, twentyFourHour)

        fun long(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) =
            Defaults.selectFormat(
                datetime,
                locale = locale,
                Defaults.ISO_DATE_TIME
            ).long(locale, timeZone, twentyFourHour)

        fun full(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) =
            Defaults.selectFormat(
                datetime,
                locale = locale,
                Defaults.ISO_DATE_TIME
            ).full(locale, timeZone, twentyFourHour)

        //        2025-02-04 16:08:39.194 24472-24472 DateTimeStyle SHORT       bpappin.qapital              D  2/4/25, 4:08 PM
//        2025-02-04 16:08:39.208 24472-24472 DateTimeStyle MEDIUM      bpappin.qapital                      D  Feb 4, 2025, 4:08:39 PM
//        2025-02-04 16:08:39.212 24472-24472 DateTimeStyle LONG        bpappin.qapital                      D  February 4, 2025 at 4:08:39 PM EST
//        2025-02-04 16:08:39.231 24472-24472 DateTimeStyle FULL        bpappin.qapital                      D  Tuesday, February 4, 2025 at 4:08:39 PM Eastern Standard Time
//
        @Deprecated("Marked for refactoring")
        val datetime: Map<String, StyledDateTimeFormats<LocalDateTime>> =
            mapOf(
                "" to CompositLocalDateTimeFormats,
//        "en" to Format(short =, medium =, long =, full =),
//        "en_rCA" to Format(short =, medium =, long =, full =),
//        "en_rUS" to Format(short =, medium =, long =, full =),
            )

    }

    @Deprecated("Marked for refactoring")
    object Date {
        fun short(locale: Locale) =
            Defaults.selectFormat(date, locale = locale, Defaults.ISO_DATE).short(locale)

        fun medium(locale: Locale) =
            Defaults.selectFormat(date, locale = locale, Defaults.ISO_DATE).medium(locale)

        fun long(locale: Locale) =
            Defaults.selectFormat(date, locale = locale, Defaults.ISO_DATE).long(locale)

        fun full(locale: Locale) =
            Defaults.selectFormat(date, locale = locale, Defaults.ISO_DATE).full(locale)

        @OptIn(FormatStringsInDatetimeFormats::class)
        val date: Map<String, StyledDateTimeFormats<LocalDate>> = mapOf(
            "" to LocalDateFormats0CA, // 'Tuesday, April 12, 1952 AD' or '3:30:42pm PST'.
            "en" to LocalDateFormats0CA,
            "en_CA" to LocalDateFormats0CA,
            "en_US" to LocalDateFormats0US,
        )

    }

    @Deprecated("Marked for refactoring")
    object Time {
        fun short(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) = Defaults.selectFormat(
            time,
            locale = locale,
            Defaults.ISO_TIME
        ).short(locale = locale, timeZone = timeZone, twentyFourHour = twentyFourHour)

        fun medium(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) = Defaults.selectFormat(
            time,
            locale = locale,
            Defaults.ISO_TIME
        ).medium(locale = locale, timeZone = timeZone, twentyFourHour = twentyFourHour)

        fun long(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) = Defaults.selectFormat(
            time,
            locale = locale,
            Defaults.ISO_TIME
        ).long(locale = locale, timeZone = timeZone, twentyFourHour = twentyFourHour)

        fun full(
            locale: Locale,
            timeZone: TimeZone = TimeZone.currentSystemDefault(),
            twentyFourHour: Boolean = false,
        ) = Defaults.selectFormat(
            time,
            locale = locale,
            Defaults.ISO_TIME
        ).full(locale = locale, timeZone = timeZone, twentyFourHour = twentyFourHour)

        @OptIn(FormatStringsInDatetimeFormats::class)
        val time: Map<String, StyledDateTimeFormats<LocalTime>> = mapOf(
            "" to LocalTimeFormats0CA,
            "en" to LocalTimeFormats0CA,
            "en_CA" to LocalTimeFormats0CA,
            "en_US" to LocalTimeFormats0US,
        )

    }


}
