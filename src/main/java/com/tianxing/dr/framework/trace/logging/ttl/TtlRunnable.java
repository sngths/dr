package com.tianxing.dr.framework.trace.logging.ttl;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlAttachments;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlAttachmentsDelegate;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlEnhanced;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.tianxing.dr.framework.trace.logging.ttl.TransmittableThreadLocal.Transmitter.*;

public final class TtlRunnable implements Runnable, TtlWrapper<Runnable>, TtlAttachments, TtlEnhanced {
    private final AtomicReference<Object> capturedRef;
    private final Runnable runnable;
    private final boolean releaseTtlValueReferenceAfterRun;

    private TtlRunnable(Runnable runnable, boolean releaseTtlValueReferenceAfterRun) {
        this.capturedRef = new AtomicReference<>(capture());
        this.runnable = runnable;
        this.releaseTtlValueReferenceAfterRun = releaseTtlValueReferenceAfterRun;
    }

    @Override
    public void run() {
        final Object captured = capturedRef.get();
        if (captured == null || releaseTtlValueReferenceAfterRun && !capturedRef.compareAndSet(captured, null)) {
            throw new IllegalStateException("TTL value reference is released after run!");
        }

        final Object backup = replay(captured);
        try {
            runnable.run();
        } finally {
            restore(backup);
        }
    }

    public Runnable getRunnable() {
        return unwrap();
    }

    @Override
    public Runnable unwrap() {
        return runnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TtlRunnable that = (TtlRunnable) o;
        return runnable.equals(that.runnable);
    }

    @Override
    public int hashCode() {
        return runnable.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " - " + runnable.toString();
    }

    public static TtlRunnable get(Runnable runnable) {
        return get(runnable, false, false);
    }

    public static TtlRunnable get(Runnable runnable, boolean releaseTtlValueReferenceAfterRun) {
        return get(runnable, releaseTtlValueReferenceAfterRun, false);
    }

    public static TtlRunnable get(Runnable runnable, boolean releaseTtlValueReferenceAfterRun, boolean idempotent) {
        if (null == runnable) return null;

        if (runnable instanceof TtlEnhanced) {
            // avoid redundant decoration, and ensure idempotency
            if (idempotent) return (TtlRunnable) runnable;
            else throw new IllegalStateException("Already TtlRunnable!");
        }
        return new TtlRunnable(runnable, releaseTtlValueReferenceAfterRun);
    }

    public static List<TtlRunnable> gets(Collection<? extends Runnable> tasks) {
        return gets(tasks, false, false);
    }

    public static List<TtlRunnable> gets(Collection<? extends Runnable> tasks, boolean releaseTtlValueReferenceAfterRun) {
        return gets(tasks, releaseTtlValueReferenceAfterRun, false);
    }

    public static List<TtlRunnable> gets(Collection<? extends Runnable> tasks, boolean releaseTtlValueReferenceAfterRun, boolean idempotent) {
        if (null == tasks) return Collections.emptyList();
        List<TtlRunnable> copy = new ArrayList<>();
        for (Runnable task : tasks) {
            copy.add(TtlRunnable.get(task, releaseTtlValueReferenceAfterRun, idempotent));
        }
        return copy;
    }

    public static Runnable unwrap(Runnable runnable) {
        if (!(runnable instanceof TtlRunnable)) return runnable;
        else return ((TtlRunnable) runnable).getRunnable();
    }

    public static List<Runnable> unwraps(Collection<? extends Runnable> tasks) {
        if (null == tasks) return Collections.emptyList();

        List<Runnable> copy = new ArrayList<>();
        for (Runnable task : tasks) {
            if (!(task instanceof TtlRunnable)) copy.add(task);
            else copy.add(((TtlRunnable) task).getRunnable());
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
