package project.neoroutes.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.neoroutes.helper.KeyStoreWrapper;
import project.neoroutes.server.domain.model.i.AddCertificate;
import project.neoroutes.server.exception.NeoRoutesRuntimeException;

@Service
public class JKSCertificateService implements CertificateService {
    private final KeyStoreWrapper keyStoreWrapper;

    @Autowired
    public JKSCertificateService(KeyStoreWrapper keyStoreWrapper) {
        this.keyStoreWrapper = keyStoreWrapper;
    }

    @Override
    public void addCertificate(AddCertificate addCertificate) {
        try {
            keyStoreWrapper.addCertificate(addCertificate.getCertificate(), addCertificate.getUserId());
        } catch (Exception e) {
            throw new NeoRoutesRuntimeException(e);
        }
    }
}
