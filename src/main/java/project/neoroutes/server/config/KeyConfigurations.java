package project.neoroutes.server.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import project.neoroutes.helper.KeyStoreWrapper;
import project.neoroutes.key.*;

import java.io.IOException;
import java.security.KeyStore;

@Configuration
@Log4j2
public class KeyConfigurations {
    private final String uuidAddress;
    private final String keyStoreAddress;
    private final String password;


    public KeyConfigurations(@Value("${neoroutes.keys.uuidAddress:user.uuid}") String uuidAddress, @Value("${neoroutes.keys.keyStoreAddress:keystore.jks}") String keyStoreAddress, @Value("${neoroutes.keys.password:password}") String password) {
        this.uuidAddress = uuidAddress;
        this.keyStoreAddress = keyStoreAddress;
        this.password = password;
    }

    @Bean("userUUID")
    public String userUUID(){
        return new UUIDFileUserIdGenerator(uuidAddress).generate();
    }

    @Bean
    @DependsOn(value = "userUUID")
    public CNGenerator cnGenerator(String userUUID){
        return new NeoRoutesCNGenerator(userUUID);
    }

    @Bean("keyStore")
    @DependsOn(value = {"cnGenerator", "userUUID"})
    public KeyStore keyStore(CNGenerator cnGenerator, String userUUID) throws IOException {
        log.info("Initializing application keystore with user id: " + userUUID);
        return new KeyStoreGenerator(cnGenerator, keyStoreAddress, password, userUUID).generate();
    }

    @Bean("keyStoreWrapper")
    @DependsOn(value = "keyStore")
    public KeyStoreWrapper keyStoreWrapper(KeyStore keyStore){
        return new KeyStoreWrapper(keyStore, keyStoreAddress, password);
    }

}
