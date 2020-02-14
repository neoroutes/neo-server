package project.neoroutes.server.service.connection;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebsocketInteractableSession extends AbstractInteractableSession {
    private volatile WebSocketSession webSocketSession;
    private final String userId;
    private final WebsocketListener websocketListener;

    public WebsocketInteractableSession(WebSocketSession webSocketSession, String userId) {
        this.webSocketSession = webSocketSession;
        this.userId = userId;
        websocketListener = makeWebsocketListener();
    }

    private WebsocketListener makeWebsocketListener() {
        return new WebsocketListener() {
            @Override
            public void onClose() {
                publishClose();
            }

            @Override
            public void onMessage(String message) {
                publishMessage(message);
            }
        };
    }

    public WebsocketListener getWebsocketListener(){
        return websocketListener;
    }

    @Override
    public String getUserId() {
        return this.userId;
    }

    @Override
    public void sendMessage(String message) throws Exception {
        this.webSocketSession.sendMessage(new TextMessage(message));
    }

    public interface WebsocketListener {
        void onClose();
        void onMessage(String message);
    }
}
