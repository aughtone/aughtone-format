package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Plurals
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

open class TextResource0en : TextResource {
    override val am: Value by lazy { Value("am") }
    override val pm: Value by lazy { Value("pm") }
    override val time_in_past: Value by lazy { Value("%1 ago") }
    override val time_in_future: Value by lazy { Value("in %1") }
    override val time_now: Value by lazy { Value("now") }
    override val seconds: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "second",
                Plurals.Type.Other to "seconds",
            )
        )
    }
    override val seconds_future: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val seconds_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val minutes: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "minute",
                Plurals.Type.Other to "minutes",
            )
        )
    }
    override val minutes_future: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val minutes_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val hours: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "hour",
                Plurals.Type.Other to "hours",
            )
        )
    }
    override val hours_future: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val hours_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val days: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "day",
                Plurals.Type.Other to "days",
            )
        )
    }
    override val days_future: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val days_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val weeks: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "week",
                Plurals.Type.Other to "weeks",
            )
        )
    }
    override val weeks_future: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val weeks_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val months: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "month",
                Plurals.Type.Other to "months",
            )
        )
    }
    override val months_future: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val months_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val years: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "year",
                Plurals.Type.Other to "years",
            )
        )
    }
    override val years_future: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
    override val years_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.Other to "",
            )
        )
    }
}
