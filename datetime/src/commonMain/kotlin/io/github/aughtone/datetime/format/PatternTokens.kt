package io.github.aughtone.datetime.format

/**
 * One piece of a CLDR-style date or time pattern such as `d 'de' MMMM 'de' y` or `H時mm分ss秒`.
 */
internal sealed interface PatternToken {
    /** A run of one pattern letter: `MMMM` is `Field('M', 4)`. */
    data class Field(val letter: Char, val count: Int) : PatternToken

    /** Text that is copied to the output as it stands. */
    data class Literal(val text: String) : PatternToken
}

/**
 * Splits [pattern] into fields and literal text.
 *
 * An unquoted ASCII letter is a field. Text between single quotes is literal, so the letters in
 * `'de'` are never read as fields and the quote marks never reach the output; `''` is one literal
 * apostrophe. Everything else — spaces, punctuation, non-ASCII characters such as `時` — is literal.
 * Adjacent literal text is merged into one token.
 */
internal fun tokenizePattern(pattern: String): List<PatternToken> {
    val tokens = mutableListOf<PatternToken>()
    val literal = StringBuilder()
    fun flushLiteral() {
        if (literal.isNotEmpty()) {
            tokens += PatternToken.Literal(literal.toString())
            literal.clear()
        }
    }

    var index = 0
    var quoted = false
    while (index < pattern.length) {
        val char = pattern[index]
        when {
            char == '\'' && pattern.getOrNull(index + 1) == '\'' -> {
                literal.append('\'')
                index += 2
            }
            char == '\'' -> {
                quoted = !quoted
                index++
            }
            !quoted && (char in 'a'..'z' || char in 'A'..'Z') -> {
                flushLiteral()
                var end = index
                while (end < pattern.length && pattern[end] == char) end++
                tokens += PatternToken.Field(char, end - index)
                index = end
            }
            else -> {
                literal.append(char)
                index++
            }
        }
    }
    flushLiteral()
    return tokens
}

/**
 * Removes the fields for which [drop] is true, then tidies what they leave behind: parentheses
 * that wrapped a removed field, doubled spaces, and leading or trailing spaces. So `HH:mm:ss (zzzz)`
 * without its zone is `HH:mm:ss`, not `HH:mm:ss ()`.
 */
internal fun List<PatternToken>.withoutFields(drop: (PatternToken.Field) -> Boolean): List<PatternToken> {
    // Mark each removed field with a private-use character so the tidy-up can find where it was.
    val gap = ''
    val merged = mutableListOf<PatternToken>()
    for (token in this) {
        val next = if (token is PatternToken.Field && drop(token)) PatternToken.Literal(gap.toString()) else token
        val last = merged.lastOrNull()
        if (next is PatternToken.Literal && last is PatternToken.Literal) {
            merged[merged.lastIndex] = PatternToken.Literal(last.text + next.text)
        } else {
            merged += next
        }
    }
    return merged.mapIndexedNotNull { index, token ->
        if (token !is PatternToken.Literal) return@mapIndexedNotNull token
        var text = token.text.replace("($gap)", "").replace(gap.toString(), "")
        while (text.contains("  ")) text = text.replace("  ", " ")
        if (index == 0) text = text.trimStart(' ')
        if (index == merged.lastIndex) text = text.trimEnd(' ')
        if (text.isEmpty()) null else PatternToken.Literal(text)
    }
}
