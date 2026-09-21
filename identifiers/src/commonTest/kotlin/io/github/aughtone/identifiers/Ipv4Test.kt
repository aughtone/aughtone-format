package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class Ipv4Test {
    private val ip = "192.0.2.1"

    @Test
    fun dotted_is_default() {
        assertEquals("192.0.2.1", ip.formatIpv4())
    }

    @Test
    fun reverse_dns() {
        assertEquals("1.2.0.192.in-addr.arpa", ip.formatIpv4(Ipv4Notation.ReverseDns))
    }

    @Test
    fun integer() {
        assertEquals("3221225985", ip.formatIpv4(Ipv4Notation.Integer))
        assertEquals("0", "0.0.0.0".formatIpv4(Ipv4Notation.Integer))
        assertEquals("4294967295", "255.255.255.255".formatIpv4(Ipv4Notation.Integer))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "192.0.2.256".formatIpv4() } // octet > 255
        assertFailsWith<IllegalArgumentException> { "192.0.2".formatIpv4() }     // too few octets
        assertFailsWith<IllegalArgumentException> { "192.0.02.1".formatIpv4() }  // leading zero
        assertFailsWith<IllegalArgumentException> { "192.0.2.x".formatIpv4() }   // non-numeric
    }
}
