package project.neoroutes.server.websocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import project.neoroutes.server.service.connection.WebsocketInteractableSession;

import static project.neoroutes.server.config.StaticConfig.WEBSOCKET_BUFFER_SIZE_LIMIT;
import static project.neoroutes.server.config.StaticConfig.WEBSOCKET_SEND_TIME_LIMIT;

@Log4j2
public class WebsocketHandler extends TextWebSocketHandler {
    private volatile WebsocketInteractableSession websocketInteractableSession;
    private WebsocketInteractableSession.WebsocketListener websocketListener;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        this.websocketInteractableSession = new WebsocketInteractableSession(new ConcurrentWebSocketSessionDecorator(session, WEBSOCKET_SEND_TIME_LIMIT, WEBSOCKET_BUFFER_SIZE_LIMIT, ConcurrentWebSocketSessionDecorator.OverflowStrategy.TERMINATE), userId);
        this.websocketListener = websocketInteractableSession.getWebsocketListener();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        websocketListener.onMessage(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Websocket session of `"+websocketInteractableSession.getUserId()+"` has been closed");
        websocketListener.onClose();
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.catching(exception);
    }
}
