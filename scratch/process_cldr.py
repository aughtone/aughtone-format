import json
import os

locales = [
    "af", "ar", "az", "be", "bg", "ca", "cs", "da", "de", "el", "en", "es", "et", "eu", "fa", "fi", "fr", "gl", "he", "hi", 
    "hr", "hu", "hy", "id", "is", "it", "iu", "ja", "ka", "kk", "ko", "lt", "lv", "mk", "ms", "nb", "nl", "nn", "no", "pl", 
    "pt", "ro", "ru", "sk", "sl", "sq", "sr", "sv", "sw", "th", "tr", "uk", "uz", "vi", "zh"
]

month_map = {
    "1": "january", "2": "february", "3": "march", "4": "april",
    "5": "may", "6": "june", "7": "july", "8": "august",
    "9": "september", "10": "october", "11": "november", "12": "december"
}

day_map = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"]

def get_months(data, loc):
    months = data["main"][loc]["dates"]["calendars"]["gregorian"]["months"]["format"]
    full = months["wide"]
    abbr = months["abbreviated"]
    
    code = f'    "{loc}" to lazy {{\n'
    code += '        MonthNamesData(\n'
    code += '            full = MonthNames(\n'
    for i in range(1, 13):
        code += f'                {month_map[str(i)]} = "{full[str(i)]}",\n'
    code = code.rstrip(",\n") + "\n"
    code += '            ),\n'
    code += '            abbreviated = MonthNames(\n'
    for i in range(1, 13):
        code += f'                {month_map[str(i)]} = "{abbr[str(i)]}",\n'
    code = code.rstrip(",\n") + "\n"
    code += '            )\n'
    code += '        )\n'
    code += '    },'
    return code

def get_days(data, loc):
    days = data["main"][loc]["dates"]["calendars"]["gregorian"]["days"]["format"]
    full = days["wide"]
    abbr = days["abbreviated"]
    
    full_list = [full[d] for d in day_map]
    abbr_list = [abbr[d] for d in day_map]
    
    code = f'    "{loc}" to lazy {{ DayOfWeekNamesData(\n'
    code += f'        full = DayOfWeekNames(listOf(' + ", ".join([f'"{d}"' for d in full_list]) + ")),\n"
    code += f'        abbreviated = DayOfWeekNames(listOf(' + ", ".join([f'"{d}"' for d in abbr_list]) + "))\n"
    code += '    ) },'
    return code

def get_eras(data, loc):
    eras = data["main"][loc]["dates"]["calendars"]["gregorian"]["eras"]
    wide = eras["eraNames"]
    abbr = eras["eraAbbr"]
    
    code = f'    "{loc}" to lazy {{ EraNamesData(\n'
    code += f'        wide = listOf("{wide["0"]}", "{wide["1"]}"),\n'
    code += f'        abbreviated = listOf("{abbr["0"]}", "{abbr["1"]}")\n'
    code += '    ) },'
    return code

def get_date_patterns(data, loc):
    patterns = data["main"][loc]["dates"]["calendars"]["gregorian"]["dateFormats"]
    code = f'    "{loc}" to DatePatterns(\n'
    code += f'        full = "{patterns["full"]}",\n'
    code += f'        long = "{patterns["long"]}",\n'
    code += f'        medium = "{patterns["medium"]}",\n'
    code += f'        short = "{patterns["short"]}"\n'
    code += '    ),'
    return code

def get_time_patterns(data, loc):
    patterns = data["main"][loc]["dates"]["calendars"]["gregorian"]["timeFormats"]
    code = f'    "{loc}" to TimePatterns(\n'
    code += f'        full = "{patterns["full"]}",\n'
    code += f'        long = "{patterns["long"]}",\n'
    code += f'        medium = "{patterns["medium"]}",\n'
    code += f'        short = "{patterns["short"]}"\n'
    code += '    ),'
    return code

def get_ampm(data, loc):
    periods = data["main"][loc]["dates"]["calendars"]["gregorian"]["dayPeriods"]["format"]["wide"]
    code = f'    "{loc}" to lazy {{ AmPmStrings("{periods["am"]}", "{periods["pm"]}") }},'
    return code

results = {
    "months": [],
    "days": [],
    "eras": [],
    "date": [],
    "time": [],
    "ampm": []
}

for loc in locales:
    filename = f"scratch/cldr_{loc}.json"
    if not os.path.exists(filename): continue
    with open(filename, "r") as f:
        data = json.load(f)
        results["months"].append(get_months(data, loc))
        results["days"].append(get_days(data, loc))
        results["eras"].append(get_eras(data, loc))
        results["date"].append(get_date_patterns(data, loc))
        results["time"].append(get_time_patterns(data, loc))
        results["ampm"].append(get_ampm(data, loc))

for key in results:
    with open(f"scratch/output_{key}.txt", "w") as f:
        f.write("\n".join(results[key]))
