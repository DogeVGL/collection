@file:Suppress("UNCHECKED_CAST")

package org.glavo.collection.internal

import org.glavo.collection.AbstractIterator
import org.glavo.collection.function.IndexedFunction
import org.glavo.collection.Iterator
import java.util.function.Predicate

interface IteratorView<T> : Iterator<T> {
    class Filtered<T>(private val iterator: org.glavo.collection.Iterator<T>, private val predicate: Predicate<in T>) : IteratorView<T> {

        private var head: T? = null
        private var headIsDefined = false

        override fun hasNext(): Boolean {
            if (headIsDefined) {
                return true
            }
            do {
                if (!iterator.hasNext()) {
                    return false
                }
                head = iterator.next()
            } while (!predicate.test(head as T))
            headIsDefined = true
            return true
        }

        override fun next(): T {
            if (hasNext()) {
                headIsDefined = false
                return head as T
            }

            throw NoSuchElementException()
        }
    }

    class MappedIndexed<T, U>(private val iterator: org.glavo.collection.Iterator<T>, private val mapper: IndexedFunction<in T, out U>) : AbstractIterator<U>(), IteratorView<U> {

        private var index = 0

        override fun hasNext(): Boolean {
            return iterator.hasNext()
        }

        override fun next(): U {
            return mapper.apply(index++, iterator.next())
        }
    }

    class Mapped<T, U>(private val iterator: org.glavo.collection.Iterator<T>, private val mapper: Function1<T, U>) : AbstractIterator<U>(), IteratorView<U> {

        override fun hasNext(): Boolean {
            return iterator.hasNext()
        }

        override fun next(): U {
            return mapper.invoke(iterator.next())
        }
    }

    class Concat<T>(private val iterators: org.glavo.collection.Iterator<org.glavo.collection.Iterator<T>>) : AbstractIterator<T>(), IteratorView<T> {
        private var theIterator: org.glavo.collection.Iterator<T>? = null

        override fun hasNext(): Boolean {
            if (theIterator == null) {
                if (iterators.hasNext()) {
                    theIterator = iterators.next()
                } else {
                    return false
                }
            }
            while (!theIterator!!.hasNext()) {
                if (iterators.hasNext()) {
                    theIterator = iterators.next()
                } else {
                    theIterator = null
                    return false
                }
            }

            return true
        }

        override fun next(): T {
            if (theIterator == null) {
                if (iterators.hasNext()) {
                    theIterator = iterators.next()
                } else {
                    throw NoSuchElementException()
                }
            }
            while (!theIterator!!.hasNext()) {
                if (iterators.hasNext()) {
                    theIterator = iterators.next()
                } else {
                    theIterator = null
                    throw NoSuchElementException()
                }
            }
            return theIterator!!.next()
        }
    }

    class Take<T>(private val iterator: org.glavo.collection.Iterator<T>, private val n: Int) : AbstractIterator<T>(), IteratorView<T> {

        private var count = 0

        override fun hasNext(): Boolean {
            return iterator.hasNext() && count < n
        }

        override fun next(): T {
            if (hasNext()) {
                count++
                return iterator.next()
            }
            throw NoSuchElementException()
        }
    }

    class Zip<T, U>(private val iterator1: org.glavo.collection.Iterator<T>, private val iterator2: org.glavo.collection.Iterator<U>) : AbstractIterator<Pair<T, U>>(), IteratorView<Pair<T, U>> {

        override fun hasNext(): Boolean {
            return iterator1.hasNext() && iterator2.hasNext()
        }

        override fun next(): Pair<T, U> {
            return Pair(iterator1.next(), iterator2.next())
        }
    }

}