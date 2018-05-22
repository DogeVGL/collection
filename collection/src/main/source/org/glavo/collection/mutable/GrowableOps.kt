@file:Suppress("NOTHING_TO_INLINE")

package org.glavo.collection.mutable

inline operator fun <T> Growable<T>.plusAssign(elem: T) {
    this.addOne(elem)
}

inline fun <T, G : Growable<T>> G.append(elem: T): G {
    addOne(elem)
    return this
}