package project.neoroutes.server.handler.pipeline.route.request;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.protocol.UserInfo;

public class RouteReqExtendStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final UserInfo userInfo;

    public RouteReqExtendStep(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        routeRequestInput.getFindDto().getPassedRoute().add(userInfo);
        return true;
    }
}
