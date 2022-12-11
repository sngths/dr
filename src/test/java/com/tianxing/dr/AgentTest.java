package com.tianxing.dr;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author tianxing
 */
public class AgentTest {


    public void test(){
        ByteBuddyAgent.install();
        ByteBuddyAgent.getInstrumentation().addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {


                return new byte[0];
            }
        });
    }
}
