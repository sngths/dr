package com.tianxing.dr.framework.trace.logging.ttl.threadpool;

import java.util.concurrent.ThreadFactory;

import static com.tianxing.dr.framework.trace.logging.ttl.TransmittableThreadLocal.Transmitter.clear;
import static com.tianxing.dr.framework.trace.logging.ttl.TransmittableThreadLocal.Transmitter.restore;


class DisableInheritableThreadFactoryWrapper implements DisableInheritableThreadFactory {
    private final ThreadFactory threadFactory;

    DisableInheritableThreadFactoryWrapper(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    @Override
    public Thread newThread(Runnable r) {
        final Object backup = clear();
        try {
            return threadFactory.newThread(r);
        } finally {
            restore(backup);
        }
    }

    @Override
    public ThreadFactory unwrap() {
        return threadFactory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisableInheritableThreadFactoryWrapper that = (DisableInheritableThreadFactoryWrapper) o;
        return threadFactory.equals(that.threadFactory);
    }

    @Override
    public int hashCode() {
        return threadFactory.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " - " + threadFactory.toString();
    }
}
