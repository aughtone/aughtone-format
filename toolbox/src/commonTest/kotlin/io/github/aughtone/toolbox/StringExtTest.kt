package io.github.aughtone.toolbox

import kotlin.test.Test
import kotlin.test.assertEquals

class StringExtTest {
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
