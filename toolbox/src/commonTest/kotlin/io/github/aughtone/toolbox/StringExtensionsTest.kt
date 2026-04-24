package io.github.aughtone.toolbox

import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalMultiplatform::class)
class StringExtensionsTest {
    @Test
    fun testFormatOneParam() {
        assertEquals("hello world", "hello %1".format("world"))
    }

    @Test
    fun testFormatTwoParams() {
        assertEquals("hello world car", "hello %1 %2".format("world", "car"))
    }

    @Test
    fun testFormatTwoParamsTwice() {
        assertEquals("hello world car world", "hello %1 %2 %1".format("world", "car"))
    }

    @Test
    fun testFormatMissingPlaceholderIsIgnored() {
        assertEquals("hello world", "hello %1".format("world", "car"))
    }

    @Test
    fun testFormatExtraPlaceholderNotFormatted() {
        assertEquals("hello world car %3", "hello %1 %2 %3".format("world", "car"))
    }

//    Key components of format specifiers (used with String.format() and printf()):
//    %: Introduces a format specifier.
//    argument_index$ (optional): Specifies which argument from the list to use (e.g., 1$, 2$).
//    flags (optional): Characters that modify the output format (e.g., - for left-justification, 0 for zero-padding, , for grouping separators).
//    width (optional): Minimum number of characters to occupy.
//    .precision (optional): For floating-point numbers, specifies the number of decimal places; for strings, specifies the maximum number of characters.
//    conversion (required): A character indicating the data type and how it should be represented (e.g., s for string, d for decimal integer, f for floating-point, t for date/time).

    @Test
    fun testObfuscatedFormatDigitsWithOnlyDigits() {
        assertEquals("•2345", "12345".obfuscateLast(digitsOnly = true))
        assertEquals("•••45", "12345".obfuscateLast(digitsOnly = true, count = 2))
    }

    @Test
    fun testObfuscatedFormatDigitsOnlyWithMixedString() {
        assertEquals("•••••••4567", "+1 (555) 123-4567".obfuscateLast(digitsOnly = true, ))
        assertEquals("•••••••••67", "+1 (555) 123-4567".obfuscateLast(digitsOnly = true, count = 2))
    }

    @Test
    fun testObfuscatedFormatDigitsOnlyWithShortString() {
        assertEquals("567", "567".obfuscateLast(digitsOnly = true, ))
        assertEquals("4567", "4567".obfuscateLast(digitsOnly = true, count = 5))
    }

    @Test
    fun testObfuscatedFormatWithMixedString() {
        assertEquals("•••••••••••••••ring", "ThisIsASampleString".obfuscateLast())
        assertEquals("•••••••••••••••••ng", "ThisIsASampleString".obfuscateLast(count = 2))
    }

    @Test
    fun testObfuscatedFormatWithShortString() {
        assertEquals("Short", "Short".obfuscateLast(count = 5))
        assertEquals("Tiny", "Tiny".obfuscateLast(count = 5))
    }

    @Test
    fun testObfuscatedFormatWithAltObfuscationChar() {
        assertEquals("XXXXLong", "VeryLong".obfuscateLast(obfuscationChar='X'))
        assertEquals("Xhort", "Short".obfuscateLast(obfuscationChar='X'))
        assertEquals("Tiny", "Tiny".obfuscateLast(obfuscationChar='X'))
    }
}
