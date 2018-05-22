package org.glavo.collection.immutable;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Unsafe {
    private Unsafe() {
    }

    public static <T> @NotNull ImmutableArraySeq<T> newImmutableArraySeq(Object[] array) {
        Objects.requireNonNull(array);
        return new ImmutableArraySeq<>(array);
    }
}
