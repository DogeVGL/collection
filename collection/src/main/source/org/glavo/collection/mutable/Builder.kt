package org.glavo.collection.mutable

interface Builder<in T, out R> : Growable<T> {
    override fun addOne(element: T)

    fun build(): R

    @JvmDefault
    fun growSize(size: Int) {
    }
}
