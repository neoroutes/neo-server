package project.neoroutes.server.config;

import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import project.neoroutes.helper.KeyStoreWrapper;
import project.neoroutes.server.ServerApplication;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
public class SSLConfig {
    private final KeyStore keyStore;
    private final String password = ServerApplication.password;

    @Autowired
    public SSLConfig(KeyStoreWrapper keyStoreWrapper) {
        this.keyStore = keyStoreWrapper.getKeyStore();
    }


    private SSLContext sslContext() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new SSLContextBuilder()
                .loadTrustMaterial(
                        keyStore
                        , new TrustStrategy() {
                            @Override
                            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                                System.out.println("==========");
                                for (X509Certificate x509Certificate : x509Certificates) {
                                    System.out.println(x509Certificate.getIssuerX500Principal().toString());
                                    System.out.println(x509Certificate.getIssuerAlternativeNames() != null ? x509Certificate.getIssuerAlternativeNames().toString() : "");
                                    System.out.println(new String(x509Certificate.getPublicKey().toString()));
                                }
                                System.out.println("==========");
                                System.out.println(s);
                                System.out.println("==========");
                                return true;
                            }
                        }
                ).build();
    }
}
