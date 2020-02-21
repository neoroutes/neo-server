package project.neoroutes.server.handler.pipeline.route.response;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.protocol.UserInfo;
import project.neoroutes.server.domain.model.protocol.route.FoundDto;
import project.neoroutes.server.repository.RouteCacheRepository;
import project.neoroutes.server.util.RouteUtil;

public class RouteResponseCacheStep implements Pipeline.Step<FoundDto, Void> {
    private final RouteCacheRepository routeCacheRepository;
    private final UserInfo userInfo;

    public RouteResponseCacheStep(RouteCacheRepository routeCacheRepository, UserInfo userInfo) {
        this.routeCacheRepository = routeCacheRepository;
        this.userInfo = userInfo;
    }

    @Override
    public boolean process(FoundDto foundDto, Void o) {
        UserInfo userInfo = RouteUtil.getPreviousOrLastRoute(foundDto.getRoute(), this.userInfo);
        if(userInfo.equals(this.userInfo)){
            routeCacheRepository.add(foundDto.getRequest().getData().getSearch().getUserId(), foundDto);
        }
        return true;
    }
}
