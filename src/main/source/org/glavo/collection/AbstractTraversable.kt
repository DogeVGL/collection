package org.glavo.collection

abstract class AbstractTraversable<out T> : Traversable<T> {
    override fun toString(): String = joinToString(", ", "${className()}[", "]")
}