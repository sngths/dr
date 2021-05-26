package com.tianxing.dr.framework.trace.logging.ttl;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlAttachments;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlAttachmentsDelegate;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlEnhanced;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

import static com.tianxing.dr.framework.trace.logging.ttl.TransmittableThreadLocal.Transmitter.*;

public final class TtlCallable<V> implements Callable<V>, TtlWrapper<Callable<V>>, TtlAttachments, TtlEnhanced {
    private final AtomicReference<Object> capturedRef;
    private final Callable<V> callable;
    private final boolean releaseTtlValueReferenceAfterCall;

    private TtlCallable(Callable<V> callable, boolean releaseTtlValueReferenceAfterCall) {
        this.capturedRef = new AtomicReference<>(capture());
        this.callable = callable;
        this.releaseTtlValueReferenceAfterCall = releaseTtlValueReferenceAfterCall;
    }

    @Override
    public V call() throws Exception {
        final Object captured = capturedRef.get();
        if (captured == null || releaseTtlValueReferenceAfterCall && !capturedRef.compareAndSet(captured, null)) {
            throw new IllegalStateException("TTL value reference is released after call!");
        }

        final Object backup = replay(captured);
        try {
            return callable.call();
        } finally {
            restore(backup);
        }
    }

    public Callable<V> getCallable() {
        return unwrap();
    }

    @Override
    public Callable<V> unwrap() {
        return callable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TtlCallable<?> that = (TtlCallable<?>) o;
        return callable.equals(that.callable);
    }

    @Override
    public int hashCode() {
        return callable.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " - " + callable.toString();
    }

    public static <T> TtlCallable<T> get(Callable<T> callable) {
        return get(callable, false);
    }

    public static <T> TtlCallable<T> get(Callable<T> callable, boolean releaseTtlValueReferenceAfterCall) {
        return get(callable, releaseTtlValueReferenceAfterCall, false);
    }

    public static <T> TtlCallable<T> get(Callable<T> callable, boolean releaseTtlValueReferenceAfterCall, boolean idempotent) {
        if (null == callable) return null;

        if (callable instanceof TtlEnhanced) {
            if (idempotent) return (TtlCallable<T>) callable;
            else throw new IllegalStateException("Already TtlCallable!");
        }
        return new TtlCallable<T>(callable, releaseTtlValueReferenceAfterCall);
    }

    public static <T> List<TtlCallable<T>> gets(Collection<? extends Callable<T>> tasks) {
        return gets(tasks, false, false);
    }

    public static <T> List<TtlCallable<T>> gets(Collection<? extends Callable<T>> tasks, boolean releaseTtlValueReferenceAfterCall) {
        return gets(tasks, releaseTtlValueReferenceAfterCall, false);
    }

    public static <T> List<TtlCallable<T>> gets(Collection<? extends Callable<T>> tasks, boolean releaseTtlValueReferenceAfterCall, boolean idempotent) {
        if (null == tasks) return Collections.emptyList();
        List<TtlCallable<T>> copy = new ArrayList<>();
        for (Callable<T> task : tasks) {
            copy.add(TtlCallable.get(task, releaseTtlValueReferenceAfterCall, idempotent));
        }
        return copy;
    }

    public static <T> Callable<T> unwrap(Callable<T> callable) {
        if (!(callable instanceof TtlCallable)) return callable;
        else return ((TtlCallable<T>) callable).getCallable();
    }

    public static <T> List<Callable<T>> unwraps(Collection<? extends Callable<T>> tasks) {
        if (null == tasks) return Collections.emptyList();

        List<Callable<T>> copy = new ArrayList<>();
        for (Callable<T> task : tasks) {
            if (!(task instanceof TtlCallable)) copy.add(task);
            else copy.add(((TtlCallable<T>) task).getCallable());
        }
        return copy;
    }

    private final TtlAttachmentsDelegate ttlAttachment = new TtlAttachmentsDelegate();

    @Override
    public void setTtlAttachment(String key, Object value) {
        ttlAttachment.setTtlAttachment(key, value);
    }

    @Override
    public <T> T getTtlAttachment(String key) {
        return ttlAttachment.getTtlAttachment(key);
    }
}
