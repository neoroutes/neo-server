package project.neoroutes.server.service.connection;

public interface InteractableSession {
    void getUserId();
    void addListener(Listener listener);
    void removeListener(Listener listener);
    void sendMessage(String message);

    interface Listener {
        void onMessage(InteractableSession interactableSession, String message);
        void onClose(InteractableSession interactableSession);
    }
}
