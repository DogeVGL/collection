package org.glavo.collection

import java.util.function.Predicate

inline fun <T> Iterator<T>.forEach(op: (T) -> Unit) {
    while (hasNext()) {
        op(next())
    }
}

inline fun <T> Iterator<T>.forEachRemaining(op: (T) -> Unit) {
    while (hasNext()) {
        op(next())
    }
}

inline fun <T> Iterator<T>.filter(crossinline predicate: (T) -> Boolean): Iterator<T> =
        filter(Predicate { predicate(it) })