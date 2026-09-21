package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class IbanTest {
    private val compact = "GB82WEST12345698765432"

    @Test
    fun grouped_is_default() {
        assertEquals("GB82 WEST 1234 5698 7654 32", compact.formatIban())
    }

    @Test
    fun compact() {
        assertEquals(compact, compact.formatIban(IbanNotation.Compact))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "gb82west12345698765432".formatIban() } // lower-case
        assertFailsWith<IllegalArgumentException> { "GB82 WEST 1234 5698 7654 32".formatIban() } // already spaced
        assertFailsWith<IllegalArgumentException> { "1234WEST12345698765432".formatIban() } // digits before letters
        assertFailsWith<IllegalArgumentException> { "GB82".formatIban() } // too short
    }
}
