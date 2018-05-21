package org.glavo.collection

interface Set<out T> : Traversable<T> {
    @JvmDefault
    override fun className(): String = "Set"
}