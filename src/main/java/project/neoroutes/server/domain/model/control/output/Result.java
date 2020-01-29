package project.neoroutes.server.domain.model.control.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.neoroutes.server.domain.model.Status;

@AllArgsConstructor
@Getter
public class Result {
    private final Status status;
}
