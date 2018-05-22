@file:JvmName("Traversables")

package org.glavo.collection

import org.glavo.collection.internal.CollectionUtils
import org.glavo.collection.internal.TraversableAsJava
import org.glavo.collection.internal.isNullPredicate
import org.glavo.collection.mutable.ArrayBuffer
import org.glavo.collection.mutable.Builder
import org.glavo.collection.mutable.Growable
import java.util.*
import java.util.function.IntFunction
import java.util.function.Predicate
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList

interface Traversable<out T> {
    /**
     * Returns an iterator over the elements of this object.
     */
    operator fun iterator(): Iterator<T>

    @JvmDefault
    fun elementAt(index: Int): T = iterator().elementAt(index)

    @JvmDefault
    fun elementAtOrNull(index: Int): T? = iterator().elementAtOrNull(index)

    @JvmDefault
    fun elementValueAtOrNull(index: Int): Value<T>? = iterator().elementValueAtOrNull(index)

    @JvmDefault
    fun size(): Int = iterator().size()

    @JvmDefault
    fun knownSize(): Int = -1

    @JvmDefault
    fun isEmpty(): Boolean = iterator().hasNext()

    @JvmDefault
    fun isNotEmpty(): Boolean = !isEmpty()

    @JvmDefault
    fun contains(value: Any?): Boolean =
            iterator().contains(value)

    @JvmDefault
    fun count(): Long = count(CollectionUtils.ALWAYS_TRUE)

    @JvmDefault
    fun count(predicate: Predicate<in T>): Long {
        var i = 0L
        forEach { i++ }
        return i
    }

    @JvmDefault
    fun <G : Growable<T>> filterTo(growable: G, predicate: Predicate<in T>): G {
        return iterator().filterTo(growable, predicate)
    }

    @JvmDefault
    fun <G : Growable<T>> filterNotTo(growable: G, predicate: Predicate<in T>): G {
        return iterator().filterTo(growable, predicate)
    }

    @JvmDefault
    fun <G : Growable<T>> filterNotNullTo(growable: G): G {
        return filterTo(growable, isNullPredicate)
    }

    @JvmDefault
    fun filter(predicate: Predicate<in T>): Traversable<T> =
            filterTo(newTBuilder(), predicate).build()

    @JvmDefault
    fun filterNot(predicate: Predicate<in T>): Traversable<T> =
            filterNotTo(newTBuilder(), predicate).build()

    @JvmDefault
    fun first(): T = iterator().next()

    @JvmDefault
    fun firstOrNull(): T? = if (isEmpty()) null else first()

    @JvmDefault
    fun firstValueOrNull(): Value<T>? = if (isEmpty()) null else Value(first())

    @JvmDefault
    fun first(predicate: Predicate<in T>): T {
        for (elem in this) {
            if (predicate.test(elem)) {
                return elem
            }
        }
        throw NoSuchElementException()
    }

    @JvmDefault
    fun firstOrNull(predicate: Predicate<in T>): T? = if (isEmpty()) null else first()

    @JvmDefault
    fun firstValueOrNull(predicate: Predicate<in T>): Value<T>? = if (isEmpty()) null else Value(first())

    @JvmDefault
    fun <A : Appendable> joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): A {
        val t: (T) -> CharSequence = transform ?: { Objects.toString(it) }
        buffer.append(prefix)
        var count = 0
        for (element in this) {
            if (++count > 1) buffer.append(separator)
            if (limit < 0 || count <= limit) {
                buffer.append(t(element))
            } else break
        }
        if (limit in 0..(count - 1)) buffer.append(truncated)
        buffer.append(postfix)
        return buffer
    }

    @JvmDefault
    fun joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): String =
            joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()

    @JvmDefault
    fun toArray(): Array<Any?> {
        val l = ArrayList<Any?>(knownSize())
        for (elem in this) {
            l.add(elem)
        }
        return l.toArray()
    }

    @JvmDefault
    fun <U> toArray(constructor: IntFunction<out Array<U>>): Array<U> {
        val l = ArrayList<Any?>(knownSize())
        for (elem in this) {
            l.add(elem)
        }
        return l.toArray(constructor.apply(l.size))
    }

    @JvmDefault
    fun <U> toArray(array: Array<out U>): Array<U> {
        val l = ArrayList<Any?>(knownSize())
        for (elem in this) {
            l.add(elem)
        }
        return l.toArray(array)
    }

    @JvmDefault
    fun className(): String = "Traversable"

    @JvmDefault
    fun <U> newBuilder(): Builder<U, Traversable<U>> = ArrayBuffer()

    @JvmDefault
    fun newTBuilder(): Builder<@UnsafeVariance T, Traversable<T>> = newBuilder()

    @JvmDefault
    infix fun sameElements(other: Traversable<*>): Boolean {
        val it1 = this.iterator()
        val it2 = other.iterator()
        while (it1.hasNext() && it2.hasNext()) {
            if (it1.next() != it2.next()) {
                return false
            }
        }
        return it1.hasNext() == it2.hasNext()
    }

    @JvmDefault
    fun asJava(): Iterable<T> = TraversableAsJava(this)
}