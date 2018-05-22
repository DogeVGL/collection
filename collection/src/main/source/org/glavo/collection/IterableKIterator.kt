package org.glavo.collection

import kotlin.collections.Iterator

interface IterableKIterator<out T> : Iterable<T>, KIterator<T> {
    @JvmDefault
    override fun iterator(): Iterator<T> = this

    override fun hasNext(): Boolean

    override fun next(): T
}