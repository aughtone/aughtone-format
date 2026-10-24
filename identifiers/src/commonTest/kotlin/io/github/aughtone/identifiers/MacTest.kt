package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MacTest {
    private val canonical = "00:00:5e:00:53:01"

    @Test
    fun defaults_to_colon() {
        assertEquals("00:00:5e:00:53:01", canonical.formatMac())
    }

    @Test
    fun colon() {
        assertEquals("00:00:5e:00:53:01", canonical.formatMac(MacNotation.Colon))
    }

    @Test
    fun ieee_is_uppercase_hyphenated() {
        assertEquals("00-00-5E-00-53-01", canonical.formatMac(MacNotation.Ieee))
    }

    @Test
    fun cisco_dotted_is_three_groups_of_four() {
        assertEquals("0000.5e00.5301", canonical.formatMac(MacNotation.CiscoDotted))
    }

    @Test
    fun bare_has_no_separators() {
        assertEquals("00005e005301", canonical.formatMac(MacNotation.Bare))
    }

    @Test
    fun uppercase_colon() {
        assertEquals("00:00:5E:00:53:01", canonical.formatMac(MacNotation.UppercaseColon))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "not-a-mac".formatMac() }
        assertFailsWith<IllegalArgumentException> { "00:00:5e:00:53".formatMac() }    // five octets
        assertFailsWith<IllegalArgumentException> { "00:00:5e:00:53:0g".formatMac() } // non-hex digit
        assertFailsWith<IllegalArgumentException> { "00:00:5e:00:53:01:02".formatMac() } // seven octets
        assertFailsWith<IllegalArgumentException> { "00-00:5e-00:53-01".formatMac() } // mixed separators
        assertFailsWith<IllegalArgumentException> { "000.05e00.5301".formatMac() }    // dots in the wrong place
        assertFailsWith<IllegalArgumentException> { "".formatMac() }
    }

    @Test
    fun every_notation_and_letter_case_reads_as_the_same_address() {
        val spellings = listOf(
            "00:00:5e:00:53:01", "00:00:5E:00:53:01", "00-00-5E-00-53-01", "00-00-5e-00-53-01",
            "0000.5e00.5301", "0000.5E00.5301", "00005e005301", "00005E005301",
        )
        for (spelling in spellings) {
            assertEquals("00:00:5e:00:53:01", spelling.formatMac(), spelling)
            assertEquals("00-00-5E-00-53-01", spelling.formatMac(MacNotation.Ieee), spelling)
            assertEquals("0000.5e00.5301", spelling.formatMac(MacNotation.CiscoDotted), spelling)
        }
    }

    @Test
    fun digitsFromOtherScriptsAreNotHexDigits() {
        // Fullwidth and Arabic-Indic digits are digits to Unicode, but not to a MAC address.
        assertFailsWith<IllegalArgumentException> { "００:00:5e:00:53:01".formatMac() }
        assertFailsWith<IllegalArgumentException> { "ａｂ:00:5e:00:53:01".formatMac(MacNotation.Bare) }
        assertFailsWith<IllegalArgumentException> { "٠٠:00:5e:00:53:01".formatMac() }
    }
}
