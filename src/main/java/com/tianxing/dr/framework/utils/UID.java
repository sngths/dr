package com.tianxing.dr.framework.utils;

import java.util.UUID;

/**
 * @author tianxing
 */
public class UID {
    private static final Base62 base62 = Base62.createInstance();

    private static final SnowFlake SNOW_FLAKE = new SnowFlake(0L,0L);

    static {
        //主机名称 进程标识符 作为数据中心ID和机器ID
        //String hostName = InetAddress.getLocalHost().getHostName();
        //String pid = ManagementFactory.getRuntimeMXBean().getName();
    }

    public static String longBase62(){
        return base62.encode(UUID.randomUUID().getMostSignificantBits());
    }

    /**
     * 本地全局ID 暂不支持分布式场景
     * */
    public static long localUid(){
        return SNOW_FLAKE.nextId();
    }
}
