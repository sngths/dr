package com.tianxing.dr.framework.trace.tracing;

/**
 * @author tianxing
 */
public class Span {

    public SpanContext getContext() {
        return null;
    }


    public void setTag(String key,String value){

    }


    public void log(String key, Object value){

    }

    /**
     * log和baggage都是用于记录业务相关数据  baggage在全局范围内跨进程传输 log不传输
     * */
    public String getBaggageItem(){
        return "";
    }

    public void setBaggageItem(){

    }


    public void finish(){
    }
}
