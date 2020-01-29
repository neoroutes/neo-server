package project.neoroutes.server.domain.model.route.register;

import lombok.*;
import project.neoroutes.server.domain.model.route.UserInfo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterInfo extends UserInfo {
    private long time;
}
