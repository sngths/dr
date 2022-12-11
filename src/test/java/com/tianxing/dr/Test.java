package com.tianxing.dr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tianxing
 */
public class Test {
    Logger logger = LoggerFactory.getLogger(Test.class);

//    public static void main(String[] args) throws JsonProcessingException, ClassNotFoundException {
//        ObjectMapper mapper = new ObjectMapper();
//        Method[] methods = String.class.getMethods();
//        Class<?> r = methods[25].getReturnType();
//        Class<?>[] classes = methods[9].getParameterTypes();
//        System.out.println(mapper.writeValueAsString(r));
//        Object o = mapper.readValue(mapper.writeValueAsString(methods[0]),Object.class);
//        System.out.println(o);
//    }

//    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        Hello hello = new ByteBuddy().subclass(Hello.class)
//                .method(ElementMatchers.any())
//                .intercept(MethodDelegation.to(Test.class)).defineField("filed1", String.class)
//                .make().load(ClassLoader.getSystemClassLoader())
//                .getLoaded().newInstance();
//        hello.hello("hello");
//    }
//
//
//    public String invoke(Object... objects){
//        logger.info((String) objects[0]);
//        return "";
//    }


    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Bean bean = new Bean();
        String s = mapper.writeValueAsString("bean");
        Object o = mapper.readValue(s,Object.class);




        ProxyHandler handler = new ProxyHandler();

        TestInterface testInterface =  (TestInterface) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{TestInterface.class},
                handler);
        testInterface.hello("");
    }

    public static class ProxyHandler implements InvocationHandler {

        public ProxyHandler(){

        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("hello");
            return null;
        }
    }

    public static interface TestInterface{
        String hello(String s);
    }
}
