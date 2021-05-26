package com.tianxing.dr.framework.trace.logging.ttl.threadpool;

import com.tianxing.dr.framework.trace.logging.ttl.TtlRunnable;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlEnhanced;
import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlWrapper;

import java.util.concurrent.Executor;

class ExecutorTtlWrapper implements Executor, TtlWrapper<Executor>, TtlEnhanced {
    private final Executor executor;

    ExecutorTtlWrapper(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(TtlRunnable.get(command));
    }

    @Override
    public Executor unwrap() {
        return executor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutorTtlWrapper that = (ExecutorTtlWrapper) o;
        return executor.equals(that.executor);
    }

    @Override
    public int hashCode() {
        return executor.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " - " + executor.toString();
    }
}
