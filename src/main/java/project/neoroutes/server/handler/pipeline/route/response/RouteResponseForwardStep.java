package project.neoroutes.server.handler.pipeline.route.response;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.protocol.UserInfo;
import project.neoroutes.server.domain.model.protocol.route.FoundDto;
import project.neoroutes.server.service.ResponseSender;
import project.neoroutes.server.service.connection.ConnectionManager;
import project.neoroutes.server.service.connection.InteractableSession;
import project.neoroutes.server.util.RouteUtil;

public class RouteResponseForwardStep implements Pipeline.Step<FoundDto, Void> {
    private final UserInfo userInfo;
    private final ConnectionManager connectionManager;
    private final ResponseSender responseSender;

    public RouteResponseForwardStep(UserInfo userInfo, ConnectionManager connectionManager, ResponseSender responseSender) {
        this.userInfo = userInfo;
        this.connectionManager = connectionManager;
        this.responseSender = responseSender;
    }


    @Override
    public boolean process(FoundDto foundDto, Void o) {
        UserInfo previousOrLastRoute = RouteUtil.getPreviousOrLastRoute(foundDto.getRoute(), userInfo);
        if(previousOrLastRoute == null)
            return true;
        if(previousOrLastRoute.equals(userInfo)){
            String userId = foundDto.getRequest().getData().getRequester().getUserId();
            InteractableSession connection = connectionManager.getConnection(userId);
            if(connection != null)
                responseSender.sendFound(userId, foundDto);
        }else {
            responseSender.sendFound(previousOrLastRoute.getUserId(), foundDto);
        }
        return true;
    }
}
