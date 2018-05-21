package org.glavo.collection.internal

import org.glavo.collection.Seq

class SeqAsJava<out T>(private val seq: Seq<T>) : kotlin.collections.AbstractList<T>() {
    override val size: Int
        get() = seq.size()

    override fun get(index: Int): T = seq[index]
}