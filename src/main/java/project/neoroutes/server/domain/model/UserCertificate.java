package project.neoroutes.server.domain.model;

import lombok.*;

@Data
@Builder
public class UserCertificate {
    private String userId;
    private String certificate;
}
