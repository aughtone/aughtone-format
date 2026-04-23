package io.github.aughtone.datetime.format.resources.formats

data class TimePatterns(
    val short: String,
    val medium: String,
    val long: String,
    val full: String
)

// Patterns are now defined directly in the map to reduce constant overhead.

internal val localeTimePatterns = mapOf(
    "af" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "ar" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "az" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "be" to lazy { TimePatterns(
        full = "HH:mm:ss, zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "bg" to lazy { TimePatterns(
        full = "H:mm:ss 'ч'. zzzz",
        long = "H:mm:ss 'ч'. z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "ca" to lazy { TimePatterns(
        full = "H:mm:ss (zzzz)",
        long = "H:mm:ss z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "cs" to lazy { TimePatterns(
        full = "H:mm:ss, zzzz",
        long = "H:mm:ss z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "da" to lazy { TimePatterns(
        full = "HH.mm.ss zzzz",
        long = "HH.mm.ss z",
        medium = "HH.mm.ss",
        short = "HH.mm"
    ) },
    "de" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "el" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "en" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "es" to lazy { TimePatterns(
        full = "H:mm:ss (zzzz)",
        long = "H:mm:ss z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "et" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "eu" to lazy { TimePatterns(
        full = "HH:mm:ss (zzzz)",
        long = "HH:mm:ss (z)",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "fa" to lazy { TimePatterns(
        full = "H:mm:ss (zzzz)",
        long = "H:mm:ss (z)",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "fi" to lazy { TimePatterns(
        full = "H.mm.ss zzzz",
        long = "H.mm.ss z",
        medium = "H.mm.ss",
        short = "H.mm"
    ) },
    "fr" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "gl" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "he" to lazy { TimePatterns(
        full = "H:mm:ss zzzz",
        long = "H:mm:ss z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "hi" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "hr" to lazy { TimePatterns(
        full = "HH:mm:ss (zzzz)",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "hu" to lazy { TimePatterns(
        full = "H:mm:ss zzzz",
        long = "H:mm:ss z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "hy" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "id" to lazy { TimePatterns(
        full = "HH.mm.ss zzzz",
        long = "HH.mm.ss z",
        medium = "HH.mm.ss",
        short = "HH.mm"
    ) },
    "is" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "it" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "iu" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "ja" to lazy { TimePatterns(
        full = "H時mm分ss秒 zzzz",
        long = "H:mm:ss z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "ka" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "kk" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "ko" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "lt" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "lv" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "mk" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "ms" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "nb" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "nl" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "nn" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "no" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "pl" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "pt" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "ro" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "ru" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "sk" to lazy { TimePatterns(
        full = "H:mm:ss zzzz",
        long = "H:mm:ss z",
        medium = "H:mm:ss",
        short = "H:mm"
    ) },
    "sl" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "sq" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "sr" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "sv" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "sw" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "sv" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "th" to lazy { TimePatterns(
        full = "H นาฬิกา mm นาที ss วินาที zzzz",
        long = "H นาฬิกา mm นาที ss วินาที z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "tr" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "uk" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "uz" to lazy { TimePatterns(
        full = "H:mm:ss (zzzz)",
        long = "H:mm:ss (z)",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "vi" to lazy { TimePatterns(
        full = "HH:mm:ss zzzz",
        long = "HH:mm:ss z",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "zh" to lazy { TimePatterns(
        full = "zzzz HH:mm:ss",
        long = "z HH:mm:ss",
        medium = "HH:mm:ss",
        short = "HH:mm"
    ) },
    "US" to lazy { TimePatterns(
        short = "HH:mm",
        medium = "HH:mm:ss",
        long = "HH:mm:ss z",
        full = "HH:mm:ss zzzz"
    ) },
)
