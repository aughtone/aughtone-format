package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class UuidTest {
    private val canonical = "123e4567-e89b-12d3-a456-426614174000"

    @Test
    fun defaults_to_hyphenated() {
        assertEquals(canonical, canonical.formatUuid())
    }

    @Test
    fun braces() {
        assertEquals("{123e4567-e89b-12d3-a456-426614174000}", canonical.formatUuid(UuidNotation.Braces))
    }

    @Test
    fun urn() {
        assertEquals("urn:uuid:123e4567-e89b-12d3-a456-426614174000", canonical.formatUuid(UuidNotation.Urn))
    }

    @Test
    fun uppercase() {
        assertEquals("123E4567-E89B-12D3-A456-426614174000", canonical.formatUuid(UuidNotation.Uppercase))
    }

    @Test
    fun no_hyphens() {
        assertEquals("123e4567e89b12d3a456426614174000", canonical.formatUuid(UuidNotation.NoHyphens))
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun uuid_overload_matches_string_overload() {
        val id = Uuid.parse(canonical)
        assertEquals(canonical, id.format())
        assertEquals("{123e4567-e89b-12d3-a456-426614174000}", id.format(UuidNotation.Braces))
        assertEquals("urn:uuid:123e4567-e89b-12d3-a456-426614174000", id.format(UuidNotation.Urn))
    }

    @Test
    fun accepts_uppercase_and_mixed_case_input() {
        // RFC 9562 §4: hex case is insignificant on input. Apple's Foundation
        // (UUID/NSUUID in Swift/Obj-C) emits upper-case; it normalizes to lower-case.
        assertEquals(canonical, "123E4567-E89B-12D3-A456-426614174000".formatUuid())
        assertEquals(canonical, "123E4567-e89b-12D3-a456-426614174000".formatUuid())
        assertEquals("123E4567-E89B-12D3-A456-426614174000", canonical.formatUuid(UuidNotation.Uppercase))
    }

    @Test
    fun malformed_input_throws() {
        assertFailsWith<IllegalArgumentException> { "not-a-uuid".formatUuid() }
        assertFailsWith<IllegalArgumentException> { "{$canonical}".formatUuid() }                       // brace-wrapped
        assertFailsWith<IllegalArgumentException> { "123e4567e89b12d3a456426614174000".formatUuid() }   // unhyphenated
        assertFailsWith<IllegalArgumentException> { "123e4567-e89b-12d3-a456-42661417400g".formatUuid() } // non-hex digit
    }
}
