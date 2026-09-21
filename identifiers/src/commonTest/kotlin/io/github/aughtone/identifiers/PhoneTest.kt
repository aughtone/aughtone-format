package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PhoneTest {
    private val e164 = "+16502530000"

    @Test
    fun international_is_default() {
        assertEquals("+1 650-253-0000", e164.formatPhone())
    }

    @Test
    fun national() {
        assertEquals("(650) 253-0000", e164.formatPhone(PhoneNotation.National))
    }

    @Test
    fun rfc3966() {
        assertEquals("tel:+1-650-253-0000", e164.formatPhone(PhoneNotation.Rfc3966))
    }

    @Test
    fun another_country_international() {
        assertEquals("+44 20 7946 0958", "+442079460958".formatPhone(PhoneNotation.International))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "not-a-number".formatPhone() }
        assertFailsWith<IllegalArgumentException> { "6502530000".formatPhone() } // no country code
    }
}
