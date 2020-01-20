package project.neoroutes.server.exception;

import lombok.Getter;

@Getter
public class NeoRoutesRuntimeException extends RuntimeException {
    private final Exception rootException;

    public NeoRoutesRuntimeException(Exception rootException) {
        this.rootException = rootException;
    }

    public NeoRoutesRuntimeException(String message, Exception rootException) {
        super(message);
        this.rootException = rootException;
    }

    public NeoRoutesRuntimeException(String message, Throwable cause, Exception rootException) {
        super(message, cause);
        this.rootException = rootException;
    }

    public NeoRoutesRuntimeException(Throwable cause, Exception rootException) {
        super(cause);
        this.rootException = rootException;
    }

    public NeoRoutesRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Exception rootException) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.rootException = rootException;
    }
}
