package com.tianxing.dr.framework.trace.runtime.context;


import com.tianxing.dr.framework.util.Function;

import java.util.Optional;

/**
 * @author tianxing
 */
public interface Context {

    /**
     * 获取类型
     * */
    Type getType();
    /**
     * 存入数据
     * */
    void put(String name, Object o);

    /**
     * 移除数据
     * */
    void remove(String name);

    /**
     * 获取指定类型暂存数据
     */
    <T> Optional<T> get(String name, Class<T> type);

    /**
     * 当前值为空则从function接口中获取
     * */
    <T> T get(String name, Class<T> type, Function<String,T> function);

}
