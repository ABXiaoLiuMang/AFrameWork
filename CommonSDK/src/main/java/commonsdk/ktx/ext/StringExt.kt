package commonsdk.ktx.ext

/**
 *
 */

fun <T> String?.notNull(f: () -> T, t: () -> T): T {
    return if (this != null) f() else t()
}

fun String.areDigitsOnly() = matches(Regex("[0-9]+"))