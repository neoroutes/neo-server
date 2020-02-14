package project.neoroutes.server.handler.pipeline.register;

import lombok.extern.log4j.Log4j2;
import project.neoroutes.Pipeline;
import project.neoroutes.server.service.connection.ConnectionManager;

@Log4j2
public class ConnectionManagerStep implements Pipeline.Step<RegisterInput, Void> {
    private final ConnectionManager connectionManager;

    public ConnectionManagerStep(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean process(RegisterInput registerInput, Void o) {
        connectionManager.addConnection(registerInput.getRegisterDto(), registerInput.getInteractableSession());
        return true;
    }
}
