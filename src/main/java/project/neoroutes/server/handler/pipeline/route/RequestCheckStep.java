package project.neoroutes.server.handler.pipeline.route;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.route.UserInfo;
import project.neoroutes.server.domain.model.route.register.RegisterDto;
import project.neoroutes.server.service.connection.ConnectionManager;

public class RequestCheckStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final ConnectionManager connectionManager;

    public RequestCheckStep(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        UserInfo search = routeRequestInput.getFindDto().getRequest().getData().getSearch();
        RegisterDto connectionProof = connectionManager.getConnectionProof(search.getUserId());

        if(connectionProof != null){
            //todo: found
            return false;
        }

        return true;
    }
}
