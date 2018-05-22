package org.glavo.collection

interface View<out T> : Traversable<T> {
    @JvmDefault
    override fun className(): String = "View"
}