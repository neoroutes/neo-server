package project.neoroutes.server.domain.model.i;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddCertificate {
    @NotNull
    @NotEmpty
    private String userId;
    @NotNull
    @NotEmpty
    private String certificate;
}
