package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class IpNetworkTest {
    private val net = "192.0.2.0/24"

    @Test
    fun cidr_is_default() {
        assertEquals("192.0.2.0/24", net.formatIpNetwork())
    }

    @Test
    fun netmask() {
        assertEquals("192.0.2.0 255.255.255.0", net.formatIpNetwork(IpNetworkNotation.Netmask))
    }

    @Test
    fun wildcard() {
        assertEquals("0.0.0.255", net.formatIpNetwork(IpNetworkNotation.Wildcard))
    }

    @Test
    fun range() {
        assertEquals("192.0.2.0 - 192.0.2.255", net.formatIpNetwork(IpNetworkNotation.Range))
    }

    @Test
    fun prefix_edges() {
        assertEquals("0.0.0.0 0.0.0.0", "0.0.0.0/0".formatIpNetwork(IpNetworkNotation.Netmask))
        assertEquals(
            "255.255.255.255 255.255.255.255",
            "255.255.255.255/32".formatIpNetwork(IpNetworkNotation.Netmask),
        )
        assertEquals("10.0.0.0 - 10.255.255.255", "10.0.0.0/8".formatIpNetwork(IpNetworkNotation.Range))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "192.0.2.0".formatIpNetwork() }      // no prefix
        assertFailsWith<IllegalArgumentException> { "192.0.2.0/33".formatIpNetwork() }   // prefix > 32
        assertFailsWith<IllegalArgumentException> { "192.0.2.256/24".formatIpNetwork() } // bad octet
        assertFailsWith<IllegalArgumentException> { "192.0.2.0/".formatIpNetwork() }     // empty prefix
    }
}
