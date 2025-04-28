package io.github.aughtone.datetime.format.resources

import android.text.format.DateFormat

internal actual fun isPlatform24HourSettingEnabled(): Boolean? =
    DateFormat.is24HourFormat(dateTimeFormatApplicationContext)
