package com.example.files_microservice.interceptors;

import com.example.files_microservice.config.websocket.WsClientObserver;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Component
public class WsHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    private final WsClientObserver wsClientObserver;

    public WsHandshakeInterceptor(WsClientObserver wsClientObserver) {
        this.wsClientObserver = wsClientObserver;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
        System.out.println(wsClientObserver.toString());
    }
}
