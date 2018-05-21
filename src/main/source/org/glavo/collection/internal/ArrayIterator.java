package org.glavo.collection.internal;

import org.glavo.collection.AbstractIterator;
import org.glavo.collection.Iterator;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public final class ArrayIterator<T> extends AbstractIterator<T> implements Iterator<T> {
    private Object[] array;
    private int index = 0;
    private int end;

    public ArrayIterator(Object[] array) {
        this.array = array;
        this.end = array.length;
    }

    public ArrayIterator(Object[] array, int start, int end) {
        this.array = array;
        index = start;
        this.end = end;
    }

    @Override
    public T next() {
        if (array == null) {
            throw new NoSuchElementException();
        }
        if (index >= end) {
            array = null;
            throw new NoSuchElementException();
        }
        return (T) array[index++];
    }

    @Override
    public boolean hasNext() {
        if (array == null) {
            return false;
        }
        if (index >= end) {
            array = null;
            return false;
        }
        return index < array.length;
    }
}
