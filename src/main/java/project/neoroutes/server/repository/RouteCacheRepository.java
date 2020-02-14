package project.neoroutes.server.repository;

import project.neoroutes.server.domain.model.route.UserInfo;

import java.util.Date;
import java.util.List;


//todo: implement
public interface RouteCacheRepository {
    void add(String userId, List<UserInfo> route);
    List<UserInfo> getCachedRoute(String userId);
    void clean(Date date);
}
