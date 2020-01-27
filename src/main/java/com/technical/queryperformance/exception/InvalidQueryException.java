package com.technical.queryperformance.exception;

/**
 * The exception that will be thrown in the case when any of the passed queries are null or empty.
 */
public class InvalidQueryException extends RuntimeException {
    public InvalidQueryException() {
        super();
    }

    public InvalidQueryException(String message) {
        super(message);
    }

    public InvalidQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidQueryException(Throwable cause) {
        super(cause);
    }

    protected InvalidQueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
