package io.github.aughtone.toolbox.normalize

/**
 * The canonical string plus the policy identity that produced it. Store all three fields beside any
 * hash / token derived from [canonical], so the value can be reproduced under the exact epoch. There is
 * no Unicode-version field: this canonical form has no Unicode dependency, so [policyVersion] alone
 * identifies the rules completely.
 */
data class NormalizedEmail(
    val canonical: String,
    val policyId: String,
    val policyVersion: Int,
)
