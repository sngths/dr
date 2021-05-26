package com.tianxing.dr.framework.mq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author tianxing
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WindQ {
    /**
     * 消息队列主题
     */
    String value();
}
