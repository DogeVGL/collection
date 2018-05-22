package org.glavo.collection.function;


import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface IndexedFunction<T, R> extends kotlin.Function<R> {
    R apply(int index, T value);

    static <T, R> @NotNull IndexedFunction<T, R> of(@NotNull Function<? super T, ? extends R> f) {
        Objects.requireNonNull(f);
        return (index, value) -> f.apply(value);
    }

    static <T, R> @NotNull IndexedFunction<T, R> of(@NotNull Function1<? super T, ? extends R> f) {
        Objects.requireNonNull(f);
        return (index, value) -> f.invoke(value);
    }
}
