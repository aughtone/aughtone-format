package io.github.aughtone.toolbox

/**
 * Marks a declaration that is public only so that the modules of `aughtone-format` can share it.
 *
 * Kotlin's `internal` reaches no further than one module, so code one module of the library needs
 * from another has to be public. This annotation keeps it out of reach: using it without opting in
 * is a compile error. It is not part of the library's API, carries no compatibility promise, and
 * can change or disappear in any release; the modules that use it are released together at one
 * version.
 *
 * It is specific to `aughtone-format` on purpose: opting in to this library's internals does not
 * opt in to those of any other library.
 */
@RequiresOptIn(
    message = "Internal to aughtone-format: it can change or disappear in any release.",
    level = RequiresOptIn.Level.ERROR,
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.CONSTRUCTOR,
)
@MustBeDocumented
annotation class InternalAughtoneFormatApi
