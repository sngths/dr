package com.tianxing.dr.framework.trace.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

/**
 *  java agent(探针)
 * @author tianxing
 */
public class TraceAgent {


    /**
     * 修改默认线程池，处理tracer的跨线程传递
     * */
    public static void agentmain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

                return null;
            }
        });
        //重新加载Class 触发字节码转换
        try {
            inst.retransformClasses(String.class);
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }

}
