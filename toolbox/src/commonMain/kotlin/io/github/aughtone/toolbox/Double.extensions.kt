package io.github.aughtone.toolbox

/**
 * Formats this Double as a currency string.
 *
 * The output will be prefixed with a dollar sign ($) and formatted to the specified number of decimal places.
 *
 * @param scale The number of decimal places to display. Defaults to 2.
 * @return A String representing the Double formatted as currency.
 *
 * @sample io.github.aughtone.toolbox.DoubleExtensionsTest.formatCurrency
 */
@ExperimentalMultiplatform()
fun Double.formatCurrency(scale: Int = 2) = "$%.${scale}f".format(this)
