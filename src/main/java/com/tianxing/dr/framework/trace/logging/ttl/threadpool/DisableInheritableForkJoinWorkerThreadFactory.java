package com.tianxing.dr.framework.trace.logging.ttl.threadpool;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlWrapper;

import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;


public interface DisableInheritableForkJoinWorkerThreadFactory extends ForkJoinWorkerThreadFactory, TtlWrapper<ForkJoinWorkerThreadFactory> {

    @Override
    ForkJoinWorkerThreadFactory unwrap();
}
