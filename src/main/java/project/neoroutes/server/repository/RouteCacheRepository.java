package project.neoroutes.server.repository;

import project.neoroutes.server.domain.model.protocol.route.FoundDto;

import java.util.Date;


//todo: implement
public interface RouteCacheRepository {
    void add(String userId, FoundDto foundDto);
    FoundDto getCachedRoute(String userId);
    void clean(Date date);
}
