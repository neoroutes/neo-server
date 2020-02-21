package project.neoroutes.server.domain.model.protocol.communication;

import lombok.*;
import project.neoroutes.server.domain.model.protocol.SignedData;
import project.neoroutes.server.domain.model.protocol.UserInfo;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageDto<E extends Payload> implements Serializable {
    private SignedData<Message<E>> message;
    List<UserInfo> route;
}
