package project.neoroutes.server.handler.pipeline.route.request;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.protocol.UserInfo;
import project.neoroutes.server.domain.model.protocol.register.RegisterDto;
import project.neoroutes.server.handler.factory.FoundMessageFactory;
import project.neoroutes.server.repository.RouteRequestRepository;
import project.neoroutes.server.service.ResponseSender;
import project.neoroutes.server.service.connection.ConnectionManager;
import project.neoroutes.server.util.RouteUtil;

public class RouteReqCheckStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final ConnectionManager connectionManager;
    private final ResponseSender responseSender;
    private final RouteRequestRepository routeRequestRepository;
    private final UserInfo userInfo;

    public RouteReqCheckStep(ConnectionManager connectionManager, ResponseSender responseSender, RouteRequestRepository routeRequestRepository, UserInfo userInfo) {
        this.connectionManager = connectionManager;
        this.responseSender = responseSender;
        this.routeRequestRepository = routeRequestRepository;
        this.userInfo = userInfo;
    }

    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        UserInfo search = routeRequestInput.getFindDto().getRequest().getData().getSearch();
        RegisterDto connectionProof = connectionManager.getConnectionProof(search.getUserId());

        if(connectionProof != null){
            UserInfo userInfo = RouteUtil.getPreviousOrLastRoute(routeRequestInput.getFindDto().getPassedRoute(), this.userInfo);
            if(userInfo != null)
                responseSender.sendFound(userInfo.getUserId(), FoundMessageFactory.found(routeRequestInput.getFindDto(), connectionProof));
            return false;
        }

        routeRequestRepository.addRequest(routeRequestInput.getFindDto().getRequest().getData().getRequestId());

        return true;
    }
}
