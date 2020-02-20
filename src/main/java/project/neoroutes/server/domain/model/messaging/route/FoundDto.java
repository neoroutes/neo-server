package project.neoroutes.server.domain.model.messaging.route;

import lombok.*;
import project.neoroutes.server.domain.model.messaging.SignedData;
import project.neoroutes.server.domain.model.messaging.UserInfo;
import project.neoroutes.server.domain.model.messaging.register.RegisterInfo;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FoundDto implements Serializable {
    SignedData<FindRequest> request;
    SignedData<RegisterInfo> result;
    List<UserInfo> route;
}
