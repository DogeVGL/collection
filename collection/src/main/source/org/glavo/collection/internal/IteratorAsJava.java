package org.glavo.collection.internal;

import org.glavo.collection.IterableKIterator;
import org.glavo.collection.Iterator;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class IteratorAsJava<T> implements IterableKIterator<T> {
    private Iterator<T> it;

    public IteratorAsJava(@NotNull Iterator<T> it) {
        Objects.requireNonNull(it);
        this.it = it;
    }


    @Override
    public boolean hasNext() {
        if (it == null) {
            return false;
        }
        if (it.hasNext()) {
            return true;
        }
        it = null;
        return false;
    }

    @Override
    public T next() {
        if (it == null) {
            throw new NoSuchElementException();
        }
        return it.next();
    }
}
