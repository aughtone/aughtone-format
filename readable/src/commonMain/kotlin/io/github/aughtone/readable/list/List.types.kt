package io.github.aughtone.readable.list

/**
 * Which kind of list to build — the conjunction that joins the final element.
 *
 * A type changes only the connecting word ("and" vs "or") and, for [Unit],
 * drops it entirely. It follows Unicode CLDR's list-pattern types.
 *
 * @see formatReadableList
 */
enum class ListType {
    /**
     * A conjunctive list: "A, B, and C" / "A, B et C". CLDR's `standard` type.
     */
    And,

    /**
     * A disjunctive list: "A, B, or C" / "A, B ou C". CLDR's `or` type.
     */
    Or,

    /**
     * A unit list: the elements run together with a separator and no
     * conjunction — "5 h, 30 min". CLDR's `unit` type.
     */
    Unit,
}
