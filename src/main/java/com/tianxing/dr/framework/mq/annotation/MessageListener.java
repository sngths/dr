package com.tianxing.dr.framework.mq.annotation;

import java.lang.annotation.*;

/**
 * @author tianxing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageListener {

}
