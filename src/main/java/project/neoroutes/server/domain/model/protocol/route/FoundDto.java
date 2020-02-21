package project.neoroutes.server.domain.model.protocol.route;

import lombok.*;
import project.neoroutes.server.domain.model.protocol.SignedData;
import project.neoroutes.server.domain.model.protocol.UserInfo;
import project.neoroutes.server.domain.model.protocol.register.RegisterInfo;

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
