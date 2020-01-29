package project.neoroutes.server.domain.model.route.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.neoroutes.server.domain.model.route.SignedData;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterDto implements Serializable {
    private SignedData<RegisterInfo> registerInfo;
}
