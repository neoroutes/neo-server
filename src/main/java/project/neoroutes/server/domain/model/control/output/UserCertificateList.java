package project.neoroutes.server.domain.model.control.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.neoroutes.server.domain.model.control.UserCertificate;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserCertificateList extends SuccessResult {
    private final List<UserCertificate> userCertificates;
}
