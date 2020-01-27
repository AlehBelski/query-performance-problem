package com.technical.queryperformance.exception;

/**
 * The exception that will be thrown in the case when the passed body doesn't contain the list of queries.
 */
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
