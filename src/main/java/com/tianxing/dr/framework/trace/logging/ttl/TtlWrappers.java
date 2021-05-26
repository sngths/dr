package com.tianxing.dr.framework.trace.logging.ttl;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlEnhanced;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlWrapper;

import java.util.function.*;

import static com.tianxing.dr.framework.trace.logging.ttl.TransmittableThreadLocal.Transmitter.*;

public class TtlWrappers {

    public static <T> Supplier<T> wrap(Supplier<T> supplier) {
        if (supplier == null) return null;
        else if (supplier instanceof TtlEnhanced) return supplier;
        else return new TtlSupplier<>(supplier);
    }

    private static class TtlSupplier<T> implements Supplier<T>, TtlWrapper<Supplier<T>>, TtlEnhanced {
        final Supplier<T> supplier;
        final Object captured;

        TtlSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
            this.captured = capture();
        }

        @Override
        public T get() {
            final Object backup = replay(captured);
            try {
                return supplier.get();
            } finally {
                restore(backup);
            }
        }

        @Override
        public Supplier<T> unwrap() {
            return supplier;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TtlSupplier<?> that = (TtlSupplier<?>) o;
            return supplier.equals(that.supplier);
        }

        @Override
        public int hashCode() {
            return supplier.hashCode();
        }

        @Override
        public String toString() {
            return this.getClass().getName() + " - " + supplier.toString();
        }
    }

    public static <T> Consumer<T> wrap(Consumer<T> consumer) {
        if (consumer == null) return null;
        else if (consumer instanceof TtlEnhanced) return consumer;
        else return new TtlConsumer<>(consumer);
    }

    private static class TtlConsumer<T> implements Consumer<T>, TtlWrapper<Consumer<T>>, TtlEnhanced {
        final Consumer<T> consumer;
        final Object captured;

        TtlConsumer(Consumer<T> consumer) {
            this.consumer = consumer;
            this.captured = capture();
        }

        @Override
        public void accept(T t) {
            final Object backup = replay(captured);
            try {
                consumer.accept(t);
            } finally {
                restore(backup);
            }
        }

        @Override
        public Consumer<T> unwrap() {
            return consumer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TtlConsumer<?> that = (TtlConsumer<?>) o;
            return consumer.equals(that.consumer);
        }

        @Override
        public int hashCode() {
            return consumer.hashCode();
        }

        @Override
        public String toString() {
            return this.getClass().getName() + " - " + consumer.toString();
        }
    }

    public static <T, U> BiConsumer<T, U> wrap(BiConsumer<T, U> consumer) {
        if (consumer == null) return null;
        else if (consumer instanceof TtlEnhanced) return consumer;
        else return new TtlBiConsumer<>(consumer);
    }

    private static class TtlBiConsumer<T, U> implements BiConsumer<T, U>, TtlWrapper<BiConsumer<T, U>>, TtlEnhanced {
        final BiConsumer<T, U> consumer;
        final Object captured;

        TtlBiConsumer(BiConsumer<T, U> consumer) {
            this.consumer = consumer;
            this.captured = capture();
        }

        @Override
        public void accept(T t, U u) {
            final Object backup = replay(captured);
            try {
                consumer.accept(t, u);
            } finally {
                restore(backup);
            }
        }

        @Override
        public BiConsumer<T, U> unwrap() {
            return consumer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TtlBiConsumer<?, ?> that = (TtlBiConsumer<?, ?>) o;
            return consumer.equals(that.consumer);
        }

        @Override
        public int hashCode() {
            return consumer.hashCode();
        }

        @Override
        public String toString() {
            return this.getClass().getName() + " - " + consumer.toString();
        }
    }

    public static <T, R> Function<T, R> wrap(Function<T, R> fn) {
        if (fn == null) return null;
        else if (fn instanceof TtlEnhanced) return fn;
        else return new TtlFunction<>(fn);
    }

    private static class TtlFunction<T, R> implements Function<T, R>, TtlWrapper<Function<T, R>>, TtlEnhanced {
        final Function<T, R> fn;
        final Object captured;

        TtlFunction(Function<T, R> fn) {
            this.fn = fn;
            this.captured = capture();
        }

        @Override
        public R apply(T t) {
            final Object backup = replay(captured);
            try {
                return fn.apply(t);
            } finally {
                restore(backup);
            }
        }

        @Override
        public Function<T, R> unwrap() {
            return fn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TtlFunction<?, ?> that = (TtlFunction<?, ?>) o;
            return fn.equals(that.fn);
        }

        @Override
        public int hashCode() {
            return fn.hashCode();
        }

        @Override
        public String toString() {
            return this.getClass().getName() + " - " + fn.toString();
        }
    }

    public static <T, U, R> BiFunction<T, U, R> wrap(BiFunction<T, U, R> fn) {
        if (fn == null) return null;
        else if (fn instanceof TtlEnhanced) return fn;
        else return new TtlBiFunction<>(fn);
    }

    private static class TtlBiFunction<T, U, R> implements BiFunction<T, U, R>, TtlWrapper<BiFunction<T, U, R>>, TtlEnhanced {
        final BiFunction<T, U, R> fn;
        final Object captured;

        TtlBiFunction(BiFunction<T, U, R> fn) {
            this.fn = fn;
            this.captured = capture();
        }

        @Override
        public R apply(T t, U u) {
            final Object backup = replay(captured);
            try {
                return fn.apply(t, u);
            } finally {
                restore(backup);
            }
        }

        @Override
        public BiFunction<T, U, R> unwrap() {
            return fn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TtlBiFunction<?, ?, ?> that = (TtlBiFunction<?, ?, ?>) o;
            return fn.equals(that.fn);
        }

        @Override
        public int hashCode() {
            return fn.hashCode();
        }

        @Override
        public String toString() {
            return this.getClass().getName() + " - " + fn.toString();
        }
    }

    private TtlWrappers() {
        throw new InstantiationError("Must not instantiate this class");
    }
}
