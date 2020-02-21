package project.neoroutes.server.domain.model.protocol;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NeoRouteMessage<E extends Serializable> implements Serializable {
    private MessageType type;
    private E object;

    public enum MessageType {
        FIND_REQ, FOUND_RESP, COMM
    }
}
