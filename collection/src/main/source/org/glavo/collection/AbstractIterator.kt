package org.glavo.collection

abstract class AbstractIterator<out T> : AbstractTraversable<T>(), Iterator<T> {
    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }

    override fun equals(other: Any?): Boolean = this === other

    override fun toString(): String =
            if (hasNext()) "<non-empty iterator>" else "<empty iterator>"
}