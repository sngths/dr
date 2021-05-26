package com.tianxing.dr.framework.trace.runtime;

import com.tianxing.dr.framework.trace.runtime.context.Context;
import com.tianxing.dr.framework.trace.runtime.context.RuntimeContext;
import com.tianxing.dr.framework.trace.runtime.context.Type;

/**
 * @author tianxing
 */
public class Runtime {

    /**
     * 后续使用自有实现替换 完成线程切换的消息传递
     * */
    private static final ThreadLocal<Context> LOCAL = new ThreadLocal<>();

    public static Context initialize(Type type){
        LOCAL.set(new RuntimeContext(type));
        return LOCAL.get();
    }


    public static Context getContext(){
        Context context = LOCAL.get();
        if (context == null){
            return initialize(Type.unknown);
        }
        return context;
    }

    public static void clear(){
        LOCAL.remove();
    }







    /**
     * 参数名称
     * */
    public static class param{
        public static final String errorMessage = "errorMessage";


        public static final String env = "environment";


    }

    /**
     * 当前运行环境
     * */
    public static class environment{
        public static final String dev = "development";
        public static final String test = "test";
        public static final String prd = "production";
    }

}
