package project.neoroutes.server.domain.model.messaging.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.neoroutes.server.domain.model.messaging.SignedData;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterDto implements Serializable {
    private SignedData<RegisterInfo> registerInfo;
}
