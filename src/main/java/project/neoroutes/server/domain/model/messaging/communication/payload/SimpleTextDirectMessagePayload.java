package project.neoroutes.server.domain.model.messaging.communication.payload;

import lombok.*;
import project.neoroutes.server.domain.model.messaging.SignedData;

import java.io.Serializable;

@Getter
@Setter
public class SimpleTextDirectMessagePayload extends AbstractMessagePayload implements Serializable {
    private SignedData<TextMessage> textMessage;
    public SimpleTextDirectMessagePayload() {
        super(PayloadType.DIRECT_MESSAGE);
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TextMessage {
        private String message;
        private long time;
    }
}