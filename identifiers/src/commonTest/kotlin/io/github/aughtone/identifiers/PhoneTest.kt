package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

class PhoneTest {
    private val e164 = "+16502530000"

    /** [text] as [PhoneSeparators.Typographic] writes it: U+00A0 for each space, U+2011 for each hyphen. */
    private fun unbreakable(text: String) = text.replace(' ', ' ').replace('-', '‑')

    @Test
    fun international_is_default() {
        assertEquals(unbreakable("+1 650-253-0000"), e164.formatPhone())
    }

    @Test
    fun national() {
        assertEquals(unbreakable("(650) 253-0000"), e164.formatPhone(PhoneNotation.National))
    }

    @Test
    fun rfc3966() {
        assertEquals("tel:+1-650-253-0000", e164.formatPhone(PhoneNotation.Rfc3966))
    }

    @Test
    fun rfc3966_keeps_ascii_hyphens_whatever_the_separators() {
        for (separators in PhoneSeparators.entries) {
            assertEquals("tel:+1-650-253-0000", e164.formatPhone(PhoneNotation.Rfc3966, separators), separators.name)
            assertEquals("tel:+33-1-23-45-67-89", "+33123456789".formatPhone(PhoneNotation.Rfc3966, separators), separators.name)
        }
    }

    @Test
    fun ascii_gives_the_metadatas_own_characters() {
        assertEquals("+1 650-253-0000", e164.formatPhone(separators = PhoneSeparators.Ascii))
        assertEquals("(650) 253-0000", e164.formatPhone(PhoneNotation.National, PhoneSeparators.Ascii))
    }

    @Test
    fun north_american_french_and_german_numbers_in_both_notations() {
        val cases = listOf(
            Triple("+16502530000", "+1 650-253-0000", "(650) 253-0000"),
            Triple("+33123456789", "+33 1 23 45 67 89", "01 23 45 67 89"),
            Triple("+4930123456", "+49 30 123456", "030 123456"),
        )
        for ((number, international, national) in cases) {
            assertEquals(international, number.formatPhone(PhoneNotation.International, PhoneSeparators.Ascii), number)
            assertEquals(national, number.formatPhone(PhoneNotation.National, PhoneSeparators.Ascii), number)
            assertEquals(unbreakable(international), number.formatPhone(PhoneNotation.International), number)
            assertEquals(unbreakable(national), number.formatPhone(PhoneNotation.National), number)
        }
    }

    @Test
    fun typographic_output_has_nowhere_to_break() {
        for (number in listOf("+16502530000", "+33123456789", "+4930123456", "+442079460958")) {
            for (notation in listOf(PhoneNotation.National, PhoneNotation.International)) {
                val text = number.formatPhone(notation)
                assertFalse(text.any { it == ' ' || it == '-' }, "$number $notation: \"$text\"")
            }
        }
    }

    @Test
    fun another_country_international() {
        assertEquals(unbreakable("+44 20 7946 0958"), "+442079460958".formatPhone(PhoneNotation.International))
    }

    @Test
    fun punctuated_input_gives_the_same_output() {
        for (spelling in listOf("+1 650-253-0000", "+1 (650) 253-0000", "+1.650.253.0000", "+1 650 253 0000")) {
            assertEquals(unbreakable("+1 650-253-0000"), spelling.formatPhone(), spelling)
            assertEquals(unbreakable("(650) 253-0000"), spelling.formatPhone(PhoneNotation.National), spelling)
            assertEquals("tel:+1-650-253-0000", spelling.formatPhone(PhoneNotation.Rfc3966), spelling)
        }
        assertEquals(unbreakable("+44 20 7946 0958"), "+44 20 7946 0958".formatPhone())
    }

    @Test
    fun typographic_input_reads_like_ascii_input() {
        assertEquals(e164.formatPhone(), e164.formatPhone().formatPhone())
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "not-a-number".formatPhone() }
        assertFailsWith<IllegalArgumentException> { "6502530000".formatPhone() } // no country code
    }
}
