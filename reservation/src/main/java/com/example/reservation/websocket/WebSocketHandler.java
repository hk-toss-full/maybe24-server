package com.example.reservation.websocket;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final WebSocketService webSocketService;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = getUserIdFromSession(session);
        webSocketService.addSession(userId, session);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = getUserIdFromSession(session);
        webSocketService.removeSession(userId);
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 필요시 메시지 처리 로직 추가
    }
    private String getUserIdFromSession(WebSocketSession session) {
        // 세션에서 userId 가져오는 로직 구현
        return (String) session.getAttributes().get("userId");
    }
}