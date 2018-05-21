package org.glavo.collection.booleans

interface BooleanIterator : BooleanTraversable {
    /**
     * Returns the next element in the iteration.
     */
    operator fun next(): Boolean

    /**
     * Returns `true` if the iteration has more elements.
     */
    operator fun hasNext(): Boolean

    @JvmDefault
    override fun iterator(): BooleanIterator = this
}