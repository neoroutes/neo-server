package project.neoroutes.server.handler.pipeline.route;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.messaging.UserInfo;
import project.neoroutes.server.repository.RouteCacheRepository;

import java.util.List;

public class RequestRouteCacheCheckStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final RouteCacheRepository routeCacheRepository;

    public RequestRouteCacheCheckStep(RouteCacheRepository routeCacheRepository) {
        this.routeCacheRepository = routeCacheRepository;
    }

    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        if(routeRequestInput.getFindDto().getRequest().getData().isSkipCache())
            return true;
        List<UserInfo> cachedRoute = routeCacheRepository.getCachedRoute(routeRequestInput.getFindDto().getRequest().getData().getSearch().getUserId());
        if(cachedRoute != null){

            return false;
        }
        return true;
    }
}
