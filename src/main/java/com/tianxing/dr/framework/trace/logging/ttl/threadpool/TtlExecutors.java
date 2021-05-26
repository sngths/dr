package com.tianxing.dr.framework.trace.logging.ttl.threadpool;


import com.tianxing.dr.framework.trace.logging.ttl.spi.TtlEnhanced;

import java.util.concurrent.*;

public final class TtlExecutors {

    public static Executor getTtlExecutor(Executor executor) {
        if (null == executor || executor instanceof TtlEnhanced) {
            return executor;
        }
        return new ExecutorTtlWrapper(executor);
    }

    public static ExecutorService getTtlExecutorService(ExecutorService executorService) {
        if (executorService == null || executorService instanceof TtlEnhanced) {
            return executorService;
        }
        return new ExecutorServiceTtlWrapper(executorService);
    }

    public static ScheduledExecutorService getTtlScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        if (scheduledExecutorService == null || scheduledExecutorService instanceof TtlEnhanced) {
            return scheduledExecutorService;
        }
        return new ScheduledExecutorServiceTtlWrapper(scheduledExecutorService);
    }

    public static <T extends Executor> boolean isTtlWrapper(T executor) {
        return executor instanceof TtlEnhanced;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Executor> T unwrap(T executor) {
        if (!isTtlWrapper(executor)) return executor;

        return (T) ((ExecutorTtlWrapper) executor).unwrap();
    }

    public static ThreadFactory getDisableInheritableThreadFactory(ThreadFactory threadFactory) {
        if (threadFactory == null || isDisableInheritableThreadFactory(threadFactory)) return threadFactory;

        return new DisableInheritableThreadFactoryWrapper(threadFactory);
    }

    public static ThreadFactory getDefaultDisableInheritableThreadFactory() {
        return getDisableInheritableThreadFactory(Executors.defaultThreadFactory());
    }

    public static boolean isDisableInheritableThreadFactory(ThreadFactory threadFactory) {
        return threadFactory instanceof DisableInheritableThreadFactory;
    }

    public static ThreadFactory unwrap(ThreadFactory threadFactory) {
        if (!isDisableInheritableThreadFactory(threadFactory)) return threadFactory;

        return ((DisableInheritableThreadFactory) threadFactory).unwrap();
    }

    private TtlExecutors() {
    }
}
