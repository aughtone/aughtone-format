package io.github.aughtone.datetime.format.resources.strings

import io.github.aughtone.datetime.format.RelativeTime
import io.github.aughtone.datetime.format.format
import io.github.aughtone.datetime.format.resources.Resources

// XXX This needs tobe refactored to select resources based on locale.
internal enum class TimeUnitPlurals(
    val past: StringItem.Plurals,
    val present: StringItem.Plurals,
    val future: StringItem.Plurals,
) {
    Seconds(
        past = Resources.getText().seconds_past,//Res.plurals.seconds_past,
        present = Resources.getText().seconds,
        future = Resources.getText().seconds_future
    ),
    Minutes(
        past = Resources.getText().minutes_past,
        present = Resources.getText().minutes,
        future = Resources.getText().minutes_future
    ),
    Hours(
        past = Resources.getText().hours_past,
        present = Resources.getText().hours,
        future = Resources.getText().hours_future
    ),
    Days(
        past = Resources.getText().days_past,
        present = Resources.getText().days,
        future = Resources.getText().days_future
    ),
    Weeks(
        past = Resources.getText().weeks_past,
        present = Resources.getText().weeks,
        future = Resources.getText().weeks_future
    ),
    Months(
        past = Resources.getText().months_past,
        present = Resources.getText().months,
        future = Resources.getText().months_future
    ),
    Years(
        past = Resources.getText().years_past,
        present = Resources.getText().years,
        future = Resources.getText().years_future
    );
    private fun getPluralString(plural: StringItem.Plurals, quantity: Int): String =
        with(getTypeForQuantity(quantity)) {
            plural.items[this]
                ?: plural.items[StringItem.Plurals.Type.Other]
                ?: error("Quantity string ID=`${plural::class.simpleName}` does not have the pluralization $this for quantity $quantity!")
        }

    private fun getTypeForQuantity(quantity: Int) = when (quantity) {
        0 -> StringItem.Plurals.Type.Zero
        1 -> StringItem.Plurals.Type.One
        2 -> StringItem.Plurals.Type.Two
        in 2..4 -> StringItem.Plurals.Type.Few
        else -> StringItem.Plurals.Type.Many
    }

    fun format(value: Int, relativeTime: RelativeTime): String =
        when (relativeTime) {
            RelativeTime.Past -> getPluralString(plural = past, quantity = value).ifBlank {
                getPluralString(
                    plural = present,
                    quantity = value
                )
            }.format(value)
            RelativeTime.Present -> getPluralString(plural = present, quantity = value).ifBlank {
                getPluralString(
                    plural = present,
                    quantity = value
                )
            }.format(value)
            RelativeTime.Future -> getPluralString(plural = future, quantity = value).ifBlank {
                getPluralString(
                    plural = present,
                    quantity = value
                )
            }.format(value)
        }

}
