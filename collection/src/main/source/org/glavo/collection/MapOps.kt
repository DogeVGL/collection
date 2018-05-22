@file:Suppress("NOTHING_TO_INLINE")

package org.glavo.collection

inline operator fun <T> MapEntry<T, *>.component1(): T = key

inline operator fun <T> MapEntry<*, T>.component2(): T = value
