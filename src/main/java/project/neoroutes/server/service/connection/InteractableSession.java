package project.neoroutes.server.service.connection;

public interface InteractableSession {
    String getUserId();
    void addListener(Listener listener);
    void removeListener(Listener listener);
    void sendMessage(String message) throws Exception;

    interface Listener {
        void onMessage(InteractableSession interactableSession, String message);
        void onClose(InteractableSession interactableSession);
    }
}
