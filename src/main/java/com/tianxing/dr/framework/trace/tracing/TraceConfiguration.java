package com.tianxing.dr.framework.trace.tracing;

import io.opentracing.Tracer;
import io.opentracing.noop.NoopTracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianxing
 */
@Configuration
public class TraceConfiguration {

    private static final boolean enable = true;

    /**
     * 根据配置判断是否需要返回 NoopTracer
     * */
    @Bean
    public Tracer tracer(){
        if (enable){
            return new DrTracer();
        }
        return NoopTracerFactory.create();
    }
}
