package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.timezone.windowsZoneAliases
import io.github.aughtone.datetime.format.resources.timezone.windowsZoneMapping
import kotlinx.datetime.TimeZone

/**
 * The IANA time-zone identifier for a Windows time-zone name, as Unicode CLDR maps it:
 * `"Eastern Standard Time"` is `"America/New_York"`, and in Canada `"America/Toronto"`.
 *
 * Windows names appear where Windows software writes a zone, such as the `TZID` of an iCalendar
 * file from Outlook or Exchange; kotlinx-datetime's [TimeZone.of] accepts only IANA identifiers.
 * The result is the zone's current IANA name ("Asia/Kolkata"), for [TimeZone.of]. The answer is
 * the same on every platform, from bundled data.
 *
 * ```kotlin
 * TimeZone.ianaIdForWindowsName("Eastern Standard Time")       // "America/New_York"
 * TimeZone.ianaIdForWindowsName("Eastern Standard Time", "CA") // "America/Toronto"
 * ```
 *
 * @param windowsName The Windows name, exactly as Windows writes it (the match is case-sensitive).
 * @param territory An optional ISO 3166 region code, in either case. Where CLDR maps the name for
 *   that territory its zone is used; otherwise, and when `null`, the name's default zone.
 * @return The IANA identifier, or `null` when CLDR does not list [windowsName]. This never throws.
 * @see windowsNameForIanaId for the other direction.
 */
fun TimeZone.Companion.ianaIdForWindowsName(windowsName: String, territory: String? = null): String? {
    val entries = windowsZoneMapping[windowsName] ?: return null
    val region = territory?.uppercase()
    val ids = entries.firstOrNull { it.first == region }?.second ?: entries.first { it.first == "001" }.second
    return ids.substringBefore(' ')
}

/**
 * The Windows time-zone name for an IANA identifier, as Unicode CLDR maps it:
 * `"America/Toronto"` is `"Eastern Standard Time"`. Use it to write a zone that Windows software
 * reads. The answer is the same on every platform, from bundled data.
 *
 * @param ianaId An IANA identifier, as [TimeZone.id] gives it. A zone's older names and links
 *   ("Asia/Calcutta", "US/Eastern") are accepted too.
 * @return The Windows name, or `null` when CLDR maps no Windows name to [ianaId]. This never
 *   throws.
 * @see ianaIdForWindowsName for the other direction.
 */
fun TimeZone.Companion.windowsNameForIanaId(ianaId: String): String? =
    windowsNameByIanaId[windowsZoneAliases[ianaId] ?: ianaId]

/** Every IANA identifier CLDR lists, to its Windows name, built on first use. */
private val windowsNameByIanaId: Map<String, String> by lazy {
    buildMap {
        for ((name, entries) in windowsZoneMapping) {
            // The default zone first, so an identifier listed under two names takes its default's.
            for ((_, ids) in entries.sortedBy { it.first != "001" }) {
                for (id in ids.split(' ')) if (id.isNotEmpty() && id !in this) put(id, name)
            }
        }
    }
}

/** Every Windows name CLDR maps. */
internal fun windowsZoneNames(): Set<String> = windowsZoneMapping.keys
