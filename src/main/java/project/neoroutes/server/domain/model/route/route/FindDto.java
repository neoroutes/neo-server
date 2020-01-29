package project.neoroutes.server.domain.model.route.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.neoroutes.server.domain.model.route.SignedData;
import project.neoroutes.server.domain.model.route.UserInfo;

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
