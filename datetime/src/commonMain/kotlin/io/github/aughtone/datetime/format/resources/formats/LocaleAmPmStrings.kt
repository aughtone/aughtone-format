package io.github.aughtone.datetime.format.resources.formats

internal val localeAmPmStrings by lazy {
    mapOf(
        "US" to AmPmStrings(am = "AM", pm = "PM"),
        "CA" to AmPmStrings(am = "a.m.", pm = "p.m."),
        "GB" to AmPmStrings(am = "a.m.", pm = "p.m."),
        "ar" to AmPmStrings(am = "ص", pm = "م"),
        "es" to AmPmStrings(am = "a. m.", pm = "p. m."),
        "id" to AmPmStrings(am = "pagi", pm = "sore"),
        "iu" to AmPmStrings(am = "ᐅᓪᓛᒃᑯᑦ", pm = "ᐅᓐᓄᒃᑯᑦ"),
        "ja" to AmPmStrings(am = "午前", pm = "午後"),
        "ko" to AmPmStrings(am = "오전", pm = "오후"),
        "pt" to AmPmStrings(am = "a.m.", pm = "p.m."), // Inherited from en-CA
        "vi" to AmPmStrings(am = "SA", pm = "CH"),
        "zh" to AmPmStrings(am = "上午", pm = "下午")
    )
}
