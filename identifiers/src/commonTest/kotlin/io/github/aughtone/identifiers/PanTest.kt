package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PanTest {
    @Test
    fun grouped_in_fours_is_default() {
        assertEquals("4111 1111 1111 1111", "4111111111111111".formatCardNumber())
    }

    @Test
    fun amex_groups_four_six_five() {
        assertEquals("3782 822463 10005", "378282246310005".formatCardNumber())
    }

    @Test
    fun grouped_keeps_a_short_final_group() {
        // 19-digit PAN -> 4-4-4-4-3
        assertEquals("4111 1111 1111 1111 111", "4111111111111111111".formatCardNumber())
    }

    @Test
    fun masked_shows_only_last_four() {
        assertEquals("•••• 1111", "4111111111111111".formatCardNumber(CardNotation.Masked))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "4111-1111-1111-1111".formatCardNumber() } // separators
        assertFailsWith<IllegalArgumentException> { "41111".formatCardNumber() } // too short
        assertFailsWith<IllegalArgumentException> { "41111111111111111111".formatCardNumber() } // 20 digits, too long
        assertFailsWith<IllegalArgumentException> { "4111abcd11111111".formatCardNumber() } // non-digit
    }
}
