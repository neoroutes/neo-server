package project.neoroutes.server.domain.model.control;

import lombok.*;

@Data
@Builder
public class UserCertificate {
    private String userId;
    private String certificate;
}
