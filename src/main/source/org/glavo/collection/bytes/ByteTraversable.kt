package org.glavo.collection.bytes

interface ByteTraversable {
    /**
     * Returns an iterator over the elements of this object.
     */
    operator fun iterator(): ByteIterator
}