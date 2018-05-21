@file:Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")

package org.glavo.collection

import org.glavo.collection.mutable.Growable
import java.util.function.IntFunction
import java.util.function.Predicate

inline val Traversable<*>.size: Int
    get() = size()

inline fun <T> Traversable<T>.forEach(f: (T) -> Unit) {
    for (elem in this) {
        f(elem)
    }
}

inline fun <G : Growable<T>, T> Traversable<T>.filterTo(growable: G, crossinline predicate: (T) -> Boolean): G {
    return this.filterTo(growable, Predicate { t -> predicate(t) })
}

inline fun <G : Growable<T>, T> Traversable<T>.filterNotTo(growable: G, crossinline predicate: (T) -> Boolean): G {
    return this.filterNotTo(growable, Predicate { t -> predicate(t) })
}

inline fun <T> Traversable<T>.filter(crossinline predicate: (T) -> Boolean): Traversable<T> =
        filter(Predicate { predicate(it) })

inline fun <reified T> Traversable<T>.toTypedArray(): Array<T> {
    return toArray(IntFunction { arrayOfNulls<T>(it) }) as Array<T>
}