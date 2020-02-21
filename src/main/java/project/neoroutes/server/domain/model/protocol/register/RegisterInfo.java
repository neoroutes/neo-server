package project.neoroutes.server.domain.model.protocol.register;

import lombok.*;
import project.neoroutes.server.domain.model.protocol.UserInfo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterInfo extends UserInfo {
    private long time;
}
