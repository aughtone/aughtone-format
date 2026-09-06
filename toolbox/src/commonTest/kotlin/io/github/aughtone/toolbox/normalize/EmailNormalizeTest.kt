package io.github.aughtone.toolbox.normalize

import kotlin.test.Test
import kotlin.test.assertEquals

class EmailNormalizeTest {

    // --- Conservative (the safe default) -------------------------------------

    @Test
    fun conservativeTrimsAndLowercases() {
        assertEquals("user@example.com", "  User@Example.COM  ".normalizeEmail())
        assertEquals("user@example.com", "USER@EXAMPLE.COM".normalizeEmail(EmailNormalization.Conservative))
    }

    @Test
    fun conservativeIsTheDefaultPolicy() {
        assertEquals(
            "user@example.com".normalizeEmail(EmailNormalization.Conservative),
            "user@example.com".normalizeEmail(),
        )
    }

    @Test
    fun conservativeKeepsDotsAndSubaddress() {
        // Conservative makes no provider-specific decisions.
        assertEquals("first.last+tag@example.com", "First.Last+Tag@Example.com".normalizeEmail())
    }

    @Test
    fun conservativeDoesNotTouchGmailStructure() {
        // Even for gmail, Conservative only trims + lowercases.
        assertEquals("f.i.r.s.t+x@gmail.com", "F.I.R.S.T+X@GMAIL.COM".normalizeEmail())
    }

    @Test
    fun conservativeIsIdempotent() {
        val once = "  Mixed.Case+Sub@Example.COM ".normalizeEmail()
        assertEquals(once, once.normalizeEmail())
    }

    @Test
    fun conservativeHandlesValueWithoutAt() {
        // No @ -> trimmed + lowercased only, no local/domain handling, never throws.
        assertEquals("not-an-email", "  NOT-AN-EMAIL ".normalizeEmail())
    }

    // --- GmailAware ----------------------------------------------------------

    @Test
    fun gmailAwareRemovesDotsAndSubaddress() {
        assertEquals(
            "firstlast@gmail.com",
            "First.Last+newsletter@gmail.com".normalizeEmail(EmailNormalization.GmailAware),
        )
    }

    @Test
    fun gmailAwareCanonicalizesGooglemailDomain() {
        assertEquals(
            "firstlast@gmail.com",
            "First.Last@googlemail.com".normalizeEmail(EmailNormalization.GmailAware),
        )
    }

    @Test
    fun gmailAwareLeavesNonGmailUntouchedBeyondConservative() {
        // Dots and subaddress are meaningful outside gmail; only trim + lowercase apply.
        assertEquals(
            "first.last+tag@example.com",
            "First.Last+Tag@Example.com".normalizeEmail(EmailNormalization.GmailAware),
        )
    }

    @Test
    fun gmailAwareNeverProducesEmptyLocalPart() {
        // A local part that is entirely dots/subaddress must not collapse to "@gmail.com".
        val input = ".+tag@gmail.com"
        assertEquals(input.normalizeEmail(EmailNormalization.Conservative), input.normalizeEmail(EmailNormalization.GmailAware))
    }

    @Test
    fun gmailAwareHandlesMissingLocalOrDomain() {
        // Leading @ (no local) and trailing @ (no domain) fall back to Conservative, never throw.
        assertEquals("@gmail.com", "@gmail.com".normalizeEmail(EmailNormalization.GmailAware))
        assertEquals("user@", "User@".normalizeEmail(EmailNormalization.GmailAware))
    }

    @Test
    fun gmailAwareIsIdempotent() {
        val once = "First.Last+Sub@GoogleMail.com".normalizeEmail(EmailNormalization.GmailAware)
        assertEquals(once, once.normalizeEmail(EmailNormalization.GmailAware))
        assertEquals("firstlast@gmail.com", once)
    }

    // --- Byte-stability / policy identity ------------------------------------

    @Test
    fun sameInputAndPolicyYieldByteIdenticalOutput() {
        val a = "Repeatable.Case+Sub@Gmail.com".normalizeEmail(EmailNormalization.GmailAware)
        val b = "Repeatable.Case+Sub@Gmail.com".normalizeEmail(EmailNormalization.GmailAware)
        assertEquals(a, b)
    }

    @Test
    fun policyIdentifiersAreStable() {
        // These strings travel with derived values (e.g. tokens); they must not drift.
        assertEquals("email.conservative", EmailNormalization.Conservative.id)
        assertEquals(1, EmailNormalization.Conservative.version)
        assertEquals("email.gmail-aware", EmailNormalization.GmailAware.id)
        assertEquals(1, EmailNormalization.GmailAware.version)
    }
}
