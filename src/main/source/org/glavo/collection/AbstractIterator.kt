package org.glavo.collection

abstract class AbstractIterator<out T> : AbstractTraversable<T>(), Iterator<T> {
    override fun toString(): String =
            if (hasNext()) "<non-empty iterator>" else "<empty iterator>"
}