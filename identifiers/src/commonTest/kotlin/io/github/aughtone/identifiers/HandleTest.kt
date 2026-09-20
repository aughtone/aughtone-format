package io.github.aughtone.identifiers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class HandleTest {
    @Test
    fun prefixes_at() {
        assertEquals("@alice", "alice".formatHandle())
    }

    @Test
    fun is_idempotent() {
        assertEquals("@alice", "@alice".formatHandle())
    }

    @Test
    fun blank_throws() {
        assertFailsWith<IllegalArgumentException> { "".formatHandle() }
        assertFailsWith<IllegalArgumentException> { "   ".formatHandle() }
    }
}
