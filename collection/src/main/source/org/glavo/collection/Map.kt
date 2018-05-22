package org.glavo.collection

interface Map<K, out V> {
    operator fun iterator(): MapIterator<K, V>
}