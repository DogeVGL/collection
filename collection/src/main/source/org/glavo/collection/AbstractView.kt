package org.glavo.collection

abstract class AbstractView<out T> : View<T> {
    override fun toString(): String = "View[?]"
}