@file:Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")

package org.glavo.collection.mutable

import org.glavo.collection.IndexedSeq
import org.glavo.collection.Iterator
import org.glavo.collection.Traversable
import org.glavo.collection.internal.ArrayIterator
import org.glavo.collection.internal.CollectionUtils
import java.io.Serializable
import java.util.*
import java.util.function.IntFunction
import kotlin.math.max

open class ArrayBufferBase<T>
@JvmOverloads
constructor(initialCapacity: Int = 0)
    : AbstractBuffer<T>(), IndexedSeq<T>, Cloneable, Serializable {
    companion object Factory {
        const val DEFAULT_CAPACITY = 16
        @JvmName("of")
        @JvmStatic
        operator fun <T> invoke(vararg elements: T): ArrayBufferBase<T> {
            val buf = ArrayBufferBase<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Traversable<T>): ArrayBufferBase<T> {
            val buf = ArrayBufferBase<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Iterable<T>): ArrayBufferBase<T> {
            val buf = ArrayBufferBase<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Sequence<T>): ArrayBufferBase<T> {
            val buf = ArrayBufferBase<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Array<out T>): ArrayBufferBase<T> {
            val buf = ArrayBufferBase<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> builder(): Builder<T, ArrayBufferBase<T>> = GrowableBuilder(ArrayBufferBase())

        @JvmStatic
        fun <T> empty(): ArrayBufferBase<T> = ArrayBufferBase()
    }

    @JvmField
    protected var data: Array<Any?>
    @JvmField
    protected var size = 0

    init {
        data = if (initialCapacity > 0) {
            arrayOfNulls(initialCapacity)
        } else {
            CollectionUtils.EMPTY_OBJECT_ARRAY
        }
    }

    override fun iterator(): Iterator<T> {
        return if (size == 0) {
            CollectionUtils.EMPTY_ITERATOR as Iterator<T>
        } else {
            ArrayIterator(data, 0, size)
        }
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return data[index] as T
    }

    override fun set(index: Int, value: T): T {
        checkIndex(index)
        val data = this.data
        val old = data[index] as T
        data[index] = value
        return old
    }

    override fun add(element: T) {
        growOne()
        data[size++] = element
    }

    override fun add(index: Int, element: T) {
        if (index > size || index < 0) {
            indexOutOfBounds(index)
        }
        if (index == size) {
            add(element)
        }
        growOne()
        System.arraycopy(
                data, index,
                data, index + 1,
                size - index
        )
        data[index] = element
        size++
    }

    override fun addAll(elements: Array<out T>) {
        val size = this.size
        val arrSize = elements.size
        grow(size + arrSize)
        System.arraycopy(
                elements, 0,
                data, size,
                arrSize
        )
        this.size += arrSize
    }

    override fun addAll(index: Int, elements: Array<out T>) {
        val size = this.size
        if (index > size || index < 0) {
            indexOutOfBounds(index)
        }
        if (index == size) {
            addAll(elements)
        }
        val arrSize = elements.size
        grow(size + arrSize)
        System.arraycopy(
                data, index,
                data, index + arrSize,
                arrSize
        )
        System.arraycopy(
                elements, 0,
                data, index,
                arrSize
        )
        this.size += arrSize
    }

    override fun remove(value: Any?): Boolean {
        val data = data
        val size = size
        for (i in 0..(size - 1)) {
            if (data[i] == value) {
                TODO()
            }
        }
        return false
    }

    override fun removeAt(index: Int): T {
        checkIndex(index)
        val data = data
        val size = size
        val v = data[index] as T
        if (index == size - 1) {
            data[size] = null
        } else {
            System.arraycopy(
                    data, index + 1,
                    data, index,
                    size - index - 1
            )
        }
        this.size--
        return v
    }

    override fun mapInPlace(op: (T) -> T): ArrayBufferBase<T> {
        for (i in 0..(size - 1)) {
            data[i] = op(data[i] as T)
        }
        return this
    }

    override fun toArray(): Array<Any?> {
        return Arrays.copyOf(data, size)
    }

    override fun <U> toArray(constructor: IntFunction<out Array<U>>): Array<U> {
        val arr = constructor.apply(size)
        if (size != 0) {
            System.arraycopy(
                    data, 0,
                    arr, 0,
                    size
            )
        }
        return arr
    }

    override fun clear() {
        Arrays.fill(data, null)
        size = 0
    }

    override fun className(): String = "ArrayBufferBase"

    override fun clone(): ArrayBufferBase<T> {
        val ab = super.clone() as ArrayBufferBase<T>
        ab.data = Arrays.copyOf(data, size)
        return ab
    }

    protected inline fun growOne() {
        if (data.size - size < 1) {
            grow(DEFAULT_CAPACITY)
        }
    }

    protected inline fun grow(minSize: Int) {
        val newSize = max(minSize, size + size shr 1)
        if (newSize > data.size) {
            data = Arrays.copyOf(data, newSize)
        }
    }

    protected inline fun checkIndex(index: Int) {
        if (index !in 0..(size - 1)) {
            indexOutOfBounds(index)
        }
    }

    protected inline fun indexOutOfBounds(index: Int): Nothing = throw IndexOutOfBoundsException(index.toString())
}