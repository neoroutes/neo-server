package project.neoroutes.server.util;

import project.neoroutes.server.domain.model.protocol.UserInfo;

import java.util.List;

public class RouteUtil {
    public static UserInfo getPreviousOrLastRoute(List<UserInfo> userInfos, UserInfo thisUserInfo){
        if(userInfos.contains(thisUserInfo)){
            int i = userInfos.indexOf(thisUserInfo);
            if(i == 0)
                return thisUserInfo;
            if(userInfos.get(i - 1) != null)
                return userInfos.get(i - 1);
        }

        return userInfos.get(userInfos.size() - 1);
    }

    public static UserInfo getNextRoute(List<UserInfo> userInfos, UserInfo thisUserInfo){
        if(userInfos.contains(thisUserInfo)){
            int i = userInfos.indexOf(thisUserInfo);
            if(userInfos.size() == i + 1)
                return thisUserInfo;
            return userInfos.get(i + 1);
        }

        return null;
    }
}
