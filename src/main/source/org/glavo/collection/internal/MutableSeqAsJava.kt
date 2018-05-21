package org.glavo.collection.internal

import org.glavo.collection.mutable.MutableSeq

class MutableSeqAsJava<T>(private val seq: MutableSeq<T>) : kotlin.collections.AbstractMutableList<T>() {
    override fun removeAt(index: Int): T {
        throw UnsupportedOperationException()
    }

    override fun set(index: Int, element: T): T {
        return seq.set(index, element)
    }

    override fun add(index: Int, element: T) {
        throw UnsupportedOperationException()
    }

    override val size: Int
        get() = seq.size()

    override fun get(index: Int): T = seq[index]
}