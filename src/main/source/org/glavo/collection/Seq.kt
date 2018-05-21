package org.glavo.collection

import org.glavo.collection.internal.SeqAsJava
import org.glavo.collection.mutable.ArrayBuffer
import org.glavo.collection.mutable.Builder
import org.glavo.collection.mutable.plusAssign
import java.util.function.Predicate

interface Seq<out T> : Traversable<T> {
    operator fun get(index: Int): T

    @JvmDefault
    fun updated(index: Int, newValue: @UnsafeVariance T): Seq<T> {
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
    override fun filter(predicate: Predicate<in T>): Seq<T> = filterTo(newTBuilder(), predicate).build()

    @JvmDefault
    override fun filterNot(predicate: Predicate<in T>): Seq<T> = filterNotTo(newTBuilder(), predicate).build()

    @JvmDefault
    override fun className(): String = "Seq"

    @JvmDefault
    override fun <U> newBuilder(): Builder<U, Seq<U>> = ArrayBuffer()

    @JvmDefault
    override fun newTBuilder(): Builder<@UnsafeVariance T, Seq<T>> = newBuilder()

    @JvmDefault
    override fun asJava(): kotlin.collections.List<T> {
        return SeqAsJava(this)
    }
}