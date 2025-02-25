package io.github.aughtone.datetime.format

internal fun String.format(vararg args: Any): String {
    var newString = this
    args.forEachIndexed { index, arg ->
        newString = newString.replace("%${index+1}", "$arg")
    }
    return newString
}
