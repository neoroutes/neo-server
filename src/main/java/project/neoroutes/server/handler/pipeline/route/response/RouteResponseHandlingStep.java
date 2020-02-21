package project.neoroutes.server.handler.pipeline.route.response;

import project.neoroutes.Pipeline;
import project.neoroutes.server.domain.model.protocol.UserInfo;
import project.neoroutes.server.domain.model.protocol.route.FoundDto;
import project.neoroutes.server.handler.FoundResultListener;

public class RouteResponseHandlingStep implements Pipeline.Step<FoundDto, Void> {
    private final UserInfo userInfo;
    private final FoundResultListener foundResultListener;

    public RouteResponseHandlingStep(UserInfo userInfo, FoundResultListener foundResultListener) {
        this.userInfo = userInfo;
        this.foundResultListener = foundResultListener;
    }

    @Override
    public boolean process(FoundDto foundDto, Void o) {
        if(foundDto.getRequest().getData().getRequester().equals(userInfo)){
            foundResultListener.onFound(foundDto);
        }
        return true;
    }
}
