package project.neoroutes.server.domain.model.messaging.communication.payload;

import lombok.AllArgsConstructor;
import project.neoroutes.server.domain.model.messaging.communication.Payload;

@AllArgsConstructor
public abstract class AbstractMessagePayload implements Payload {
    private final PayloadType payloadType;

    public final PayloadType getPayloadType(){
        return payloadType;
    }

    public enum PayloadType {
        DIRECT_MESSAGE, DH_HANDSHAKE
    }
}
