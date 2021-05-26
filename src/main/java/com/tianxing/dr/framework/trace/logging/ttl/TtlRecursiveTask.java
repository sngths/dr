package com.tianxing.dr.framework.trace.logging.ttl;

import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlEnhanced;

import java.util.concurrent.ForkJoinTask;

import static com.tianxing.dr.framework.trace.logging.ttl.TransmittableThreadLocal.Transmitter.*;


public abstract class TtlRecursiveTask<V> extends ForkJoinTask<V> implements TtlEnhanced {

    private static final long serialVersionUID = 1814679366926362436L;

    private final Object captured = capture();

    protected TtlRecursiveTask() {
    }

    V result;

    protected abstract V compute();

    public final V getRawResult() {
        return result;
    }

    protected final void setRawResult(V value) {
        result = value;
    }

    protected final boolean exec() {
        final Object backup = replay(captured);
        try {
            result = compute();
            return true;
        } finally {
            restore(backup);
        }
    }

}
