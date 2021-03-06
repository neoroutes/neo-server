package project.neoroutes.server.domain.model.protocol.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.neoroutes.server.domain.model.protocol.UserInfo;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindRequest implements Serializable {
    private String requestId;
    private UserInfo requester;
    private UserInfo search;
    private boolean skipCache;
}
