package org.glavo.collection

import org.glavo.collection.internal.IteratorAsJava
import org.glavo.collection.internal.IteratorView
import org.glavo.collection.mutable.Growable
import java.util.function.Predicate

interface Iterator<out T> : Traversable<T> {
    /**
     * Returns the next element in the iteration.
     */
    operator fun next(): T

    /**
     * Returns `true` if the iteration has more elements.
     */
    operator fun hasNext(): Boolean

    @JvmDefault
    fun nextOrNull(): T? = if (hasNext()) next() else null

    @JvmDefault
    fun nextValueOrNull(): Value<T>? = if (hasNext()) Value(next()) else null

    @JvmDefault
    override fun elementAt(index: Int): T {
        var idx = 0
        forEachRemaining {
            if (idx == index)
                return it
            idx++
        }
        throw NoSuchElementException()
    }

    @JvmDefault
    override fun elementAtOrNull(index: Int): T? {
        var idx = 0
        forEachRemaining {
            if (idx == index) {
                return it
            }
            idx++
        }
        return null
    }

    @JvmDefault
    override fun elementValueAtOrNull(index: Int): Value<T>? {
        var idx = 0
        forEachRemaining {
            if (idx == index) {
                return Value(it)
            }
            idx++
        }
        return null
    }

    @JvmDefault
    override fun size(): Int {
        var count = 0
        forEachRemaining { count++ }
        return count
    }

    @JvmDefault
    override fun isEmpty(): Boolean = !hasNext()

    @JvmDefault
    override fun contains(value: Any?): Boolean {
        forEachRemaining {
            if (it == value) {
                return true
            }
        }
        return false
    }

    @JvmDefault
    override fun <G : Growable<T>> filterTo(growable: G, predicate: Predicate<in T>): G {
        forEachRemaining { if (predicate.test(it)) growable.addOne(it) }
        return growable
    }

    @JvmDefault
    override fun <G : Growable<T>> filterNotTo(growable: G, predicate: Predicate<in T>): G {
        forEachRemaining { if (!predicate.test(it)) growable.addOne(it) }
        return growable
    }

    @JvmDefault
    override fun filter(predicate: Predicate<in T>): Iterator<T> {
        return IteratorView.Filtered(this, predicate)
    }

    @JvmDefault
    override operator fun iterator(): Iterator<T> = this

    @JvmDefault
    override fun asJava(): IterableKIterator<T> = IteratorAsJava(this)
}
