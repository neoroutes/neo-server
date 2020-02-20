package project.neoroutes.server.domain.model.messaging.register;

import lombok.*;
import project.neoroutes.server.domain.model.messaging.UserInfo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterInfo extends UserInfo {
    private long time;
}
