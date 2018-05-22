package org.glavo.collection

@Suppress("EqualsOrHashCode")
abstract class AbstractSeq<out T> : AbstractTraversable<T>(), Seq<T> {
    override fun equals(other: Any?): Boolean = other is Seq<*> && this sameElements other
}
