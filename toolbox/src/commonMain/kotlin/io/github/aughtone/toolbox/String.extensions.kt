package io.github.aughtone.toolbox

/**
 * Formats the string by replacing placeholders with the given arguments.
 *
 * Placeholders are in the format `%1`, `%2`, etc., where the number corresponds to the index of the argument in [args] (starting from 1).
 * A number may have several digits, so `%10` is the tenth argument when there are at least ten. A `%` that is not followed by
 * the number of an argument is left as it is.
 *
 * The template is read once, left to right: text that arrives in an argument is never scanned for placeholders, so an argument
 * containing `%2` is emitted unchanged.
 *
 * **Experimental**: This is intended to partly fill the role of the string format functions missing from KMP, that are available in Java/Android.
 *
 * @param args The arguments to replace the placeholders with. Each is written with its
 *   `toString()`; a `null` argument is written as `null`.
 * @return The formatted string.
 *
 * Example:
 * ```kotlin
 * "Hello %1, today is %2".format("World", "Monday") // Returns "Hello World, today is Monday"
 * "Value: %1, Value2: %2".format(10, "test") // Returns "Value: 10, Value2: test"
 * ```
 */
@ExperimentalMultiplatform()
fun String.format(vararg args: Any?): String = buildString {
    val template = this@format
    var index = 0
    while (index < template.length) {
        if (template[index] == '%') {
            var end = index + 1
            while (end < template.length && template[end] in '0'..'9') end++
            // The longest run of digits that names an argument: "%10" is the tenth argument when
            // there are ten, and otherwise the first argument followed by a literal "0".
            while (end > index + 1) {
                val number = template.substring(index + 1, end).toIntOrNull()
                if (number != null && number in 1..args.size) break
                end--
            }
            if (end > index + 1) {
                append(args[template.substring(index + 1, end).toInt() - 1])
                index = end
                continue
            }
        }
        append(template[index])
        index++
    }
}

/**
 * Obfuscates a string, showing only the last N characters (or digits).
 * Leading characters (or digits) are replaced with an obfuscation character.
 *
 * If the string (or, when [digitsOnly] is true, its digits) has [count] or fewer
 * characters there is nothing to hide, and the original string is returned unchanged.
 *
 * Characters are counted whole: an emoji or other character stored as a surrogate pair counts
 * as one and is hidden with one [obfuscationChar], never split.
 *
 * @param count The number of characters/digits to keep visible at the end. Defaults to 4. Zero or
 *   less hides everything.
 * @param obfuscationChar The character to use for replacing hidden parts. Defaults to '•'.
 * @param digitsOnly If true, the obfuscation will only consider digits in the string.
 *                   If false (default), it will consider all characters.
 * @return The obfuscated string, or the original string unchanged when it has [count]
 *   or fewer characters (digits, when [digitsOnly] is true).
 */
fun String.obfuscateLast(
    count: Int = 4,
    obfuscationChar: Char = '•',
    digitsOnly: Boolean = false
): String {
    val effectiveString = if (digitsOnly) filter { it.isDigit() } else this

    // Count whole characters: a surrogate pair is one, and is never cut in half.
    val characters = effectiveString.toCodePointStrings()
    val visible = count.coerceAtLeast(0)

    // Nothing to hide: return the original, formatting and all.
    if (characters.size <= visible) return this

    // With digitsOnly the result is built from the digits alone; otherwise from the whole string.
    return obfuscationChar.toString().repeat(characters.size - visible) +
        characters.takeLast(visible).joinToString("")
}

/** Splits a string into its characters, keeping each surrogate pair together as one element. */
private fun String.toCodePointStrings(): List<String> {
    val result = ArrayList<String>(length)
    var index = 0
    while (index < length) {
        val paired = this[index].isHighSurrogate() && index + 1 < length && this[index + 1].isLowSurrogate()
        val end = if (paired) index + 2 else index + 1
        result.add(substring(index, end))
        index = end
    }
    return result
}
