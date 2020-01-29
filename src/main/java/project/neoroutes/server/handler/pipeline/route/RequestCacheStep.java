package project.neoroutes.server.handler.pipeline.route;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.RouteRequest;
import project.neoroutes.server.repository.RouteRequestRepository;

import java.util.Date;

public class RequestCacheStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final RouteRequestRepository routeRequestRepository;

    public RequestCacheStep(RouteRequestRepository routeRequestRepository) {
        this.routeRequestRepository = routeRequestRepository;
    }

    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        routeRequestRepository.addRequest(RouteRequest.builder()
                .date(new Date())
                .userId(routeRequestInput.getFindDto().getRequest().getData().getRequester().getUserId())
                .requestId(routeRequestInput.getFindDto().getRequest().getData().getRequestId())
                .build());
        return true;
    }
}
