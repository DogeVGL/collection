@file:Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")

package org.glavo.collection.mutable

import org.glavo.collection.Traversable
import org.glavo.collection.immutable.ImmutableArraySeq
import org.glavo.collection.immutable.Unsafe

class ArrayBuffer<T>
@JvmOverloads
constructor(initialCapacity: Int = 0) : ArrayBufferBase<T>(initialCapacity), Builder<T, ImmutableArraySeq<T>> {
    companion object Factory {
        @JvmName("of")
        @JvmStatic
        operator fun <T> invoke(vararg elements: T): ArrayBuffer<T> {
            val buf = ArrayBuffer<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Traversable<T>): ArrayBuffer<T> {
            val buf = ArrayBuffer<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Iterable<T>): ArrayBuffer<T> {
            val buf = ArrayBuffer<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Sequence<T>): ArrayBuffer<T> {
            val buf = ArrayBuffer<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(elements: Array<out T>): ArrayBuffer<T> {
            val buf = ArrayBuffer<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> builder(): Builder<T, ArrayBuffer<T>> = GrowableBuilder(ArrayBuffer())

        @JvmStatic
        fun <T> empty(): ArrayBuffer<T> = ArrayBuffer()
    }

    override fun mapInPlace(op: (T) -> T): ArrayBuffer<T> {
        for (i in 0..(size - 1)) {
            data[i] = op(data[i] as T)
        }
        return this
    }

    override fun growSize(size: Int) {
        super.grow(size)
    }

    override fun build(): ImmutableArraySeq<T> {
        return Unsafe.newImmutableArraySeq<T>(toArray())
    }

    override fun className(): String = "ArrayBuffer"

    override fun clone(): ArrayBuffer<T> {
        return super.clone() as ArrayBuffer<T>
    }
}