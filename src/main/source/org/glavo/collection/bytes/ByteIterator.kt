package org.glavo.collection.bytes

interface ByteIterator : ByteTraversable {
    /**
     * Returns the next element in the iteration.
     */
    operator fun next(): Byte

    /**
     * Returns `true` if the iteration has more elements.
     */
    operator fun hasNext(): Boolean

    @JvmDefault
    override fun iterator(): ByteIterator = this
}