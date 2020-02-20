package project.neoroutes.server.handler.pipeline.route;

import lombok.*;
import project.neoroutes.server.domain.model.messaging.route.FindDto;
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
