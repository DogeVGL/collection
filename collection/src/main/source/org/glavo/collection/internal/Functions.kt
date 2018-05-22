package org.glavo.collection.internal

import java.util.function.Predicate

val isNullPredicate: Predicate<in Any?> = java.util.function.Predicate.isEqual(null)