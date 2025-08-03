package io.github.aughtone.toolbox

/**
 * Formats the string by replacing placeholders with the given arguments.
 *
 * Placeholders are in the format `%1`, `%2`, etc., where the number corresponds to the index of the argument in [args] (starting from 1).
 *
 * @param args The arguments to replace the placeholders with.
 * @return The formatted string.
 *
 * Example:
 * ```kotlin
 * "Hello %1, today is %2".format("World", "Monday") // Returns "Hello World, today is Monday"
 * "Value: %1, Value2: %2".format(10, "test") // Returns "Value: 10, Value2: test"
 * ```
 */
fun String.format(vararg args: Any): String {
    var newString = this
    args.forEachIndexed { index, arg ->
        newString = newString.replace("%${index+1}", "$arg")
    }
    return newString
}

/**
 * Obfuscates a string, showing only the last N characters (or digits).
 * Leading characters (or digits) are replaced with an obfuscation character.
 *
 * @param count The number of characters/digits to keep visible at the end. Defaults to 4.
 * @param obfuscationChar The character to use for replacing hidden parts. Defaults to '•'.
 * @param digitsOnly If true, the obfuscation will only consider digits in the string.
 *                   If false (default), it will consider all characters.
 * @return The obfuscated string.
 */
fun String.obfuscateLast(
    count: Int = 4,
    obfuscationChar: Char = '•',
    digitsOnly: Boolean = false
): String {
    // 1. Determine the effective string to work with
    val effectiveString = if (digitsOnly) {
        filter { it.isDigit() }
    } else {
        this
    }

    // 2. Handle cases where the effective string is too short to obfuscate effectively
    if (effectiveString.length <= count) {
        // If the string (or its digit-only version) is too short,
        // it doesn't make sense to obfuscate.
        // Return the original inputString to preserve original formatting if no obfuscation occurs.
        // Or, return `effectiveString` if you always want the sanitized version when too short.
        return this
    }

    // 3. Determine the part to keep visible and the part to obfuscate
    val visiblePart = effectiveString.takeLast(count)
    val numberOfPartsToObfuscate = effectiveString.length - count

    // 4. Create the obfuscated part
    val obfuscatedPrefix = obfuscationChar.toString().repeat(numberOfPartsToObfuscate)

    // 5. Combine and return
    // If digitsOnly is true, we want to place the obfuscated part and visible digits
    // back into the original string structure if possible, but that's complex.
    // A simpler and common approach when digitsOnly=true for phone numbers
    // is to return a string that is purely the obfuscated digits and visible digits.
    // For this generic function, if digitsOnly is true, the result will be based
    // on the extracted digits. If digitsOnly is false, it's a direct prefix.

    if (digitsOnly) {
        // When digitsOnly is true, the output is just the sequence of obfuscated and visible digits.
        return obfuscatedPrefix + visiblePart
    } else {
        // When digitsOnly is false, it's a simple prefix to the original string's suffix.
        // We need to find where the `visiblePart` starts in the `inputString`
        // if we want to preserve the original non-obfuscated suffix.
        // However, the original request was to obfuscate leading characters,
        // so `obfuscatedPrefix + visiblePart` (where visiblePart is from inputString directly) is simpler.

        // Re-calculate visiblePart from original string if not digitsOnly for correct suffix.
        val originalVisibleSuffix = this.takeLast(count)
        val originalPrefixLength = this.length - count
        val actualObfuscatedPrefix = obfuscationChar.toString().repeat(originalPrefixLength)
        return actualObfuscatedPrefix + originalVisibleSuffix
    }
}
