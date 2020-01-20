package project.neoroutes.server.domain.model.o;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result {
    private final Status status;

    public enum Status {
        SUCCESS, FAILED
    }
}
