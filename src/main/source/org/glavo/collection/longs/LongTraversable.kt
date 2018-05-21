package org.glavo.collection.longs

interface LongTraversable {
    /**
     * Returns an iterator over the elements of this object.
     */
    operator fun iterator(): LongIterator
}