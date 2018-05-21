package org.glavo.collection

interface IndexedSeq<out T> : Seq<T>, RandomAccess {
    @JvmDefault
    override fun knownSize(): Int = size()

    @JvmDefault
    override fun className(): String = "IndexedSeq"
}
