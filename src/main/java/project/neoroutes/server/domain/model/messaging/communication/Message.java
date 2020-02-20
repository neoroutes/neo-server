package project.neoroutes.server.domain.model.messaging.communication;

import lombok.*;
import project.neoroutes.server.domain.model.messaging.UserInfo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message<E extends Payload> {
    private String id;
    private UserInfo userInfo;
    private E payload;
}
