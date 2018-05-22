package org.glavo.collection

import org.glavo.collection.internal.MapEntryAsJava
import kotlin.collections.Map

interface MapEntry<out K, out V> {
    val key: K
    val value: V

    @JvmDefault
    fun asJava(): kotlin.collections.Map.Entry<K, V> = MapEntryAsJava(this)
}

data class DefaultMapEntry<out K, out V>(override val key: K, override val value: V) : MapEntry<K, V>, kotlin.collections.Map.Entry<K, V> {
    override fun asJava(): Map.Entry<K, V> = this
}