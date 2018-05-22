package org.glavo.collection.immutable

import org.glavo.collection.AbstractSeq

abstract class AbstractImmutableSeq<out T> : AbstractSeq<T>(), ImmutableSeq<T>