module kcollection.core {
    requires kotlin.stdlib;
    requires static annotations;

    exports org.glavo.collection;
    exports org.glavo.collection.function;
    exports org.glavo.collection.mutable;
    exports org.glavo.collection.immutable;

    exports org.glavo.collection.booleans;
    exports org.glavo.collection.booleans.mutable;
    exports org.glavo.collection.booleans.immutable;

    exports org.glavo.collection.chars;
    exports org.glavo.collection.chars.mutable;
    exports org.glavo.collection.chars.immutable;

    exports org.glavo.collection.bytes;
    exports org.glavo.collection.bytes.mutable;
    exports org.glavo.collection.bytes.immutable;

    exports org.glavo.collection.shorts;
    exports org.glavo.collection.shorts.mutable;
    exports org.glavo.collection.shorts.immutable;

    exports org.glavo.collection.ints;
    exports org.glavo.collection.ints.mutable;
    exports org.glavo.collection.ints.immutable;

    exports org.glavo.collection.longs;
    exports org.glavo.collection.longs.mutable;
    exports org.glavo.collection.longs.immutable;

    exports org.glavo.collection.floats;
    exports org.glavo.collection.floats.mutable;
    exports org.glavo.collection.floats.immutable;

    exports org.glavo.collection.doubles;
    exports org.glavo.collection.doubles.mutable;
    exports org.glavo.collection.doubles.immutable;
}