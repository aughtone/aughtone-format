package io.github.aughtone.datetime.format.resources.formats

data class TimePatterns(
    val short: String,
    val medium: String,
    val long: String,
    val full: String
)

private const val TIME_PATTERN_HH_MM = "HH:mm"
private const val TIME_PATTERN_HH_MM_SS = "HH:mm:ss"
private const val TIME_PATTERN_HH_MM_SS_Z = "HH:mm:ss z"
private const val TIME_PATTERN_HH_MM_SS_ZZZZ = "HH:mm:ss zzzz"

private const val TIME_PATTERN_H_MM = "H:mm"
private const val TIME_PATTERN_H_MM_SS = "H:mm:ss"
private const val TIME_PATTERN_H_MM_SS_Z = "H:mm:ss z"
private const val TIME_PATTERN_H_MM_SS_ZZZZ = "H:mm:ss zzzz"

private const val TIME_PATTERN_HH_DOT_MM = "HH.mm"
private const val TIME_PATTERN_HH_DOT_MM_SS = "HH.mm.ss"
private const val TIME_PATTERN_HH_DOT_MM_SS_Z = "HH.mm.ss z"
private const val TIME_PATTERN_HH_DOT_MM_SS_ZZZZ = "HH.mm.ss zzzz"

// French-specific (using 'h' literal for 24-hour patterns)
private const val TIME_PATTERN_HH_H_MM = "HH 'h' mm"
private const val TIME_PATTERN_HH_H_MM_SS = "HH 'h' mm:ss"
private const val TIME_PATTERN_HH_H_MM_SS_Z = "HH 'h' mm:ss z"
private const val TIME_PATTERN_HH_H_MM_SS_ZZZZ = "HH 'h' mm:ss zzzz"

internal val localeTimePatterns = mapOf(
    "US" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "CA" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "fr-CA" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "GB" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "ar" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "cs" to lazy { TimePatterns(
        short = TIME_PATTERN_H_MM, medium = TIME_PATTERN_H_MM_SS, long = TIME_PATTERN_H_MM_SS_Z, full = TIME_PATTERN_H_MM_SS_ZZZZ
    ) },
    "de" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "es" to lazy { TimePatterns(
        short = TIME_PATTERN_H_MM, medium = TIME_PATTERN_H_MM_SS, long = TIME_PATTERN_H_MM_SS_Z, full = TIME_PATTERN_H_MM_SS_ZZZZ
    ) },
    "fi" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_DOT_MM, medium = TIME_PATTERN_HH_DOT_MM_SS, long = TIME_PATTERN_HH_DOT_MM_SS_Z, full = TIME_PATTERN_HH_DOT_MM_SS_ZZZZ
    ) },
    "fr" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "id" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_DOT_MM, medium = TIME_PATTERN_HH_DOT_MM_SS, long = TIME_PATTERN_HH_DOT_MM_SS_Z, full = TIME_PATTERN_HH_DOT_MM_SS_ZZZZ
    ) },
    "it" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "iu" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "ja" to lazy { TimePatterns(
        short = TIME_PATTERN_H_MM, medium = TIME_PATTERN_H_MM_SS, long = TIME_PATTERN_H_MM_SS_Z, full = TIME_PATTERN_H_MM_SS_ZZZZ
    ) },
    "kk" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "ko" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "nl" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "pl" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "pt" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "ru" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "tr" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "uk" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "uz" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "vi" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) },
    "zh" to lazy { TimePatterns(
        short = TIME_PATTERN_HH_MM, medium = TIME_PATTERN_HH_MM_SS, long = TIME_PATTERN_HH_MM_SS_Z, full = TIME_PATTERN_HH_MM_SS_ZZZZ
    ) }
)
