package io.github.aughtone.toolbox

/**
 * Formats this Int as a currency string.
 *
 * This function assumes the Int represents the currency amount in cents.
 * The output will be prefixed with a dollar sign ($) and formatted to the specified number of decimal places.
 *
 * @param scale The number of decimal places to display. Defaults to 2.
 * @return A String representing the Int formatted as currency.
 *
 * @sample io.github.aughtone.toolbox.IntExtensionsTest.formatCurrency
 */
@ExperimentalMultiplatform()
fun Int.formatCurrency(scale: Int = 2) = "$%.${scale}f".format(this / 100)
