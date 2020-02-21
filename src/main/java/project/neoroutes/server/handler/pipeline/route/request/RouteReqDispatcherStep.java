package project.neoroutes.server.handler.pipeline.route.request;

import lombok.SneakyThrows;
import project.neoroutes.Pipeline;
import project.neoroutes.server.service.ResponseSender;

public class RouteReqDispatcherStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final int maximumRoutesToPass;
    private final ResponseSender responseSender;;

    public RouteReqDispatcherStep(int maximumRoutesToPass, ResponseSender responseSender) {
        this.maximumRoutesToPass = maximumRoutesToPass;
        this.responseSender = responseSender;
    }

    @SneakyThrows
    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        if (routeRequestInput.getFindDto().getPassedRoute().size() < maximumRoutesToPass) {
            responseSender.dispatchFindRequest(routeRequestInput.getFindDto());
        }
        return true;
    }
}
