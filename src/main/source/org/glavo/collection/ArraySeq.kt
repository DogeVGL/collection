@file:Suppress("UNCHECKED_CAST")

package org.glavo.collection

import org.glavo.collection.internal.ArrayIterator
import java.util.function.IntFunction

abstract class ArraySeq<out T>(@JvmField protected val array: Array<*>) : AbstractIndexedSeq<T>(), IndexedSeq<T> {
    override fun get(index: Int): T {
        try {
            return array[index] as T
        } catch (e: ArrayIndexOutOfBoundsException) {
            throw IndexOutOfBoundsException(e.message)
        }
    }

    override fun size(): Int = array.size

    override fun toArray(): Array<Any?> {
        val size = size
        val newArr = arrayOfNulls<Any>(size)
        System.arraycopy(array, 0, newArr, 0, size)
        return newArr
    }

    override fun <U> toArray(constructor: IntFunction<out Array<U>>): Array<U> {
        val size = size
        val newArr = constructor.apply(size)
        System.arraycopy(array, 0, newArr, 0, size)
        return newArr
    }

    override fun iterator(): Iterator<T> =
            ArrayIterator(array)

    override fun className(): String = "ArraySeq"
}