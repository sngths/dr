package com.tianxing.dr.framework.utils;

/**
 * 0 - 41位时间戳 - 5位数据中心标识 - 5位机器标识 - 12位序列号
 * @author tianxing
 */
public class SnowFlake {
    //起始的时间戳 1592528400000L (2020-06-19 09:00:00)
    private final static long START_TIMESTAMP = 1592528400000L;
    //序列号占用的位数
    private final static long SEQUENCE_BIT = 12;
    //机器标识占用的位数
    private final static long MACHINE_BIT = 5;
    //数据中心标识占用的位数
    private final static long DATA_CENTER_BIT = 5;
    //数据中心标识 最大值31
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    //机器标识 最大值31
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    //序列号 最大值4095
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    //每一部分向左的位移
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;
    //数据中心表示
    private long dataCenterId = 0;
    //机器标识
    private long machineId = 0;
    //序列号
    private long sequence = 0L;
    //上一次时间戳
    private long lastTimestamp = -1L;


    /**
     * @param dataCenterId 5位数据中心表示 （0~31）
     * @param machineId 5位机器表示 （0~31）
     * */
    public SnowFlake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     * @return 下一个ID
     */
    public synchronized long nextId() {
        long currentTimestamp = getTimestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if (currentTimestamp == lastTimestamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currentTimestamp = getNextMillisecond();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        lastTimestamp = currentTimestamp;
        return (currentTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT
                | dataCenterId << DATA_CENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    /**
     * 取下一毫秒
     * */
    private long getNextMillisecond() {
        long millisecond = getTimestamp();
        while (millisecond <= lastTimestamp) {
            millisecond = getTimestamp();
        }
        return millisecond;
    }

    private long getTimestamp() {
        return System.currentTimeMillis();
    }
}
