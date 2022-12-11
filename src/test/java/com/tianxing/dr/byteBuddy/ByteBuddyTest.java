package com.tianxing.dr.byteBuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.InvokeDynamic;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.matcher.ElementMatchers;
import org.assertj.core.internal.bytebuddy.agent.builder.AgentBuilder;
import org.assertj.core.internal.bytebuddy.description.type.TypeDescription;
import org.assertj.core.internal.bytebuddy.utility.JavaModule;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author tianxing
 */
public class ByteBuddyTest {



    @Test
    public void dynamicType() throws InstantiationException, IllegalAccessException {
        //创建代理类
        DynamicType.Unloaded<Object> unloadedType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello World!"))
                .make();
        //加载代理类
        Class<?> dynamicType = unloadedType.load(getClass()
                .getClassLoader())
                .getLoaded();

        assert dynamicType.newInstance().toString().equals("Hello World!");
    }


    @Test
    public void redefine() throws InstantiationException, IllegalAccessException {
        ByteBuddyAgent.install();
        //修改方法
        Object f = new Object();
        new ByteBuddy().redefine(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello"))
                .make()
                .load(Object.class.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(f);

        //重新加载类
        Agent agent = new AgentImpl();
        new ByteBuddy().redefine(AgentImpl2.class)
                .name(AgentImpl.class.getName())
                .make()
                .load(AgentImpl.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(agent.hello(""));

    }


    /**
     * 创建代理类
     * */
    @Test
    public void agent(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,30, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        executor.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
    }



}
