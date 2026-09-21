package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DomainTest {
    @Test
    fun unicode_is_default_and_decodes_xn_label() {
        assertEquals("café.example", "xn--caf-dma.example".formatDomain())
    }

    @Test
    fun decodes_another_label() {
        assertEquals("münchen.example", "xn--mnchen-3ya.example".formatDomain())
    }

    @Test
    fun mixed_and_multi_label_passes_plain_labels_through() {
        assertEquals("www.café.example", "www.xn--caf-dma.example".formatDomain())
        assertEquals("café.münchen.test", "xn--caf-dma.xn--mnchen-3ya.test".formatDomain())
    }

    @Test
    fun plain_domain_is_unchanged() {
        assertEquals("www.example.com", "www.example.com".formatDomain())
    }

    @Test
    fun ascii_returns_input_unchanged() {
        assertEquals("xn--caf-dma.example", "xn--caf-dma.example".formatDomain(DomainNotation.Ascii))
    }

    @Test
    fun malformed_punycode_throws() {
        assertFailsWith<IllegalArgumentException> { "xn--caf-dm@.example".formatDomain() } // invalid digit
        assertFailsWith<IllegalArgumentException> { "xn--0.example".formatDomain() }       // truncated/overflow
    }
}
