#!/bin/bash
LOCALES=("af" "ar" "az" "be" "bg" "ca" "cs" "da" "de" "el" "en" "es" "et" "eu" "fa" "fi" "fr" "gl" "he" "hi" "hr" "hu" "hy" "id" "is" "it" "iu" "ja" "ka" "kk" "ko" "lt" "lv" "mk" "ms" "nb" "nl" "nn" "no" "pl" "pt" "ro" "ru" "sk" "sl" "sq" "sr" "sv" "sw" "th" "tr" "uk" "uz" "vi" "zh")
FILE="datetime/src/commonMain/kotlin/io/github/aughtone/datetime/format/resources/formats/LocaleDayOfWeekNamesSource.kt"
for loc in "${LOCALES[@]}"; do
  if ! grep -q "\"$loc\"" "$FILE"; then
    echo "$loc is missing"
  fi
done
