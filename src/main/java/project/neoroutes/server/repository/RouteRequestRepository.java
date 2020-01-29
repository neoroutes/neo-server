package project.neoroutes.server.repository;

import project.neoroutes.server.domain.model.RouteRequest;

import java.util.Date;

public interface RouteRequestRepository {
    void addRequest(RouteRequest routeRequest);
    boolean containsRequest(String requestId);
    void deleteRequest(String requestId);
    void flushOld(Date date);
}
