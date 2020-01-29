package project.neoroutes.server.domain.model.route.communication.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.neoroutes.server.domain.model.route.SignedData;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class DHKeyExchangePayload extends AbstractMessagePayload implements Serializable {
    public SignedData<String> dhMessage;
    public DHKeyExchangePayload() {
        super(PayloadType.DH_HANDSHAKE);
    }
}
