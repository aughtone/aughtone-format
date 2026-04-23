#!/bin/bash

LOCALES=("af" "ar" "az" "be" "bg" "ca" "cs" "da" "de" "el" "en" "es" "et" "eu" "fa" "fi" "fr" "gl" "he" "hi" "hr" "hu" "hy" "id" "is" "it" "iu" "ja" "ka" "kk" "ko" "lt" "lv" "mk" "ms" "nb" "nl" "nn" "no" "pl" "pt" "ro" "ru" "sk" "sl" "sq" "sr" "sv" "sw" "th" "tr" "uk" "uz" "vi" "zh")

# Base URL for CLDR JSON
BASE_URL="https://raw.githubusercontent.com/unicode-org/cldr-json/master/cldr-json/cldr-dates-full/main"

for loc in "${LOCALES[@]}"; do
  echo "Fetching $loc..."
  curl -s "$BASE_URL/$loc/ca-gregorian.json" > "scratch/cldr_$loc.json"
done
