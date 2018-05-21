package org.glavo.collection.mutable

import org.glavo.collection.Seq
import org.glavo.collection.internal.MutableSeqAsJava

interface MutableSeq<T> : MutableTraversable<T>, Seq<T> {
    operator fun set(index: Int, value: T): T

    fun mapInPlace(op: (T) -> T): MutableSeq<T>

    @JvmDefault
    override fun className(): String = "MutableSeq"

    @JvmDefault
    override fun asJava(): MutableList<T> {
        return MutableSeqAsJava(this)
    }
}