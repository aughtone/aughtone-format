package io.github.aughtone.datetime.format.resources

import android.provider.Settings
import android.text.format.DateFormat
import androidx.test.platform.app.InstrumentationRegistry
import io.github.aughtone.types.locale.localeFor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

/**
 * The 12/24-hour setting read through the app `Context`, run on a device or emulator.
 *
 * The test reads the device's setting and never changes it, so each run checks the one value the
 * device has. To check both, run it on a device set to 12-hour and on one set to 24-hour, or
 * change the setting (Settings, then System, then Date & time) and run it again.
 */
class ClockSettingAndroidTest {

    private val context get() = InstrumentationRegistry.getInstrumentation().targetContext

    /** 12-hour by CLDR's convention, so a `true` for it can only have come from the device. */
    private val twelveHourLocale = localeFor("en-US")!!

    /** 24-hour by CLDR's convention, so a `false` for it can only have come from the device. */
    private val twentyFourHourLocale = localeFor("de-DE")!!

    @Test
    fun theContextIsCapturedAtStartup() {
        // Reading it throws if App Startup never ran DateTimeFormatInitializer from the merged manifest.
        assertSame(context.applicationContext, dateTimeFormatApplicationContext)
    }

    @Test
    fun theDeviceSettingDecidesNotTheLocale() {
        val expected = when (Settings.System.getString(context.contentResolver, Settings.System.TIME_12_24)) {
            "24" -> true
            "12" -> false
            // Never set by the user: Android answers from the device's own locale.
            else -> DateFormat.is24HourFormat(context)
        }
        assertEquals(expected, isPlatform24HourSettingEnabled())
        assertEquals(expected, is24HourFormat(twelveHourLocale))
        assertEquals(expected, is24HourFormat(twentyFourHourLocale))
    }
}
