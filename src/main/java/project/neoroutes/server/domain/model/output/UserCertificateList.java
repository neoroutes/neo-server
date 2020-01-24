package project.neoroutes.server.domain.model.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.neoroutes.server.domain.model.UserCertificate;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserCertificateList extends SuccessResult{
    private final List<UserCertificate> userCertificates;
}
