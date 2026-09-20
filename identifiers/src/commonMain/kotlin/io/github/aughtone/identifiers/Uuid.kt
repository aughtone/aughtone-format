package io.github.aughtone.identifiers

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * A display notation for a UUID (universally unique identifier).
 *
 * A UUID is a single 128-bit value; a notation changes only how its 32
 * hexadecimal digits are grouped, delimited, and cased for display — never the
 * value itself. Every notation is lossless and round-trips: formatting to any
 * notation and back to [Hyphenated] reproduces the canonical input.
 *
 * [Hyphenated] is the canonical RFC 9562 form and the default [formatUuid]
 * produces. The others exist for the contexts that expect them — registry
 * strings, Microsoft GUIDs, URNs, compact keys.
 *
 * @see formatUuid
 */
enum class UuidNotation {
    /**
     * Conventional canonical form: lower-case hex in 8-4-4-4-12 groups joined by
     * hyphens. Lower-case follows the RFC 4122 convention; RFC 9562 §4 permits
     * any case, but this is what most platforms produce.
     *
     * `123e4567-e89b-12d3-a456-426614174000`
     */
    Hyphenated,

    /**
     * [Hyphenated] wrapped in braces — the Microsoft registry/GUID form.
     *
     * `{123e4567-e89b-12d3-a456-426614174000}`
     */
    Braces,

    /**
     * [Hyphenated] as an RFC 9562 URN, with the `urn:uuid:` scheme prefix.
     *
     * `urn:uuid:123e4567-e89b-12d3-a456-426614174000`
     */
    Urn,

    /**
     * Upper-case variant of [Hyphenated]. Non-canonical — RFC 4122 established
     * lower-case as the canonical convention (RFC 9562 §4 later relaxed this to
     * permit any case). Upper-case is nonetheless what Apple's Foundation
     * (`UUID`/`NSUUID` in Swift and Objective-C) and Microsoft registry GUIDs
     * emit, so it is provided for interop with systems that expect it.
     *
     * `123E4567-E89B-12D3-A456-426614174000`
     */
    Uppercase,

    /**
     * Bare form: the 32 lower-case hex digits with no hyphens.
     *
     * `123e4567e89b12d3a456426614174000`
     */
    NoHyphens,
}

/**
 * Renders this canonical UUID string in the given display [notation].
 *
 * The receiver must be a hyphenated UUID in the 8-4-4-4-12 shape of RFC 9562
 * §4. Hex **case is insignificant on input** — lower-, upper-, and mixed-case
 * are all accepted, upper-case being what Apple's Foundation (`UUID`/`NSUUID`
 * in Swift and Objective-C) emits — and every notation except
 * [UuidNotation.Uppercase] produces the conventional lower-case canonical form.
 * (RFC 9562 permits any case; lower-case is the long-standing RFC 4122
 * convention and what most other platforms emit.)
 *
 * This is a pure display transform: it regroups, delimits, and re-cases the same
 * 128 bits. It does not otherwise parse or normalize input — brace-wrapped,
 * URN-prefixed, or unhyphenated spellings are rejected. To format a [Uuid] value
 * directly, use the [Uuid.format] overload.
 *
 * Example:
 * ```kotlin
 * val id = "123e4567-e89b-12d3-a456-426614174000"
 * id.formatUuid()                          // "123e4567-e89b-12d3-a456-426614174000"  (default)
 * id.formatUuid(UuidNotation.Braces)       // "{123e4567-e89b-12d3-a456-426614174000}"
 * id.formatUuid(UuidNotation.Urn)          // "urn:uuid:123e4567-e89b-12d3-a456-426614174000"
 * id.formatUuid(UuidNotation.Uppercase)    // "123E4567-E89B-12D3-A456-426614174000"
 * id.formatUuid(UuidNotation.NoHyphens)    // "123e4567e89b12d3a456426614174000"
 * ```
 *
 * @param notation the display form to produce. Defaults to
 *   [UuidNotation.Hyphenated], which returns the canonical input unchanged.
 * @return the same UUID re-rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a hyphenated UUID in
 *   the 8-4-4-4-12 shape (regardless of hex case) — for example a brace-wrapped,
 *   URN-prefixed, or unhyphenated string fails.
 * @see UuidNotation
 * @see Uuid.format
 */
fun String.formatUuid(notation: UuidNotation = UuidNotation.Hyphenated): String {
    val hex = canonicalUuidHex(this)
    val hyphenated = groupUuid(hex)
    return when (notation) {
        UuidNotation.Hyphenated -> hyphenated
        UuidNotation.Uppercase -> hyphenated.uppercase()
        UuidNotation.NoHyphens -> hex
        UuidNotation.Braces -> "{$hyphenated}"
        UuidNotation.Urn -> "urn:uuid:$hyphenated"
    }
}

/**
 * Renders this [Uuid] in the given display [notation].
 *
 * The overload of [String.formatUuid] for the Kotlin-native [Uuid] type.
 * [Uuid.toString] already yields the canonical hyphenated form, so this is
 * always well-defined and never throws.
 *
 * ```kotlin
 * val id = Uuid.parse("123e4567-e89b-12d3-a456-426614174000")
 * id.format(UuidNotation.Braces)   // "{123e4567-e89b-12d3-a456-426614174000}"
 * ```
 *
 * @param notation the display form to produce. Defaults to [UuidNotation.Hyphenated].
 * @return this UUID rendered in [notation].
 * @see String.formatUuid
 */
@ExperimentalUuidApi
fun Uuid.format(notation: UuidNotation = UuidNotation.Hyphenated): String =
    toString().formatUuid(notation)

/** Group lengths of a canonical UUID: `8-4-4-4-12`. */
private val UUID_GROUP_LENGTHS = intArrayOf(8, 4, 4, 4, 12)

/**
 * Validates that [value] is a hyphenated UUID in the 8-4-4-4-12 shape and
 * returns its 32 hexadecimal digits, lower-cased, with the hyphens removed —
 * ready for regrouping by [formatUuid]. Input hex case is insignificant.
 *
 * @param value the string to check — expected to be five hyphen-separated hex
 *   groups of lengths 8-4-4-4-12, in any case.
 * @return the 32 hex digits, lower-cased, hyphens stripped.
 * @throws IllegalArgumentException if [value] is not a hyphenated UUID in the
 *   8-4-4-4-12 shape.
 */
private fun canonicalUuidHex(value: String): String {
    val groups = value.split('-')
    val valid = groups.size == UUID_GROUP_LENGTHS.size &&
        groups.withIndex().all { (i, g) ->
            g.length == UUID_GROUP_LENGTHS[i] && g.all { it.digitToIntOrNull(16) != null }
        }
    require(valid) {
        "Not a canonical UUID (expected 8-4-4-4-12 lower-case hex groups): \"$value\""
    }
    return groups.joinToString("") { it.lowercase() }
}

/** Regroups 32 hex digits into the canonical `8-4-4-4-12` hyphenated form. */
private fun groupUuid(hex: String): String {
    val sb = StringBuilder(36)
    var i = 0
    for ((index, len) in UUID_GROUP_LENGTHS.withIndex()) {
        if (index > 0) sb.append('-')
        sb.append(hex, i, i + len)
        i += len
    }
    return sb.toString()
}
