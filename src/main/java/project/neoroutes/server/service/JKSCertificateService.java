package project.neoroutes.server.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.neoroutes.helper.KeyStoreWrapper;
import project.neoroutes.server.domain.model.UserCertificate;
import project.neoroutes.server.domain.model.output.UserCertificateList;
import project.neoroutes.server.exception.NeoRoutesRuntimeException;
import sun.misc.BASE64Encoder;

import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class JKSCertificateService implements CertificateService {
    private final KeyStoreWrapper keyStoreWrapper;
    private final String userUUID;

    @Autowired
    public JKSCertificateService(KeyStoreWrapper keyStoreWrapper, String userUUID) {
        this.keyStoreWrapper = keyStoreWrapper;
        this.userUUID = userUUID;
    }

    @Override
    public void addCertificate(UserCertificate userCertificate) {
        try {
            keyStoreWrapper.addCertificate(userCertificate.getCertificate(), userCertificate.getUserId());
        } catch (Exception e) {
            throw new NeoRoutesRuntimeException("Failed to add certificate", e);
        }
    }

    @Override
    public UserCertificateList getCertificates() {
        List<UserCertificate> list = new ArrayList<>();
        try {
            Map<String, Certificate> certificatesMap = keyStoreWrapper.getCertificatesMap();
            certificatesMap.forEach((userId, certificate) -> {
                try {
                    list.add(UserCertificate.builder().userId(userId).certificate(new BASE64Encoder().encode(certificate.getEncoded())).build());
                } catch (CertificateEncodingException e) {
                    list.add(UserCertificate.builder().userId(userId).certificate("N/A").build());
                    log.catching(e);
                }
            });
            return new UserCertificateList(list);
        } catch (KeyStoreException e) {
            throw new NeoRoutesRuntimeException("Could not map users to certificates.", e);
        }
    }

    @Override
    public void deleteCertificate(String userId) {
        if(userUUID.equals(userId))
            return;
        try {
            keyStoreWrapper.deleteCertificate(userId);
        } catch (Exception e) {
            throw new NeoRoutesRuntimeException("Failed to delete certificate", e);
        }
    }

    @Override
    public void updateCertificate(UserCertificate userCertificate) {
        deleteCertificate(userCertificate.getUserId());
        try {
            keyStoreWrapper.addCertificate(userCertificate.getCertificate(), userCertificate.getUserId());
        } catch (Exception e) {
            throw new NeoRoutesRuntimeException("Failed to update certificate", e);
        }
    }
}
