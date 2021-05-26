package com.tianxing.dr.framework.trace.logging;


import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author tianxing
 */
public class WindQAppender implements Appender {
    Logger logger = LoggerFactory.getLogger("aa");

    @Override
    public void addFilter(Filter filter) {

    }

    @Override
    public void clearAllFilters() {

    }

    @Override
    public List<Filter> getCopyOfAttachedFiltersList() {
        return null;
    }

    @Override
    public FilterReply getFilterChainDecision(Object event) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void doAppend(Object event) throws LogbackException {
        // 此处自定义实现输出
//        String level = event.getLevel().toString();
//        String loggerName = event.getLoggerName();
        //String msg = loggingEvent.getMessage().getFormattedMessage();
        //String threadName = loggingEvent.getThreadName();
        //Throwable throwable = loggingEvent.getThrown();
        // todo 这里实现自定义的日志处理逻辑
    }

    @Override
    public void setName(String name) {

    }


    @Override
    public void setContext(Context context) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void addStatus(Status status) {

    }

    @Override
    public void addInfo(String msg) {

    }

    @Override
    public void addInfo(String msg, Throwable ex) {

    }

    @Override
    public void addWarn(String msg) {

    }

    @Override
    public void addWarn(String msg, Throwable ex) {

    }

    @Override
    public void addError(String msg) {

    }

    @Override
    public void addError(String msg, Throwable ex) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
