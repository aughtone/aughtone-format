package io.github.aughtone.datetime.format

/**
 * Indicates in what time frame the requested time unit needs
 * to be localised. Used to support grammar cases in languages like German.
 *
 * Ported from source: https://github.com/jacobras/Human-Readable
 */
enum class RelativeTime {
    Past, Present, Future
}
