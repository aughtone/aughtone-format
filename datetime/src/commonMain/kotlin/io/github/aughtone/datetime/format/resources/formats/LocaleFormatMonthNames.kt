package io.github.aughtone.datetime.format.resources.formats

import kotlinx.datetime.format.MonthNames

/**
 * Full month names in the form a language uses inside a date, for the languages where that
 * differs from the name of the month on its own: Russian writes "5 марта", not "5 Март".
 *
 * The month table in [localeMonthNamesSource] holds the dictionary form for these languages,
 * which is what a month shown alone needs. Languages not listed here use that table for both.
 * Keyed by language code. The names are the Unicode CLDR format-context month names (CLDR 48.0).
 */
internal val localeFormatMonthNames: Map<String, MonthNames> = mapOf(
    "cs" to MonthNames("ledna", "února", "března", "dubna", "května", "června", "července", "srpna", "září", "října", "listopadu", "prosince"),
    "fi" to MonthNames("tammikuuta", "helmikuuta", "maaliskuuta", "huhtikuuta", "toukokuuta", "kesäkuuta", "heinäkuuta", "elokuuta", "syyskuuta", "lokakuuta", "marraskuuta", "joulukuuta"),
    "pl" to MonthNames("stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "października", "listopada", "grudnia"),
    "ru" to MonthNames("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"),
    "uk" to MonthNames("січня", "лютого", "березня", "квітня", "травня", "червня", "липня", "серпня", "вересня", "жовтня", "листопада", "грудня"),
)
