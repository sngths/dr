package com.tianxing.dr.framework.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;
import io.opentracing.util.GlobalTracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tianxing
 */
public class CacheConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfiguration.class);

    public void test() {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumWeight(1024 * 1024 * 1024) //1M
                .weigher((Weigher<String, String>) (key, value) -> key.getBytes().length + value.getBytes().length)//计算字节码长度
                .removalListener(notification -> logger.warn("本地缓存被清除！ key:{},case:{}", notification.getKey(), notification.getCause().name()))
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .build();

    }

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumWeight(1024L * 1024 * 1024 * 5) //5G
                .weigher((Weigher<String, String>) (key, value) -> key.length() + value.length())//计算字节码长度
                .removalListener(notification -> logger.warn(integer.incrementAndGet() + "本地缓存被清除！ key:{},case:{}", notification.getKey(), notification.getCause().name()))
                .expireAfterAccess(300, TimeUnit.SECONDS)
                .build();
        String a = "a";
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 1024; i++) {
            buffer.append(a);
        }
        System.out.println(buffer.toString().length());
        for (int i = 0; i < 1024 * 1024; i++) {
            cache.put(String.valueOf(i), String.copyValueOf(buffer.toString().toCharArray()));
        }

//        ObjectMapper mapper = new ObjectMapper();
//        for (int i = 0; i < 10000; i++) {
//            Node node = new Node();
//            try {
//                cache.put(String.valueOf(i),mapper.writeValueAsString(node));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println("");
    }


    public static class Node {
        String a = "aaaaaaaaaa";
        String b = "aaaaaaaaaa";

        public String getA() {
            GlobalTracer.get();
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
