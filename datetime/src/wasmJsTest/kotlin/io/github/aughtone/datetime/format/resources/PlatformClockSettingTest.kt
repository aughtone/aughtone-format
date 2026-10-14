package io.github.aughtone.datetime.format.resources

import kotlin.test.Test
import kotlin.test.assertNotNull

class PlatformClockSettingTest {

    @Test
    fun theRuntimeReportsItsHourCycle() {
        // Every browser and Node expose Intl, so the probe has an answer here; which answer
        // depends on the machine's locale, so only its presence is checked.
        assertNotNull(isPlatform24HourSettingEnabled(), "Intl hour cycle was not detected")
    }
}
