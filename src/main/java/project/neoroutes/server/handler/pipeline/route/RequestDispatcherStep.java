package project.neoroutes.server.handler.pipeline.route;

import project.neoroutes.Pipeline;

public class RequestDispatcherStep implements Pipeline.Step<RouteRequestInput, Void> {

    //todo: send request to other connected clients
    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        return false;
    }
}
