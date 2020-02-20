package project.neoroutes.server.domain.model.messaging.communication.payload;

import lombok.Getter;
import lombok.Setter;
import project.neoroutes.server.domain.model.messaging.SignedData;

import java.io.Serializable;

@Getter
@Setter
public class DHKeyExchangePayload extends AbstractMessagePayload implements Serializable {
    public SignedData<String> dhMessage;
    public DHKeyExchangePayload() {
        super(PayloadType.DH_HANDSHAKE);
    }
}
