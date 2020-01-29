package project.neoroutes.server.domain.model.route.route;

import lombok.*;
import project.neoroutes.server.domain.model.route.SignedData;
import project.neoroutes.server.domain.model.route.UserInfo;
import project.neoroutes.server.domain.model.route.register.RegisterInfo;

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
