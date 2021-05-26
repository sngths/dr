package com.tianxing.dr.framework.trace.logging.ttl.threadpool;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlWrapper;

import java.util.concurrent.ThreadFactory;

public interface DisableInheritableThreadFactory extends ThreadFactory, TtlWrapper<ThreadFactory> {

    @Override
    ThreadFactory unwrap();
}
