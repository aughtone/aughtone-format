package io.github.aughtone.datetime.format.resources

import android.content.Context
import androidx.startup.Initializer

// See: https://developer.android.com/topic/libraries/app-startup
// See: https://www.droidcon.com/2023/08/22/how-to-avoid-asking-for-android-context-in-kotlin-multiplatform-libraries-api/

internal lateinit var dateTimeFormatApplicationContext: Context
    private set

class DateTimeFormatInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        dateTimeFormatApplicationContext = context.applicationContext
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
