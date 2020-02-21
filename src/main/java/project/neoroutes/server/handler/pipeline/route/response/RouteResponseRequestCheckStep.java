package project.neoroutes.server.handler.pipeline.route.response;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.protocol.route.FoundDto;
import project.neoroutes.server.repository.RouteRequestRepository;

public class RouteResponseRequestCheckStep implements Pipeline.Step<FoundDto, Void> {
    private final RouteRequestRepository routeRequestRepository;

    public RouteResponseRequestCheckStep(RouteRequestRepository routeRequestRepository) {
        this.routeRequestRepository = routeRequestRepository;
    }

    @Override
    public boolean process(FoundDto foundDto, Void o) {
        return routeRequestRepository.contains(foundDto.getRequest().getData().getRequestId());
    }
}
