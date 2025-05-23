package io.github.aughtone.datetime.format.resources

import androidx.compose.ui.text.intl.Locale

/**
 * Returns true if user preference is set to 24-hour format.
 *
 * **Note**: not all platforms have a way to determine if the users system is in 24 hour mode.
 * if the code is running on one of those platforms, this function will take a guess, based on
 * the local. If the local is not recognized, it will default to 12 hour formatting.
 *
 * @return true if 24 hour time format is selected, false otherwise.
 */
fun is24HourFormat(locale: Locale = Locale.current): Boolean {
    // XXX Not all platforms know if the users system is in 24 hour mode,
    //  so if null is returned, we'll take a guess based on locale, adn fall back to false.
    try {
        return isPlatform24HourSettingEnabled() ?: Resources.getClockHours(locale = locale).is24hour
    } catch (e: Exception) {
        // this exception would happen if the DateTimeFormatInitializer doesn't load on Android.
        // it also happens during a unit test, so we allow it to fall back.
        println(e.toString())
        return Resources.getClockHours(locale = locale).is24hour
    }
}

internal expect fun isPlatform24HourSettingEnabled(): Boolean?
