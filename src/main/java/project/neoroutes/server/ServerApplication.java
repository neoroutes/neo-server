package project.neoroutes.server;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.neoroutes.helper.PrivateKeyProvider;
import project.neoroutes.key.*;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

@SpringBootApplication
@Log4j2
public class ServerApplication {
	private final static String passwordArgName="server.ssl.key-store-password";
	public final static String keyStoreAddress="keystore.jks";
	public static String uuid;
	public static KeyStore keyStore;
	public static String password;

	public static void main(String[] args) throws Exception {
		log.info("Initializing application");
		init(args);
		log.info("Successfully initialized");
		SpringApplication.run(ServerApplication.class, args);
	}

	private static void init(String[] args) throws Exception {
		password = getPassword(args);
		KeyPair keyPair = getKeyPairFromExistingFile();
		log.info("Generating / Reading user uuid");
		uuid = new PubHashUserIdGenerator("user.uuid", keyPair != null ? keyPair.getPublic() : null).generate();
		CNGenerator cnGenerator = new NeoRoutesCNGenerator(uuid);
		KeyStoreGenerator keyStoreGenerator = new KeyStoreGenerator(cnGenerator, keyStoreAddress, password, keyPair);
		log.info("Generating / reading keystore");
		keyStore = keyStoreGenerator.generate();
	}

	@SneakyThrows
	private static KeyPair getKeyPairFromExistingFile() {
		log.info("Generating key pair if `"+keyStoreAddress+"` does not exist");
		KeyPair keyPair = null;
		File file = new File(keyStoreAddress);
		if(!file.exists()){
			keyPair = new KeyGenerator().generate();
		}else {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(new FileInputStream(file), password.toCharArray());
			PublicKey publicKey = keyStore.getCertificate("main").getPublicKey();
			PrivateKey privateKey = new PrivateKeyProvider(keyStore, password).getPrivateKey();
			keyPair = new KeyPair(publicKey, privateKey);
		}

		return keyPair;
	}

	private static String getPassword(String[] args) throws Exception {
		log.info("Getting password from parameters / environment");
		String password = null;
		for (String arg : args) {
			if(arg.startsWith(passwordArgName)){
				password = arg.replaceAll(passwordArgName + '=', "");
				break;
			}
		}
		if(password == null && (password = System.getenv(passwordArgName)) == null){
			throw new Exception("Argument not found `server.ssl.key-store-password`");
		}
		return password;
	}

}
