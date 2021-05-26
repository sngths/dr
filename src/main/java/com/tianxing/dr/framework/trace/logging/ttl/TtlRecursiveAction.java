package com.tianxing.dr.framework.trace.logging.ttl;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlEnhanced;

import java.util.concurrent.ForkJoinTask;

import static com.tianxing.dr.framework.trace.logging.ttl.TransmittableThreadLocal.Transmitter.*;

public abstract class TtlRecursiveAction extends ForkJoinTask<Void> implements TtlEnhanced {

    private static final long serialVersionUID = -5753568484583412377L;

    private final Object captured = capture();

    protected TtlRecursiveAction() {
    }

    protected abstract void compute();

    public final Void getRawResult() {
        return null;
    }

    protected final void setRawResult(Void mustBeNull) {
    }

    protected final boolean exec() {
        final Object backup = replay(captured);
        try {
            compute();
            return true;
        } finally {
            restore(backup);
        }
    }
}
