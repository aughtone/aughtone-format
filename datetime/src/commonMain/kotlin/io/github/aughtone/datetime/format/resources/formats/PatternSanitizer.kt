package io.github.aughtone.datetime.format.resources.formats

/**
 * Utility to sanitize CLDR patterns by removing unsupported tokens.
 */
object PatternSanitizer {
    private val unsupportedTokens = listOf("B", "v", "V")

    /**
     * Strips unsupported tokens and cleans up resulting separators.
     */
    fun sanitize(pattern: String): String {
        var result = pattern
        unsupportedTokens.forEach { token ->
            // Remove the token and any surrounding spaces if they become redundant
            result = result.replace(Regex("$token+"), "")
        }
        
        // Clean up:
        // 1. Double spaces
        // 2. Spaces before punctuation
        // 3. Leading/trailing spaces
        return result.replace(Regex(" {2,}"), " ")
            .replace(Regex(" ([.,:;])"), "$1")
            .trim()
    }
}
