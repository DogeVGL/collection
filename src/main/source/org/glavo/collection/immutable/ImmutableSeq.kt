package org.glavo.collection.immutable

import org.glavo.collection.*
import org.glavo.collection.mutable.ArrayBuffer
import org.glavo.collection.mutable.Builder
import org.glavo.collection.mutable.plusAssign

interface ImmutableSeq<out T> : Seq<T>, Immutable {
    @JvmDefault
    override fun updated(index: Int, newValue: @UnsafeVariance T): ImmutableSeq<T> {
        val b = newTBuilder()
        if (index >= this.size) {
            throw IndexOutOfBoundsException()
        }
        var idx = 0
        for (elem in this) {
            b += if (idx == index) {
                newValue
            } else {
                elem
            }
            idx++
        }
        return b.build()
    }

    @JvmDefault
    override fun <U> newBuilder(): Builder<U, ImmutableSeq<U>> = ArrayBuffer()

    @JvmDefault
    override fun newTBuilder(): Builder<@UnsafeVariance T, ImmutableSeq<T>> = newBuilder()
}