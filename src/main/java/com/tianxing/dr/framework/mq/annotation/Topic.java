package com.tianxing.dr.framework.mq.annotation;

import java.lang.annotation.*;

/**
 * windQ主题
 * @author tianxing
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Topic {
    String value();
}
