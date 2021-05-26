package com.tianxing.dr.framework.util;

/**
 * 用于消除unchecked警告
 * @author tianxing
 */
public class CastUtils {

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

}
