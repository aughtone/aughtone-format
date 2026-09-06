package io.github.aughtone.toolbox.normalize

/**
 * A named, frozen email-normalization policy.
 *
 * Each entry is an **immutable** rule-set with a stable [id] and [version]. The identifier is what a
 * byte-stability–sensitive caller (e.g. blind tokenization, where the normalized value is hashed)
 * stores alongside anything derived from the result, so re-normalization always uses the same rules.
 *
 * Policies are **additive**: a new rule-set is a new entry, never an edit to an existing one.
 * Changing the output of an existing policy would silently invalidate every value already derived
 * from it — so if the rules must change, add a new policy (or bump [version]) and keep the old one.
 */
enum class EmailNormalization(val id: String, val version: Int) {
    /**
     * The safe default: trim surrounding whitespace and lowercase the whole address (locale-independent
     * Unicode default case mapping). No provider-specific rules. Two addresses collide only if they
     * differ solely by case or surrounding whitespace.
     *
     * Note: this does not apply Unicode NFC. It is byte-stable (a given input always yields the same
     * output), but does not collapse canonically-equivalent Unicode sequences. If that is required,
     * it must be added as a separate policy, since changing this one would break existing values.
     */
    Conservative("email.conservative", 1),

    /**
     * [Conservative] plus Gmail-style canonicalization for `gmail.com` / `googlemail.com`: the domain
     * is canonicalized to `gmail.com`, any `+`-subaddress is dropped, and dots are removed from the
     * local part. Deliberately collides more addresses. Non-Gmail addresses are treated as [Conservative].
     */
    GmailAware("email.gmail-aware", 1),
}

/**
 * Normalizes this email address to a byte-stable canonical form under [policy].
 *
 * Intended for producing a stable key before hashing / blind-tokenization: the same logical address
 * always yields byte-identical output under the same [policy], and the operation is idempotent
 * (`s.normalizeEmail(p).normalizeEmail(p) == s.normalizeEmail(p)`). Store [EmailNormalization.id] and
 * [EmailNormalization.version] beside anything derived from the result so it can be reproduced under
 * the exact rules.
 *
 * This does **not** validate the address. A value without an `@` is returned trimmed and lowercased
 * only (no local/domain handling); validate separately if you need it.
 *
 * @param policy the frozen normalization policy to apply (defaults to [EmailNormalization.Conservative]).
 */
fun String.normalizeEmail(policy: EmailNormalization = EmailNormalization.Conservative): String {
    // Conservative base: trim + locale-independent lowercase. Applies to every policy.
    val base = trim().lowercase()

    return when (policy) {
        EmailNormalization.Conservative -> base
        EmailNormalization.GmailAware -> base.gmailCanonicalize()
    }
}

private val GMAIL_DOMAINS = setOf("gmail.com", "googlemail.com")

/**
 * Applies Gmail equivalence rules to an already-[EmailNormalization.Conservative]-normalized address.
 * Only `gmail.com` / `googlemail.com` are affected; everything else is returned unchanged.
 */
private fun String.gmailCanonicalize(): String {
    val at = lastIndexOf('@')
    if (at <= 0 || at == length - 1) return this // no usable local@domain split

    val local = substring(0, at)
    val domain = substring(at + 1)
    if (domain !in GMAIL_DOMAINS) return this

    val withoutSubaddress = local.substringBefore('+')
    val withoutDots = withoutSubaddress.replace(".", "")
    if (withoutDots.isEmpty()) return this // never produce an empty local part

    return "$withoutDots@gmail.com"
}
