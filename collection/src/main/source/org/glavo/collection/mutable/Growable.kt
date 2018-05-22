package org.glavo.collection.mutable

import org.glavo.collection.Traversable

interface Growable<in T> {
    fun addOne(element: T)

    @JvmDefault
    fun addAll(elements: Iterable<T>) {
        for (element in elements) {
            addOne(element)
        }
    }

    @JvmDefault
    fun addAll(elements: Sequence<T>) {
        for (element in elements) {
            addOne(element)
        }
    }

    @JvmDefault
    fun addAll(elements: Traversable<T>) {
        for (element in elements) {
            addOne(element)
        }
    }

    @JvmDefault
    fun addAll(elements: Array<out T>) {
        for(element in elements) {
            addOne(element)
        }
    }
}

