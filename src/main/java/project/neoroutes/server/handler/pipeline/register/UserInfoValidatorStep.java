package project.neoroutes.server.handler.pipeline.register;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import project.neoroutes.Pipeline;
import project.neoroutes.helper.KeyStoreWrapper;
import project.neoroutes.helper.Serializer;
import project.neoroutes.helper.SignatureVerifier;
import project.neoroutes.helper.TimeUtil;
import project.neoroutes.server.domain.model.protocol.SignedData;
import project.neoroutes.server.domain.model.protocol.register.RegisterInfo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

@Log4j2
public class UserInfoValidatorStep implements Pipeline.Step<RegisterInput, Void> {
    private final KeyStoreWrapper keyStoreWrapper;
    private final Serializer<RegisterInfo> registerInfoSerializer;

    public UserInfoValidatorStep(KeyStoreWrapper keyStoreWrapper) {
        this.keyStoreWrapper = keyStoreWrapper;
        this.registerInfoSerializer = new Serializer<>();
    }


    @Override
    public boolean process(RegisterInput registerInput, Void o) {
        SignedData<RegisterInfo> registerInfo = registerInput.getRegisterDto().getRegisterInfo();
        Certificate certificate;
        try {
            certificate = keyStoreWrapper.getCertificate(registerInfo.getData().getUserId());
        } catch (KeyStoreException e) {
            log.catching(e);
            return false;
        }

        try {
            SignatureVerifier.verify(certificate.getPublicKey(), new Base64().decode(registerInfo.getSignature().getBytes()), registerInfoSerializer.serialize(registerInfo.getData()));
        } catch (InvalidKeyException | IOException e) {
            log.catching(e);
            return false;
        }

        return TimeUtil.getCurrentTimeAsTimeStamp() - registerInfo.getData().getTime() <= 5 * 60 * 1000;
    }
}
