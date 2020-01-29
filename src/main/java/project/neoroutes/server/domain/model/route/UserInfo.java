package project.neoroutes.server.domain.model.route;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private String userId;
    private String publicKeyHash;
}
