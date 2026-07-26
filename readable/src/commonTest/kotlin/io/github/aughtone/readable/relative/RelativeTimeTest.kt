package io.github.aughtone.readable.relative

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class RelativeTimeTest {

    // Fixed reference point for deterministic tests
    private val now = Instant.fromEpochMilliseconds(1713888000000L) // 2024-04-23T16:00:00Z
    private val tz = TimeZone.UTC

    @Test
    fun testJustNow_withinDefaultThreshold() {
        assertEquals("just now", now.formatReadableRelative(now = now, locale = Locales.English, timeZone = tz))
        assertEquals("just now", (now - 59.seconds).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testJustNow_exactlyAtThreshold_notNow() {
        // Exactly 1 minute should be "1 minute ago" or "in 1 minute"
        assertEquals("1 minute ago", (now - 60.seconds).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testJustNow_customThreshold() {
        assertEquals("just now", (now - 8.seconds).formatReadableRelative(now = now, nowThreshold = 10.seconds, locale = Locales.English, timeZone = tz))
        assertEquals("8 seconds ago", (now - 8.seconds).formatReadableRelative(now = now, nowThreshold = 5.seconds, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testStyles() {
        assertEquals("5 days ago", (now - 5.days).formatReadableRelative(now = now, relativeDateStyle = RelativeStyle.Long, timeZone = TimeZone.UTC, locale = Locales.English, relativeThreshold = 10.days))
        // Short style in English suppresses the "ago" suffix
        assertEquals("5d", (now - 5.days).formatReadableRelative(now = now, relativeDateStyle = RelativeStyle.Short, timeZone = TimeZone.UTC, locale = Locales.English, relativeThreshold = 10.days))
    }

    @Test
    fun testNoneStyle_suppression() {
        // 5 days = 120 hours
        assertEquals("120 hours ago", (now - 5.days).formatReadableRelative(
            now = now,
            relativeDateStyle = RelativeStyle.None,
            relativeTimeStyle = RelativeStyle.Long,
            timeZone = TimeZone.UTC,
            locale = Locales.English,
            relativeThreshold = 10.days
        ))

        // 2 hours should remain 2 hours (hours is a time unit)
        assertEquals("2 hours ago", (now - 2.hours).formatReadableRelative(
            now = now,
            relativeTimeStyle = RelativeStyle.Long,
            timeZone = TimeZone.UTC,
            locale = Locales.English
        ))
    }

    @Test
    fun testDayStrings() {
        val reference = LocalDateTime(2023, 10, 27, 12, 0).toInstant(tz)
        val today = reference + 2.hours
        val tomorrow = reference + 25.hours
        val yesterday = reference - 25.hours

        // If it's the same day, Instant should return relative time (e.g. in 2 hours)
        assertEquals("in 2 hours", today.formatReadableRelative(now = reference, locale = Locales.English, timeZone = tz))
        assertEquals("Tomorrow", tomorrow.formatReadableRelative(now = reference, locale = Locales.English, timeZone = tz))
        assertEquals("Yesterday", yesterday.formatReadableRelative(now = reference, locale = Locales.English, timeZone = tz))
        
        assertEquals("dans 2 heures", today.formatReadableRelative(now = reference, locale = Locales.French, timeZone = tz))
        assertEquals("Demain", tomorrow.formatReadableRelative(now = reference, locale = Locales.French, timeZone = tz))
        assertEquals("Hier", yesterday.formatReadableRelative(now = reference, locale = Locales.French, timeZone = tz))
        
        assertEquals("oor 2 ure", today.formatReadableRelative(now = reference, locale = Locales.Afrikaans, timeZone = tz))
        assertEquals("Môre", tomorrow.formatReadableRelative(now = reference, locale = Locales.Afrikaans, timeZone = tz))
        assertEquals("Gister", yesterday.formatReadableRelative(now = reference, locale = Locales.Afrikaans, timeZone = tz))
    }

    @Test
    fun testLocalDateRelative() {
        val today = LocalDate(2023, 10, 27)
        val tomorrow = LocalDate(2023, 10, 28)
        val nextWeek = LocalDate(2023, 11, 3)
        
        assertEquals("Today", today.formatReadableRelative(now = today, locale = Locales.English))
        assertEquals("Tomorrow", tomorrow.formatReadableRelative(now = today, locale = Locales.English))
        assertEquals("in 1 week", nextWeek.formatReadableRelative(now = today, locale = Locales.English, relativeThreshold = 10.days))
    }

    // ── LocalDate "Recently" / nowThreshold (GAP-2 regression) ─────────────────

    @Test
    fun testLocalDate_yesterdayTomorrowSurviveLargeNowThreshold() {
        // A large nowThreshold must NOT swallow Yesterday/Tomorrow into "Recently".
        val today = LocalDate(2023, 10, 27)
        val yesterday = LocalDate(2023, 10, 26)
        val tomorrow = LocalDate(2023, 10, 28)
        assertEquals("Yesterday", yesterday.formatReadableRelative(now = today, nowThreshold = 5.days, relativeThreshold = 10.days, locale = Locales.English))
        assertEquals("Tomorrow", tomorrow.formatReadableRelative(now = today, nowThreshold = 5.days, relativeThreshold = 10.days, locale = Locales.English))
    }

    @Test
    fun testLocalDate_recentlyAndShortlyWithinNowThreshold() {
        val today = LocalDate(2023, 10, 27)
        val twoDaysAgo = LocalDate(2023, 10, 25)
        val inTwoDays = LocalDate(2023, 10, 29)
        // Past within nowThreshold (beyond yesterday) -> fuzzy "Recently"
        assertEquals("Recently", twoDaysAgo.formatReadableRelative(now = today, nowThreshold = 5.days, relativeThreshold = 10.days, locale = Locales.English))
        // Future within nowThreshold (beyond tomorrow) -> fuzzy "Shortly"
        assertEquals("Shortly", inTwoDays.formatReadableRelative(now = today, nowThreshold = 5.days, relativeThreshold = 10.days, locale = Locales.English))
    }

    @Test
    fun testLocalDate_defaultNowThresholdGivesExactCounts() {
        // With the default 1-day nowThreshold, "Recently"/"Shortly" are never used -> exact counts.
        val today = LocalDate(2023, 10, 27)
        val twoDaysAgo = LocalDate(2023, 10, 25)
        val inTwoDays = LocalDate(2023, 10, 29)
        assertEquals("2 days ago", twoDaysAgo.formatReadableRelative(now = today, relativeThreshold = 10.days, locale = Locales.English))
        assertEquals("in 2 days", inTwoDays.formatReadableRelative(now = today, relativeThreshold = 10.days, locale = Locales.English))
    }

    @Test
    fun testLocalTimeRelative() {
        val now = LocalTime(12, 0)
        val then = LocalTime(12, 5)
        
        assertEquals("in 5 minutes", then.formatReadableRelative(now = now, locale = Locales.English))
    }

    @Test
    fun testPast_years() {
        assertEquals("2 years ago", (now - (2 * 365).days).formatReadableRelative(now = now, locale = Locales.English, relativeThreshold = Duration.INFINITE))
    }

    @Test
    fun testFrench() {
        assertEquals("il y a 8 minutes", (now - 8.minutes).formatReadableRelative(now = now, locale = Locales.French))
        assertEquals("à l'instant", (now - 3.seconds).formatReadableRelative(now = now, locale = Locales.French))
    }

    @Test
    fun testSouthAfricanEnglish_nowNow() {
        assertEquals("now now", (now - 3.seconds).formatReadableRelative(now = now, locale = Locales.SouthAfricanEnglish))
    }

    // Regression coverage for the date-units shortcut resolving its config from
    // relativeDateStyle (was relativeTimeStyle). The Today/Tomorrow/Yesterday strings
    // are currently style-invariant in every locale, so these tests pin intended
    // behavior rather than reproduce a pre-fix output difference.

    @Test
    fun testDayStrings_mixedStyles() {
        val reference = LocalDateTime(2023, 10, 27, 12, 0).toInstant(tz)
        val tomorrow = reference + 25.hours
        val yesterday = reference - 25.hours

        assertEquals("Tomorrow", tomorrow.formatReadableRelative(now = reference, relativeDateStyle = RelativeStyle.Short, relativeTimeStyle = RelativeStyle.Long, locale = Locales.English, timeZone = tz))
        assertEquals("Yesterday", yesterday.formatReadableRelative(now = reference, relativeDateStyle = RelativeStyle.Short, relativeTimeStyle = RelativeStyle.Long, locale = Locales.English, timeZone = tz))
        assertEquals("Tomorrow", tomorrow.formatReadableRelative(now = reference, relativeDateStyle = RelativeStyle.Long, relativeTimeStyle = RelativeStyle.Short, locale = Locales.English, timeZone = tz))
        assertEquals("Yesterday", yesterday.formatReadableRelative(now = reference, relativeDateStyle = RelativeStyle.Long, relativeTimeStyle = RelativeStyle.Short, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testDateOnlyMode_noneTimeStyle() {
        val reference = LocalDateTime(2023, 10, 27, 12, 0).toInstant(tz)

        // relativeTimeStyle = None forces date units even within the same day
        assertEquals("Today", (reference + 2.hours).formatReadableRelative(now = reference, relativeTimeStyle = RelativeStyle.None, locale = Locales.English, timeZone = tz))
        assertEquals("Tomorrow", (reference + 25.hours).formatReadableRelative(now = reference, relativeTimeStyle = RelativeStyle.None, locale = Locales.English, timeZone = tz))
        assertEquals("Yesterday", (reference - 25.hours).formatReadableRelative(now = reference, relativeTimeStyle = RelativeStyle.None, locale = Locales.English, timeZone = tz))

        // beyond the shortcut, still date units, styled by relativeDateStyle
        assertEquals("5 days ago", (reference - 5.days).formatReadableRelative(now = reference, relativeTimeStyle = RelativeStyle.None, locale = Locales.English, timeZone = tz, relativeThreshold = 10.days))
        assertEquals("5d", (reference - 5.days).formatReadableRelative(now = reference, relativeDateStyle = RelativeStyle.Short, relativeTimeStyle = RelativeStyle.None, locale = Locales.English, timeZone = tz, relativeThreshold = 10.days))
    }

    @Test
    fun testTimeOnlyMode_noneDateStyle_skipsDayShortcut() {
        // 20h back crosses local midnight (daysDelta = -1) but date units are disabled
        val reference = LocalDateTime(2023, 10, 27, 8, 0).toInstant(tz)
        assertEquals("20 hours ago", (reference - 20.hours).formatReadableRelative(now = reference, relativeDateStyle = RelativeStyle.None, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testDayBoundary_localDateDisagreesWithElapsedTime() {
        // Same 20-hour delta: crossing local midnight yields "Yesterday",
        // staying within the same local date yields time units.
        val morning = LocalDateTime(2023, 10, 27, 8, 0).toInstant(tz)
        assertEquals("Yesterday", (morning - 20.hours).formatReadableRelative(now = morning, locale = Locales.English, timeZone = tz))

        val evening = LocalDateTime(2023, 10, 27, 22, 0).toInstant(tz)
        assertEquals("20 hours ago", (evening - 20.hours).formatReadableRelative(now = evening, locale = Locales.English, timeZone = tz))

        // The same instant pair flips with the timezone's day boundary:
        // 08:00Z vs 12:00Z-the-day-before is "Yesterday" in UTC but the same
        // local date (00:00 vs 20:00) in UTC+12.
        val past = morning - 20.hours
        assertEquals("Yesterday", past.formatReadableRelative(now = morning, locale = Locales.English, timeZone = TimeZone.UTC))
        assertEquals("20 hours ago", past.formatReadableRelative(now = morning, locale = Locales.English, timeZone = TimeZone.of("UTC+12")))
    }

    @Test
    fun testRelativeThreshold_strictBoundary() {
        // exactly 3 days: threshold check is strict >, so it stays relative
        assertEquals("3 days ago", (now - 3.days).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz))

        // just over falls back to absolute formatting
        val over = (now - 3.days - 1.seconds).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz)
        assertTrue(over.contains("2024"), "expected an absolute date, got: $over")
    }

    // ── LocalTime direction (RelativeDirection) ────────────────────────────────

    @Test
    fun testLocalTimeDirection_midnightCrossing() {
        val nowT = LocalTime(23, 0)
        val thisT = LocalTime(1, 0) // fwd = +2h
        val big = 24.hours // avoid the 3h default relativeThreshold falling back to absolute

        assertEquals("22 hours ago", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Present, relativeThreshold = big, locale = Locales.English))
        assertEquals("22 hours ago", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Past, relativeThreshold = big, locale = Locales.English))
        assertEquals("in 2 hours", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Future, relativeThreshold = big, locale = Locales.English))
        assertEquals("in 2 hours", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Nearest, relativeThreshold = big, locale = Locales.English))
        // default direction is Nearest
        assertEquals("in 2 hours", thisT.formatReadableRelative(now = nowT, relativeThreshold = big, locale = Locales.English))
    }

    @Test
    fun testLocalTimeDirection_allFourDistinct() {
        // now=01:00, this=23:00 -> fwd = +22h; the pair where all four modes differ
        val nowT = LocalTime(1, 0)
        val thisT = LocalTime(23, 0)
        val big = 24.hours

        assertEquals("in 22 hours", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Present, relativeThreshold = big, locale = Locales.English))
        assertEquals("2 hours ago", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Past, relativeThreshold = big, locale = Locales.English))
        assertEquals("in 22 hours", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Future, relativeThreshold = big, locale = Locales.English))
        assertEquals("2 hours ago", thisT.formatReadableRelative(now = nowT, direction = RelativeDirection.Nearest, relativeThreshold = big, locale = Locales.English))
    }

    @Test
    fun testLocalTimeDirection_sameDayUnaffected() {
        // Small forward same-day delta: Present/Future/Nearest agree, and the new Nearest default
        // matches the old linear behaviour. (Past deliberately wraps to the previous occurrence.)
        val nowT = LocalTime(12, 0)
        val thisT = LocalTime(12, 5)
        for (d in listOf(RelativeDirection.Present, RelativeDirection.Future, RelativeDirection.Nearest)) {
            assertEquals("in 5 minutes", thisT.formatReadableRelative(now = nowT, direction = d, locale = Locales.English), "mode $d")
        }
        // default direction (Nearest) is unchanged from the previous linear behaviour here
        assertEquals("in 5 minutes", thisT.formatReadableRelative(now = nowT, locale = Locales.English))
    }

    @Test
    fun testLocalTimeDirection_sameTimeIsJustNow() {
        val t = LocalTime(9, 30)
        for (d in RelativeDirection.entries) {
            assertEquals("just now", t.formatReadableRelative(now = t, direction = d, locale = Locales.English), "mode $d")
        }
    }

    @Test
    fun testLocalTimeAnchored_calendarAware() {
        val anchor = LocalDateTime(2023, 10, 27, 23, 0).toInstant(tz) // 23:00 UTC
        val oneAm = LocalTime(1, 0)

        // Future resolves to next-day 01:00 (+2h, crosses midnight) -> "Tomorrow"
        assertEquals("Tomorrow", oneAm.formatReadableRelative(now = anchor, timeZone = tz, direction = RelativeDirection.Future, locale = Locales.English))
        // Past resolves to same-day 01:00 (-22h, same date) -> time units
        assertEquals("22 hours ago", oneAm.formatReadableRelative(now = anchor, timeZone = tz, direction = RelativeDirection.Past, locale = Locales.English))

        // 23:30 Past resolves to the previous day -> "Yesterday" (the payoff of anchoring)
        val elevenThirty = LocalTime(23, 30)
        assertEquals("Yesterday", elevenThirty.formatReadableRelative(now = anchor, timeZone = tz, direction = RelativeDirection.Past, locale = Locales.English))
    }

    @Test
    fun testLocale_id_ms_sw_reachTheirOwnTranslations() {
        // Regression: id/ms/sw had full config entries but were missing from the supported-tag
        // allow-list, so relativeTimeConfigFor fell through to English.
        assertEquals("baru saja", (now - 3.seconds).formatReadableRelative(now = now, locale = Locales.Indonesian, timeZone = tz))
        assertEquals("baru sahaja", (now - 3.seconds).formatReadableRelative(now = now, locale = Locales.Malay, timeZone = tz))
        assertEquals("sasa hivi", (now - 3.seconds).formatReadableRelative(now = now, locale = Locales.Swahili, timeZone = tz))
        // and a non-"now" value exercises the past template + units
        assertEquals("8 menit yang lalu", (now - 8.minutes).formatReadableRelative(now = now, locale = Locales.Indonesian, timeZone = tz))
    }

    @Test
    fun testNordicRelative_nowReachTheirOwnTranslations() {
        // da/nb/nn/sv/is were allow-listed but had no config() -> rendered English. Now translated.
        assertEquals("for 8 minutter siden", (now - 8.minutes).formatReadableRelative(now = now, locale = Locales.Danish, timeZone = tz))
        assertEquals("för 8 minuter sedan", (now - 8.minutes).formatReadableRelative(now = now, locale = Locales.Swedish, timeZone = tz))
        assertEquals("fyrir 8 mínútum síðan", (now - 8.minutes).formatReadableRelative(now = now, locale = Locales.Icelandic, timeZone = tz))

        val reference = LocalDateTime(2023, 10, 27, 12, 0).toInstant(tz)
        assertEquals("I morgen", (reference + 25.hours).formatReadableRelative(now = reference, locale = Locales.NorwegianBokmal, timeZone = tz))
        assertEquals("I går", (reference - 25.hours).formatReadableRelative(now = reference, locale = Locales.Danish, timeZone = tz))
        assertEquals("I morgon", (reference + 25.hours).formatReadableRelative(now = reference, locale = Locales.Swedish, timeZone = tz))
    }

    // ── Absolute-fallback + future multi-day coverage (GAP-3) ──────────────────

    @Test
    fun testLocalDate_absoluteFallbackBeyondThreshold() {
        // 5 days > default 3-day relativeThreshold -> absolute date via this.format(dateStyle)
        val today = LocalDate(2023, 10, 27)
        val longAgo = LocalDate(2023, 10, 22)
        val result = longAgo.formatReadableRelative(now = today, locale = Locales.English)
        assertTrue(result.contains("2023"), "expected an absolute date, got: $result")
    }

    @Test
    fun testLocalTime_absoluteFallbackBeyondThreshold() {
        // 5h apart > default 3h relativeThreshold -> absolute time (has a ':' separator), not "in 5 hours"
        val nowT = LocalTime(12, 0)
        val laterT = LocalTime(17, 0)
        val result = laterT.formatReadableRelative(now = nowT, locale = Locales.English)
        assertTrue(result.contains(":"), "expected an absolute time fallback, got: $result")
    }

    @Test
    fun testInstant_futureMultiDayDateUnits() {
        // Future multi-day via the formatter (not the +1 "Tomorrow" shortcut)
        assertEquals("in 2 days", (now + 2.days).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz, relativeThreshold = 10.days))
        assertEquals("in 5 days", (now + 5.days).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz, relativeThreshold = 10.days))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        // Test deprecated toReadableRelative overloads (locale-first and now-first)
        assertEquals("just now", now.toReadableRelative(Locales.English, now = now, timeZone = tz))
        assertEquals("5 days ago", (now - 5.days).toReadableRelative(now = now, relativeDateStyle = RelativeStyle.Long, timeZone = TimeZone.UTC, locale = Locales.English, relativeThreshold = 10.days))
        
        val today = LocalDate(2023, 10, 27)
        assertEquals("Today", today.toReadableRelative(Locales.English, now = today))

        val nowTime = LocalTime(12, 0)
        val thenTime = LocalTime(12, 5)
        assertEquals("in 5 minutes", thenTime.toReadableRelative(Locales.English, now = nowTime))
    }
}
