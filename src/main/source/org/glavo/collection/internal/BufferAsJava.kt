package org.glavo.collection.internal

import org.glavo.collection.mutable.Buffer

class BufferAsJava<T>(private val buffer: Buffer<T>) : kotlin.collections.AbstractMutableList<T>() {
    override fun removeAt(index: Int): T =
            buffer.removeAt(index)

    override fun set(index: Int, element: T): T {
        return buffer.set(index, element)
    }

    override fun add(index: Int, element: T) {
        buffer.add(index, element)
    }

    override val size: Int
        get() = buffer.size()

    override fun get(index: Int): T = buffer[index]
}