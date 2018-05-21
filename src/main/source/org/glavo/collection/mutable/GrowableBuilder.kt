package org.glavo.collection.mutable

class GrowableBuilder<E, G : Growable<E>>(val growable: G) : Builder<E, G>{
    override fun addOne(element: E) {
        growable.addOne(element)
    }

    override fun build(): G = growable
}