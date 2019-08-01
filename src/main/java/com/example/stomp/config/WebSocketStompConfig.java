package com.example.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.sockjs.SockJsService;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年07月31日 17:13:00
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    public static final String Default_WebSocket_EndPoint ="/webSocketServer";
    public static final String Default_WebSocket_Broker ="/topic";
    public static final String Topic_WebSocket_Broker ="/queue";


    /**
     * 客户端设定
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 全局使用的消息前缀（客户端订阅路径上会体现出来）
            registry.setApplicationDestinationPrefixes("/demo");
            registry.enableSimpleBroker(Default_WebSocket_Broker,Topic_WebSocket_Broker);

    }

    /**
     * 服务端设定
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint(Default_WebSocket_EndPoint)
                    .setAllowedOrigins("*")
//                    .setHandshakeHandler(new WebSocketHandShakeInterceptor())
                    .withSockJS();
    }
}
