package project.neoroutes.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.neoroutes.server.domain.model.protocol.NeoRouteMessage;
import project.neoroutes.server.domain.model.protocol.communication.MessageDto;
import project.neoroutes.server.domain.model.protocol.communication.Payload;
import project.neoroutes.server.domain.model.protocol.route.FindDto;
import project.neoroutes.server.domain.model.protocol.route.FoundDto;
import project.neoroutes.server.service.connection.ConnectionManager;
import project.neoroutes.server.service.connection.InteractableSession;

@Service
@Log4j2
public class ResponseSender {
    private final ConnectionManager connectionManager;
    private final ObjectMapper neoRoutesObjectMapper;


    @Autowired
    public ResponseSender(ConnectionManager connectionManager, ObjectMapper neoRoutesObjectMapper) {
        this.connectionManager = connectionManager;
        this.neoRoutesObjectMapper = neoRoutesObjectMapper;
    }

    @SneakyThrows
    public void sendMessage(String receiverId, MessageDto<? extends Payload> messageDto){
        InteractableSession connection = connectionManager.getConnection(receiverId);
        if(connection == null)
            return;

        String value = neoRoutesObjectMapper.writeValueAsString(NeoRouteMessage.builder().object(messageDto).type(NeoRouteMessage.MessageType.COMM).build());
        connection.sendMessage(value);
    }

    @SneakyThrows
    public void sendFound(String receiverId, FoundDto foundDto){
        InteractableSession connection = connectionManager.getConnection(receiverId);
        if(connection == null)
            return;

        String value = neoRoutesObjectMapper.writeValueAsString(NeoRouteMessage.builder().object(foundDto).type(NeoRouteMessage.MessageType.FOUND_RESP).build());
        connection.sendMessage(value);
    }

    public void dispatchFindRequest(FindDto findDto) throws JsonProcessingException {
        String value = neoRoutesObjectMapper.writeValueAsString(NeoRouteMessage.builder().object(findDto).type(NeoRouteMessage.MessageType.FIND_REQ).build());
        connectionManager.getAllConnections().forEach(interactableSession -> {
            try {
                interactableSession.sendMessage(value);
            } catch (Exception e) {
                log.catching(e);
            }
        });
    }
}
