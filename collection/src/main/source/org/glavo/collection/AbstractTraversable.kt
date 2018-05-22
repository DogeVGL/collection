package org.glavo.collection

import java.util.*

abstract class AbstractTraversable<out T> : Traversable<T> {
    override fun hashCode(): Int {
        var count = 0
        for (elem in this) {
            count += Objects.hashCode(elem)
        }
        return count
    }

    override fun equals(other: Any?): Boolean =
            other is Traversable<*> && this sameElements other

    override fun toString(): String = joinToString(", ", "${className()}[", "]")
}