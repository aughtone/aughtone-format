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
        assertFailsWith<IllegalArgumentException> { "1234WEST12345698765432".formatIban() } // digits before letters
        assertFailsWith<IllegalArgumentException> { "GB82".formatIban() } // too short
        assertFailsWith<IllegalArgumentException> { "GB82-WEST-1234-5698-7654-32".formatIban() } // not a space
        assertFailsWith<IllegalArgumentException> { "GB82WEST1234569876543Ä".formatIban() } // not ASCII
    }

    @Test
    fun lower_case_and_the_printed_form_read_as_the_same_iban() {
        for (spelling in listOf("gb82west12345698765432", "Gb82West12345698765432", "GB82 WEST 1234 5698 7654 32", "gb82 west 1234 5698 7654 32", " GB82  WEST 1234 5698 7654 32 ")) {
            assertEquals("GB82 WEST 1234 5698 7654 32", spelling.formatIban(), spelling)
            assertEquals(compact, spelling.formatIban(IbanNotation.Compact), spelling)
        }
    }
}
