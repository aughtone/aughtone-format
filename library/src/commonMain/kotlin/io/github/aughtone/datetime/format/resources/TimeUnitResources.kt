package io.github.aughtone.datetime.format.resources

import io.github.aughtone.datetime.format.RelativeTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.getPluralString


internal enum class TimeUnitResources(
    val past: PluralStringResource,
    val present: PluralStringResource,
    val future: PluralStringResource,
) {
    Seconds(
        past = Res.plurals.seconds_past,
        present = Res.plurals.seconds,
        future = Res.plurals.seconds_future
    ),
    Minutes(
        past = Res.plurals.minutes_past,
        present = Res.plurals.minutes,
        future = Res.plurals.minutes_future
    ),
    Hours(
        past = Res.plurals.hours_past,
        present = Res.plurals.hours,
        future = Res.plurals.hours_future
    ),
    Days(
        past = Res.plurals.days_past,
        present = Res.plurals.days,
        future = Res.plurals.days_future
    ),
    Weeks(
        past = Res.plurals.weeks_past,
        present = Res.plurals.weeks,
        future = Res.plurals.weeks_future
    ),
    Months(
        past = Res.plurals.months_past,
        present = Res.plurals.months,
        future = Res.plurals.months_future
    ),
    Years(
        past = Res.plurals.years_past,
        present = Res.plurals.years,
        future = Res.plurals.years_future
    );

    fun format(value: Int, relativeTime: RelativeTime): String =
        runBlocking(context = Dispatchers.IO) {

//            Years.past.
            // XXX not sure what the fallback logic is here. Current resource handling doesn't work
            //  the same as the "io.github.skeptick.libres" library.
            return@runBlocking when (relativeTime) {
                RelativeTime.Past -> getPluralString(
                    resource = past,
                    quantity = value
                ).ifBlank {
                    getPluralString(
                        present,
                        value
                    )
                } //past().optionallyFormat(value) ?: present().format(value)
                RelativeTime.Present -> getPluralString(
                    resource = present,
                    quantity = value
                ).ifBlank {
                    getPluralString(
                        present,
                        value
                    )
                } // present().format(value)
                RelativeTime.Future -> getPluralString(
                    resource = future,
                    quantity = value
                ).ifBlank {
                    getPluralString(
                        present,
                        value
                    )
                } //future().optionallyFormat(value) ?: present().format(value)
            }
        }
}
