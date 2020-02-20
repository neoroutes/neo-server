package project.neoroutes.server.handler.pipeline.route;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.messaging.UserInfo;

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
