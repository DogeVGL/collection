@file:Suppress("UNCHECKED_CAST")

package org.glavo.collection.mutable

import org.glavo.collection.Resizable
import org.glavo.collection.Traversable
import org.glavo.collection.internal.BufferAsJava

interface Buffer<T> : MutableSeq<T>, Growable<T>, Clearable, Resizable {
    @JvmDefault
    fun add(element: T) {
        add(size(), element)
    }

    fun add(index: Int, element: T)

    @JvmDefault
    fun addAll(index: Int, elements: Traversable<T>) {
        addAll(index, elements.toArray() as Array<out T>)
    }

    @JvmDefault
    fun addAll(index: Int, elements: Iterable<T>) {
        val al = arrayListOf<T>()
        al.addAll(elements)
        addAll(index, al.toArray() as Array<out T>)
    }

    @JvmDefault
    fun addAll(index: Int, elements: Sequence<T>) {
        val al = arrayListOf<T>()
        al.addAll(elements)
        addAll(index, al.toArray() as Array<out T>)
    }

    fun addAll(index: Int, elements: Array<out T>)

    @JvmDefault
    override fun addOne(element: T) {
        add(element)
    }

    fun remove(value: Any?): Boolean

    fun removeAt(index: Int): T

    override fun mapInPlace(op: (T) -> T): Buffer<T>

    @JvmDefault
    override fun className(): String = "Buffer"

    @JvmDefault
    override fun asJava(): MutableList<T> {
        return BufferAsJava(this)
    }
}
