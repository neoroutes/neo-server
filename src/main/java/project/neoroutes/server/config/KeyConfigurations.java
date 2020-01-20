package project.neoroutes.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import project.neoroutes.key.*;

import java.io.IOException;
import java.security.KeyStore;

@Configuration
public class KeyConfigurations {
    private final String uuidAddress;
    private final String keyStoreAddress;
    private final String password;


    public KeyConfigurations(@Value("${neoroutes.keys.uuidAddress}") String uuidAddress, @Value("${neoroutes.keys.keyStoreAddress}") String keyStoreAddress, @Value("${neoroutes.keys.password}") String password) {
        this.uuidAddress = uuidAddress;
        this.keyStoreAddress = keyStoreAddress;
        this.password = password;
    }

    @Bean
    public UserIdGenerator userIdGenerator(){
        return new UUIDFileUserIdGenerator(uuidAddress);
    }

    @Bean
    @DependsOn(value = "userIdGenerator")
    public CNGenerator cnGenerator(UserIdGenerator userIdGenerator){
        return new NeoRoutesCNGenerator(userIdGenerator.generate());
    }

    @Bean("keyStoreGenerator")
    @DependsOn(value = {"cnGenerator", "userIdGenerator"})
    public KeyStore keyStoreGenerator(CNGenerator cnGenerator, UserIdGenerator userIdGenerator) throws IOException {
        return new KeyStoreGenerator(cnGenerator, keyStoreAddress, password, userIdGenerator.generate()).generate();
    }

}
