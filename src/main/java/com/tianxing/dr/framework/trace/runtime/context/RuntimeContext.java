package com.tianxing.dr.framework.trace.runtime.context;


import com.tianxing.dr.framework.util.CastUtils;
import com.tianxing.dr.framework.util.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author tianxing
 */
public class RuntimeContext implements Context{
    private final Type type;

    private final Map<String,Object> contextMap = new HashMap<>();

    public RuntimeContext(Type type){
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void put(String name, Object o) {
        contextMap.put(name, o);
    }

    @Override
    public void remove(String name) {
        contextMap.remove(name);
    }

    @Override
    public <T> Optional<T> get(String name, Class<T> type) {
        Object o = contextMap.get(name);
        if (o != null && o.getClass() == type){
            return Optional.of(CastUtils.cast(o));
        }
        return Optional.empty();
    }

    @Override
    public <T> T get(String name, Class<T> type, Function<String, T> function) {
        return get(name,type).orElseGet(new Supplier<T>() {
            @Override
            public T get() {
                T t = function.call(name);
                contextMap.put(name,t);
                return t;
            }
        });
    }

}
