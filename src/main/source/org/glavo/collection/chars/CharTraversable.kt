package org.glavo.collection.chars

interface CharTraversable {
    /**
     * Returns an iterator over the elements of this object.
     */
    operator fun iterator(): CharIterator
}