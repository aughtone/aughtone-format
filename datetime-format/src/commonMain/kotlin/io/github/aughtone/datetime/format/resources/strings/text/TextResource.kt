package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Plurals
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

interface TextResource {
    val am: Value
    val pm: Value
    val time_in_past: Value
    val time_in_future: Value
    val time_now: Value
    val seconds: Plurals
    val seconds_future: Plurals
    val seconds_past: Plurals
    val minutes: Plurals
    val minutes_future: Plurals
    val minutes_past: Plurals
    val hours: Plurals
    val hours_future: Plurals
    val hours_past: Plurals
    val days: Plurals
    val days_future: Plurals
    val days_past: Plurals
    val weeks: Plurals
    val weeks_future: Plurals
    val weeks_past: Plurals
    val months: Plurals
    val months_future: Plurals
    val months_past: Plurals
    val years: Plurals
    val years_future: Plurals
    val years_past: Plurals
}
