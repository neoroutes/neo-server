package project.neoroutes.server.repository;

public interface CommunicationRepository {
    void addMessage(String messageId);
    boolean containsMessage(String messageId);
}
