package project.neoroutes.server.handler.factory;

import project.neoroutes.server.domain.model.protocol.SignedData;
import project.neoroutes.server.domain.model.protocol.UserInfo;
import project.neoroutes.server.domain.model.protocol.register.RegisterDto;
import project.neoroutes.server.domain.model.protocol.register.RegisterInfo;
import project.neoroutes.server.domain.model.protocol.route.FindDto;
import project.neoroutes.server.domain.model.protocol.route.FindRequest;
import project.neoroutes.server.domain.model.protocol.route.FoundDto;

import java.util.List;

public class FoundMessageFactory {
    public static FoundDto found(FindDto findDto, RegisterDto registerDto){
        return found(registerDto.getRegisterInfo(), findDto.getRequest(), findDto.getPassedRoute());
    }

    public static FoundDto found(SignedData<RegisterInfo> registerInfo, SignedData<FindRequest> request, List<UserInfo> passedRoute){
        return FoundDto.builder()
                .result(registerInfo)
                .request(request)
                .route(passedRoute)
                .build();
    }
}
