package project.neoroutes.server.repository;

import org.springframework.stereotype.Service;
import project.neoroutes.server.domain.model.RouteRequest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ListRouteRequestRepository implements RouteRequestRepository {
    private List<RouteRequest> routeRequests = new CopyOnWriteArrayList<>();

    @Override
    public void addRequest(RouteRequest routeRequest) {
        routeRequests.add(routeRequest);
    }

    @Override
    public boolean containsRequest(String requestId) {
        for (RouteRequest routeRequest : routeRequests) {
            if(routeRequest.getRequestId().equals(requestId))
                return true;
        }
        return false;
    }

    @Override
    public void deleteRequest(String requestId) {
        for (RouteRequest routeRequest : routeRequests) {
            if(routeRequest.getRequestId().equals(requestId)){
                routeRequests.remove(routeRequest);
                return;
            }
        }
    }

    //todo: flush old using schedule
    @Override
    public void flushOld(Date dateBefore) {
        for (RouteRequest routeRequest : routeRequests) {
            if(routeRequest.getDate().before(dateBefore)){
                routeRequests.remove(routeRequest);
                return;
            }
        }
    }
}
