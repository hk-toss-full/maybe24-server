package com.example.reservation.websocket;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class WebSocketService {
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    public void addSession(String userId, WebSocketSession session) {
        sessions.put(userId, session);
    }
    public void removeSession(String userId) {
        sessions.remove(userId);
    }
    public void sendMessage(String userId, String message) {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}