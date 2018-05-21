package org.glavo.collection.immutable

import org.glavo.collection.ArraySeq

class ImmutableArraySeq<out T> internal constructor(array: Array<*>) : ArraySeq<T>(array), ImmutableSeq<T>, Cloneable {
    override fun className(): String = "ImmutableArraySeq"

    override fun clone(): ImmutableArraySeq<T> = ImmutableArraySeq(array)
}