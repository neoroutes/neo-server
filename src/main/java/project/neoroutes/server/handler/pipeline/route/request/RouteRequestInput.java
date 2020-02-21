package project.neoroutes.server.handler.pipeline.route.request;

import lombok.*;
import project.neoroutes.server.domain.model.protocol.route.FindDto;
import project.neoroutes.server.service.connection.InteractableSession;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RouteRequestInput {
    FindDto findDto;
    InteractableSession interactableSession;
}
