package org.glavo.collection

interface IndexedSeq<out T> : Seq<T>, RandomAccess {
    @JvmDefault
    override fun getOrNull(index: Int): T? {
        if(index in 0..(size() - 1)) {
            return this[index]
        }
        return null
    }

    override fun getValueOrNull(index: Int): Value<T>? {
        if(index in 0..(size() - 1)) {
            return Value(this[index])
        }
        return null
    }

    @JvmDefault
    override fun first(): T = this[0]

    @JvmDefault
    override fun knownSize(): Int = size()

    @JvmDefault
    override fun className(): String = "IndexedSeq"
}
