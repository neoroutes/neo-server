package project.neoroutes.server.service.connection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractInteractableSession implements InteractableSession {
    private final List<InteractableSession.Listener> listeners;

    public AbstractInteractableSession() {
        listeners = new CopyOnWriteArrayList<>();
    }

    protected void publishClose(){
        listeners.forEach(listener -> {
            listener.onClose(this);
        });
    }

    protected void publishMessage(String message){
        listeners.forEach(listener -> {
            listener.onMessage(this, message);
        });
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

}
