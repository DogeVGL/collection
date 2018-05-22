@file:Suppress("UNCHECKED_CAST")

package org.glavo.collection.mutable

import org.glavo.collection.Seq
import org.glavo.collection.Traversable
import java.util.function.IntFunction
import kotlin.reflect.KClass

class ArrayBuilder<T>
@JvmOverloads
constructor(private val arrayConstructor: IntFunction<out Array<T>>, initialCapacity: Int = 0)
    : ArrayBufferBase<T>(initialCapacity), Builder<T, Array<T>> {
    companion object Factory {
        @JvmName("ofEmpty")
        inline operator fun <reified T> invoke(): ArrayBuilder<T> = empty()

        @JvmName("ofTyped")
        @JvmStatic
        inline operator fun <reified T> invoke(vararg elements: T): ArrayBuilder<T> {
            val buf = ArrayBuilder(arrayConstructorOf<T>())
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> of(type: Class<T>, vararg elements: T): ArrayBuilder<T> {
            val buf = ArrayBuilder(arrayConstructorOf(type))
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T : Any> of(type: KClass<T>, vararg elements: T): ArrayBuilder<T> {
            val buf = ArrayBuilder(IntFunction { java.lang.reflect.Array.newInstance(type.java, it) as Array<T> })
            buf.addAll(elements)
            return buf
        }

        inline fun <reified T> from(elements: Traversable<T>): ArrayBuilder<T> {
            val buf = ArrayBuilder<T>()
            buf.addAll(elements)
            return buf
        }

        inline fun <reified T> from(elements: Iterable<T>): ArrayBuilder<T> {
            val buf = ArrayBuilder<T>()
            buf.addAll(elements)
            return buf
        }

        inline fun <reified T> from(elements: Sequence<T>): ArrayBuilder<T> {
            val buf = ArrayBuilder<T>()
            buf.addAll(elements)
            return buf
        }

        inline fun <reified T> from(elements: Array<out T>): ArrayBuilder<T> {
            val buf = ArrayBuilder<T>()
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(type: Class<T>, elements: Traversable<T>): ArrayBuilder<T> {
            val buf = empty(type)
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(type: Class<T>, elements: Iterable<T>): ArrayBuilder<T> {
            val buf = empty(type)
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(type: Class<T>, elements: Sequence<T>): ArrayBuilder<T> {
            val buf = empty(type)
            buf.addAll(elements)
            return buf
        }

        @JvmStatic
        fun <T> from(type: Class<T>, elements: Array<out T>): ArrayBuilder<T> {
            val buf = empty(type)
            buf.addAll(elements)
            return buf
        }

        inline fun <reified T> builder(): Builder<T, ArrayBuilder<T>> =
                GrowableBuilder(ArrayBuilder(arrayConstructorOf()))

        @JvmStatic
        fun <T> builder(type: Class<T>): Builder<T, ArrayBuilder<T>> =
                GrowableBuilder(ArrayBuilder(arrayConstructorOf(type)))

        inline fun <reified T> empty(): ArrayBuilder<T> =
                ArrayBuilder(arrayConstructorOf())

        @JvmStatic
        fun <T> empty(type: Class<T>): ArrayBuilder<T> =
                ArrayBuilder(arrayConstructorOf(type))

        inline fun <reified T> arrayConstructorOf(): IntFunction<Array<T>> =
                IntFunction { arrayOfNulls<T>(it) as Array<T> }

        fun <T> arrayConstructorOf(type: Class<T>): IntFunction<Array<T>> =
                IntFunction { java.lang.reflect.Array.newInstance(type, it) as Array<T> }
    }

    override fun growSize(size: Int) {
        grow(size)
    }

    override fun build(): Array<T> = toArray(arrayConstructor)

    override fun newTBuilder(): Builder<T, ArrayBuilder<T>> = GrowableBuilder(ArrayBuilder(arrayConstructor, DEFAULT_CAPACITY))

    override fun className(): String = "ArrayBuilder"
}