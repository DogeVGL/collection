package org.glavo.collection.internal;

import org.glavo.collection.AbstractIterator;
import org.glavo.collection.Iterator;

import java.util.NoSuchElementException;

public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final Iterator EMPTY_ITERATOR = new AbstractIterator() {
        @Override
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    };
}
