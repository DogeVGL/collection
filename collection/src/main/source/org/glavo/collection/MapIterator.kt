package org.glavo.collection

interface MapIterator<out K, out V> {
    operator fun hasNext(): Boolean

    operator fun next(): MapEntry<K, V>
}