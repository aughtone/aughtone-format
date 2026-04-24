package io.github.aughtone.datetime.format.resources

/**
 * Supported numbering systems for localized digit formatting.
 */
enum class NumberingSystem(val digits: String?) {
    /** Latin (Western) digits: 0123456789 */
    LATN(null),
    
    /** Arabic-Indic digits: ٠١٢٣٤٥٦٧٨٩ */
    ARAB("٠١٢٣٤٥٦٧٨٩"),
    
    /** Extended Arabic-Indic digits: ۰۱۲۳۴۵۶۷۸۹ */
    ARABEXT("۰۱۲۳۴۵۶۷۸۹"),
    
    /** Devanagari digits: ०१२३४५६७८९ */
    DEVA("०१२३४५६७८९"),

    /** Bengali digits: ০১২৩৪৫৬৭৮৯ */
    BENG("০১২৩৪৫৬৭৮৯"),

    /** Thai digits: ๐๑๒๓๔๕๖๗๘๙ */
    THAI("๐๑๒๓๔๕๖๗๘๙"),
}

/**
 * Utility to replace Latin digits in a string with the selected numbering system's digits.
 */
fun String.applyNumberingSystem(system: NumberingSystem): String {
    val replacement = system.digits ?: return this
    val result = StringBuilder(this.length)
    for (char in this) {
        if (char in '0'..'9') {
            result.append(replacement[char - '0'])
        } else {
            result.append(char)
        }
    }
    return result.toString()
}
