@file:Suppress("UNCHECKED_CAST")

package org.glavo.collection.mutable

import org.glavo.collection.ArraySeq

class MutableArraySeq<T>(array: Array<T>) : ArraySeq<T>(array), MutableSeq<T>, Cloneable {
    override fun mapInPlace(op: (T) -> T): MutableArraySeq<T> {
        val arr = array as Array<T>
        val len = arr.size
        for (i in 0..(len - 1)) {
            arr[i] = op(arr[i])
        }
        return this
    }

    override fun set(index: Int, value: T): T {
        val array = this.array as Array<T>
        val old = array[index]
        array[index] = value
        return old
    }

    override fun clone(): MutableArraySeq<T> = MutableArraySeq(array.clone() as Array<T>)

    override fun className(): String = "MutableArraySeq"
}