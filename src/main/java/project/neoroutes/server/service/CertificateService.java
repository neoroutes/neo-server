package project.neoroutes.server.service;

import project.neoroutes.server.domain.model.UserCertificate;
import project.neoroutes.server.domain.model.output.UserCertificateList;

public interface CertificateService {
    void addCertificate(UserCertificate userCertificate);
    UserCertificateList getCertificates();
    void deleteCertificate(String userId);
    void updateCertificate(UserCertificate userCertificate);
}
