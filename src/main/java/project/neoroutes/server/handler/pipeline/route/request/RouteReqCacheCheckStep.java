package project.neoroutes.server.handler.pipeline.route.request;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.protocol.UserInfo;
import project.neoroutes.server.domain.model.protocol.route.FoundDto;
import project.neoroutes.server.repository.RouteCacheRepository;
import project.neoroutes.server.service.ResponseSender;
import project.neoroutes.server.util.RouteUtil;

public class RouteReqCacheCheckStep implements Pipeline.Step<RouteRequestInput, Void> {
    private final RouteCacheRepository routeCacheRepository;
    private final ResponseSender responseSender;
    private final UserInfo userInfo;

    public RouteReqCacheCheckStep(RouteCacheRepository routeCacheRepository, ResponseSender responseSender, UserInfo userInfo) {
        this.routeCacheRepository = routeCacheRepository;
        this.responseSender = responseSender;
        this.userInfo = userInfo;
    }

    @Override
    public boolean process(RouteRequestInput routeRequestInput, Void o) {
        if(routeRequestInput.getFindDto().getRequest().getData().isSkipCache() || routeRequestInput.getFindDto().getPassedRoute().size() > 0)
            return true;
        FoundDto foundDto = routeCacheRepository.getCachedRoute(routeRequestInput.getFindDto().getRequest().getData().getSearch().getUserId());
        if(foundDto != null){
            UserInfo userInfo = RouteUtil.getPreviousOrLastRoute(routeRequestInput.getFindDto().getPassedRoute(), this.userInfo);
            if(userInfo != null)
                responseSender.sendFound(userInfo.getUserId(), foundDto);
            return false;
        }
        return true;
    }
}
