package project.neoroutes.server.domain.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class RouteRequest {
    private String userId;
    private String requestId;
    private Date date;
}
