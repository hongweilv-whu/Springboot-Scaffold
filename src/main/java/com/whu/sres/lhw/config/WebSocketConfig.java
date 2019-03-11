package com.whu.sres.lhw.config;

import com.whu.sres.lhw.websocket.WebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Description:
 * Created by lvhw on 2019/3/10 22:15.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Bean
    public WebSocketHandler webSocketServer() {
        return new WebSocketServer();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketServer(), "/webSocketServer/*");
    }
}
