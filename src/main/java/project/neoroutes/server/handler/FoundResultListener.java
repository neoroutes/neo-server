package project.neoroutes.server.handler;

import project.neoroutes.server.domain.model.protocol.route.FoundDto;

public interface FoundResultListener {
    void onFound(FoundDto foundDto);
}
