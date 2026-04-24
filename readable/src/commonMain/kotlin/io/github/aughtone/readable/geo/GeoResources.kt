package io.github.aughtone.readable.geo

import io.github.aughtone.types.locale.Locale

internal object GeoResources {
    private val directionsMap = mapOf(
        "en" to listOf("N", "NE", "E", "SE", "S", "SW", "W", "NW"),
        "fr" to listOf("N", "NE", "E", "SE", "S", "SO", "O", "NO"),
        "es" to listOf("N", "NE", "E", "SE", "S", "SO", "O", "NO"),
        "de" to listOf("N", "NO", "O", "SO", "S", "SW", "W", "NW"),
        "it" to listOf("N", "NE", "E", "SE", "S", "SO", "O", "NO"),
        "pt" to listOf("N", "NE", "E", "SE", "S", "SO", "O", "NO"),
        "ca" to listOf("N", "NE", "E", "SE", "S", "SO", "O", "NO"),
        "gl" to listOf("N", "NL", "L", "SL", "S", "SO", "O", "NO"),
        "ro" to listOf("N", "NE", "E", "SE", "S", "SV", "V", "NV"),
        "ru" to listOf("С", "СВ", "В", "ЮВ", "Ю", "ЮЗ", "З", "СЗ"),
        "uk" to listOf("Пн", "ПнС", "С", "ПдС", "Пд", "ПдЗ", "З", "ПнЗ"),
        "be" to listOf("Пн", "ПнУ", "У", "ПдУ", "Пд", "ПдЗ", "З", "ПнЗ"),
        "bg" to listOf("С", "СИ", "И", "ЮИ", "Ю", "ЮЗ", "З", "СЗ"),
        "pl" to listOf("N", "NE", "E", "SE", "S", "SW", "W", "NW"),
        "cs" to listOf("S", "SV", "V", "JV", "J", "JZ", "Z", "SZ"),
        "sk" to listOf("S", "SV", "V", "JV", "J", "JZ", "Z", "SZ"),
        "hr" to listOf("N", "SI", "I", "JI", "S", "JZ", "Z", "SZ"),
        "sr" to listOf("С", "СИ", "И", "ЈИ", "Ј", "ЈЗ", "З", "СЗ"),
        "mk" to listOf("С", "СИ", "И", "ЈИ", "Ј", "ЈЗ", "З", "СЗ"),
        "sl" to listOf("S", "SV", "V", "JV", "J", "JZ", "Z", "SZ"),
        "lt" to listOf("Š", "ŠR", "R", "PR", "P", "PV", "V", "ŠV"),
        "lv" to listOf("Z", "ZA", "A", "DA", "D", "DR", "R", "ZR"),
        "sq" to listOf("V", "VL", "L", "JL", "J", "JP", "P", "VP"),
        "ja" to listOf("北", "北東", "東", "南東", "南", "南西", "西", "北西"),
        "zh" to listOf("北", "东北", "东", "东南", "南", "西南", "西", "西北"),
        "ko" to listOf("북", "북동", "동", "남동", "남", "남서", "서", "북서"),
        "ar" to listOf("ش", "ش.ق", "ق", "ج.ق", "ج", "ج.غ", "غ", "ش.غ"),
        "he" to listOf("צ", "צ.מ", "מ", "ד.מ", "ד", "ד.מ", "מ", "צ.מ"),
        "hi" to listOf("उ", "उ.पू", "पू", "द.पू", "द", "द.प", "प", "उ.प"),
        "fa" to listOf("ش", "ش‌ق", "ق", "ج‌ق", "ج", "ج‌غ", "غ", "ش‌غ"),
        "tr" to listOf("K", "KD", "D", "GD", "G", "GB", "B", "KB"),
        "az" to listOf("Şm", "ŞmŞ", "Ş", "CŞ", "C", "CQ", "Q", "ŞmQ"),
        "kk" to listOf("С", "СШ", "Ш", "ОШ", "О", "ОБ", "Б", "СБ"),
        "uz" to listOf("Sh", "ShSharq", "Sharq", "JSharq", "J", "JG", "G", "ShG"),
        "vi" to listOf("B", "ĐB", "Đ", "ĐN", "N", "TN", "T", "BT"),
        "th" to listOf("น", "ตอ.น", "ตอ", "ตอ.ต", "ต", "ตก.ต", "ตก", "ตก.น"),
        "id" to listOf("U", "TL", "T", "TG", "S", "BD", "B", "BL"),
        "ms" to listOf("U", "TL", "T", "TG", "S", "BD", "B", "BL"),
        "fi" to listOf("P", "KO", "I", "KA", "E", "LO", "L", "LU"),
        "et" to listOf("P", "PK", "I", "LK", "L", "LK", "L", "LK"), // Simplified
        "hu" to listOf("É", "ÉK", "K", "DK", "D", "DNY", "NY", "ÉNY"),
        "el" to listOf("Β", "ΒΑ", "Α", "ΝΑ", "Ν", "ΝΔ", "Δ", "ΒΔ"),
        "eu" to listOf("I", "IE", "E", "HE", "H", "HM", "M", "IM"),
        "is" to listOf("N", "NA", "A", "SA", "S", "SV", "V", "NV"),
        "sw" to listOf("K", "K Mash", "Mash", "K Kus", "Kus", "K Mag", "Mag", "K Kas"),
        "af" to listOf("N", "NO", "O", "SO", "S", "SW", "W", "NW"),
        "nl" to listOf("N", "NO", "O", "ZO", "Z", "ZW", "W", "NW")
    )

    fun cardinalDirectionsFor(locale: Locale): List<String> {
        val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
        var currentTag = fullTag
        while (currentTag.isNotEmpty()) {
            val res = directionsMap[currentTag]
            if (res != null) return res
            currentTag = currentTag.substringBeforeLast('-', "")
        }
        return directionsMap["en"]!!
    }
}
