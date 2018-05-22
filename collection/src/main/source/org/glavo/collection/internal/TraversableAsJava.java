package org.glavo.collection.internal;

import org.glavo.collection.Traversable;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Objects;

public final class TraversableAsJava<T> implements Iterable<T> {
    private Traversable<T> traversable;

    public TraversableAsJava(@NotNull Traversable<T> traversable) {
        Objects.requireNonNull(traversable);
        this.traversable = traversable;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return traversable.iterator().asJava();
    }
}
