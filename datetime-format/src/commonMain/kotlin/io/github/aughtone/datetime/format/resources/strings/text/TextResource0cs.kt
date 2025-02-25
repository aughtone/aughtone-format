package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Plurals
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

class TextResource0cs : TextResource0en() {
    override val time_ago: Value by lazy { Value("před %1") }
    override val time_in_future: Value by lazy { Value("za %1") }
    override val time_now: Value by lazy { Value("nyní") }
    override val seconds: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "sekunda",
                Plurals.Type.Few to "sekundy",
                Plurals.Type.Other to "sekund",
            )
        )
    }
    override val seconds_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "sekundou",
                Plurals.Type.Few to "sekundami",
                Plurals.Type.Other to "sekundami",
            )
        )
    }
    override val minutes: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "minuta",
                Plurals.Type.Few to "minuty",
                Plurals.Type.Other to "minut",
            )
        )
    }
    override val minutes_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "minutou",
                Plurals.Type.Few to "minutami",
                Plurals.Type.Other to "minutami",
            )
        )
    }
    override val hours: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "hodina",
                Plurals.Type.Few to "hodiny",
                Plurals.Type.Other to "hodin",
            )
        )
    }
    override val hours_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "hodinou",
                Plurals.Type.Few to "hodinami",
                Plurals.Type.Other to "hodinami",
            )
        )
    }
    override val days: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "den",
                Plurals.Type.Few to "dny",
                Plurals.Type.Other to "dní",
            )
        )
    }
    override val days_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "dnem",
//                Plurals.Type.Few to "dny",
                Plurals.Type.Other to "dny",
            )
        )
    }
    override val weeks: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "týden",
                Plurals.Type.Few to "týdny",
                Plurals.Type.Other to "týdnů",
            )
        )
    }
    override val weeks_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "týdnem",
                Plurals.Type.Few to "týdny",
                Plurals.Type.Other to "týdny",
            )
        )
    }
    override val months: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "měsíc",
                Plurals.Type.Few to "měsíce",
                Plurals.Type.Other to "měsíců",
            )
        )
    }
    override val months_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "měsícem",
                Plurals.Type.Few to "měsíci",
                Plurals.Type.Other to "měsíci",
            )
        )
    }
    override val years: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "rok",
                Plurals.Type.Few to "roky",
                Plurals.Type.Other to "let",
            )
        )
    }
    override val years_past: Plurals by lazy {
        Plurals(
            mapOf(
                Plurals.Type.One to "rokem",
                Plurals.Type.Few to "roky",
                Plurals.Type.Other to "lety",
            )
        )
    }
}
