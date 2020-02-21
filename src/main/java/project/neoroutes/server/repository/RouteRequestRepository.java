package project.neoroutes.server.repository;


//todo: implement
//usage: Persists request id so it will be checked later when found dto is returned
public interface RouteRequestRepository {
    void addRequest(String id);
    boolean contains(String id);
}
