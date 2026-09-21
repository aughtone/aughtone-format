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
        assertFailsWith<IllegalArgumentException> { "0000.5e00.5301".formatMac() }    // not colon form
    }
}
