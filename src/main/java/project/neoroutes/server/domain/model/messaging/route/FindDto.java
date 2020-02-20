package project.neoroutes.server.domain.model.messaging.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.neoroutes.server.domain.model.messaging.SignedData;
import project.neoroutes.server.domain.model.messaging.UserInfo;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FindDto implements Serializable {
    SignedData<FindRequest> request;
    List<UserInfo> passedRoute;
}
