package com.tianxing.dr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author tianxing
 */
@Configuration
@EnableWebSocket
//@EnableWebSocketMessageBroker
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter webSocketEndpoint(){
        return new ServerEndpointExporter();
    }
}
