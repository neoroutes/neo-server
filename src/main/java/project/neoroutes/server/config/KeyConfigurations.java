package project.neoroutes.server.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import project.neoroutes.helper.KeyStoreWrapper;
import project.neoroutes.server.ServerApplication;
import project.neoroutes.server.domain.model.protocol.UserInfo;

import java.io.IOException;
import java.security.KeyStore;

@Configuration
@Log4j2
@Order(1)
public class KeyConfigurations {
    private final String uuid;
    private final KeyStore keyStore;
    private final String password;
    private final String keyStoreAddress;

    public KeyConfigurations() {
        this.uuid = ServerApplication.uuid;
        this.keyStore = ServerApplication.keyStore;
        this.password = ServerApplication.password;
        keyStoreAddress = ServerApplication.keyStoreAddress;
    }

    @Bean("userUUID")
    public String userUUID(){
        return this.uuid;
    }

    @Bean("keyStore")
    public KeyStore keyStore() throws IOException {
        return keyStore;
    }

    @Bean("keyStoreWrapper")
    @DependsOn({"keyStore"})
    public KeyStoreWrapper keyStoreWrapper(KeyStore keyStore){
        return new KeyStoreWrapper(keyStore, keyStoreAddress, password);
    }

    @Bean
    @DependsOn({"keyStoreWrapper", "userUUID"})
    public UserInfo userInfo(String userUUID, KeyStoreWrapper keyStoreWrapper){
        String publicKeyHash = keyStoreWrapper.getPublicKeyHash();
        return UserInfo.builder()
                .publicKeyHash(publicKeyHash)
                .userId(userUUID)
                .build();
    }

}
