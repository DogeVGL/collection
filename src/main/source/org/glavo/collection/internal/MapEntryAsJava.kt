package org.glavo.collection.internal

import org.glavo.collection.MapEntry

class MapEntryAsJava<out K, out V>(val entry: MapEntry<K, V>) : kotlin.collections.Map.Entry<K, V> {
    override val key: K
        get() = entry.key
    override val value: V
        get() = entry.value
}