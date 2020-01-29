package project.neoroutes.server.handler.pipeline.route;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.RouteRequest;
import project.neoroutes.server.domain.model.route.UserInfo;

import java.util.Date;

public class RequestRouteExtendStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final UserInfo userInfo;

    public RequestRouteExtendStep(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        routeRequestInput.getFindDto().getPassedRoute().add(userInfo);
        return true;
    }
}
