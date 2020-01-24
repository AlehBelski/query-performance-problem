package com.technical.queryperformance.exception;

//todo add javadoc
public class BlankQueryRequestException extends RuntimeException {
    public BlankQueryRequestException() {
        super();
    }

    public BlankQueryRequestException(String message) {
        super(message);
    }

    public BlankQueryRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankQueryRequestException(Throwable cause) {
        super(cause);
    }

    protected BlankQueryRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
