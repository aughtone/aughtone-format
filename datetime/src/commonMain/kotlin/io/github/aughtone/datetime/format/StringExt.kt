package io.github.aughtone.datetime.format

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
internal fun String.format(vararg args: Any): String {
    var newString = this
    args.forEachIndexed { index, arg ->
        newString = newString.replace("%${index+1}", "$arg")
    }
    return newString
}
