package project.neoroutes.server.handler.pipeline.register;

import lombok.*;
import project.neoroutes.server.domain.model.protocol.register.RegisterDto;
import project.neoroutes.server.service.connection.InteractableSession;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RegisterInput {
    private RegisterDto registerDto;
    private InteractableSession interactableSession;
}
