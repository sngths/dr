package com.tianxing.dr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @author tianxing
 */
@Component
@ServerEndpoint("/my-websocket-endpoint")
public class WebsocketEndpoint {

    private static final Logger log = LoggerFactory.getLogger(WebsocketEndpoint.class);
    @OnOpen
    public void onOpen(Session session) {
        // 处理连接打开事件
        log.info("onOpen");
    }

    @OnClose
    public void onClose(Session session) {
        // 处理连接关闭事件
        log.info("onClose");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // 处理连接错误事件
        log.info("onError");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // 处理文本消息
        log.info("收到消息: {}", message);
        // 发送消息
        session.getAsyncRemote().sendText(message);
    }
}
