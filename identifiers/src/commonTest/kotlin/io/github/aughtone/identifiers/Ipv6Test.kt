package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class Ipv6Test {
    @Test
    fun compressed_is_default_and_identity_for_canonical() {
        assertEquals("2001:db8::1", "2001:db8::1".formatIpv6())
    }

    @Test
    fun compressed_rederives_from_expanded_input() {
        assertEquals("2001:db8::1", "2001:0db8:0000:0000:0000:0000:0000:0001".formatIpv6())
    }

    @Test
    fun loopback_and_unspecified_compress() {
        assertEquals("::1", "0:0:0:0:0:0:0:1".formatIpv6())
        assertEquals("::", "0:0:0:0:0:0:0:0".formatIpv6())
    }

    @Test
    fun expanded() {
        assertEquals(
            "2001:0db8:0000:0000:0000:0000:0000:0001",
            "2001:db8::1".formatIpv6(Ipv6Notation.Expanded),
        )
    }

    @Test
    fun url_host_is_bracketed() {
        assertEquals("[2001:db8::1]", "2001:db8::1".formatIpv6(Ipv6Notation.UrlHost))
    }

    @Test
    fun reverse_nibbles() {
        // ::1 -> nibble '1' then 31 zero nibbles, reversed, under ip6.arpa.
        val expected = "1" + ".0".repeat(31) + ".ip6.arpa"
        assertEquals(expected, "::1".formatIpv6(Ipv6Notation.ReverseNibbles))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "2001:db8:::1".formatIpv6() }     // empty group / double ::
        assertFailsWith<IllegalArgumentException> { "2001:db8:1".formatIpv6() }       // too few groups, no ::
        assertFailsWith<IllegalArgumentException> { "2001:db8::12345".formatIpv6() }  // group too long
        assertFailsWith<IllegalArgumentException> { "::ffff:192.0.2.1".formatIpv6() } // IPv4-embedded not supported
        assertFailsWith<IllegalArgumentException> { "gggg::1".formatIpv6() }          // non-hex
    }
}
