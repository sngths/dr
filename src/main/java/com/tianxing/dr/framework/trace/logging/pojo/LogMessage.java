package com.tianxing.dr.framework.trace.logging.pojo;


import com.tianxing.dr.framework.mq.annotation.Topic;

/**
 * 日志信息
 * @author tianxing
 */
@Topic("log")
public class LogMessage {
    private Long traceId;
    private Long spanId;
    private Long timestamp;




    private Long offset;//用于排序

}
