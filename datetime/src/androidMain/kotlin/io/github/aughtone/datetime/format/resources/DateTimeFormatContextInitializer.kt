package io.github.aughtone.datetime.format.resources

import android.content.Context
import androidx.startup.Initializer

// See: https://developer.android.com/topic/libraries/app-startup
// See: https://www.droidcon.com/2023/08/22/how-to-avoid-asking-for-android-context-in-kotlin-multiplatform-libraries-api/

internal lateinit var dateTimeFormatApplicationContext: Context
    private set

/**
 * androidx App Startup [Initializer] that captures the application [Context] at app
 * startup, so the module can format dates and times on Android without asking the
 * caller for a context.
 *
 * The App Startup library discovers and runs this automatically via the manifest
 * merge; consumers of the library normally do not reference this class directly.
 *
 * @see <a href="https://developer.android.com/topic/libraries/app-startup">App Startup</a>
 */
class DateTimeFormatInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        dateTimeFormatApplicationContext = context.applicationContext
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
