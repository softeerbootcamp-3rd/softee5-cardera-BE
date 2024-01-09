package com.warmingup.cardera.exception;

public class FailSearchException extends RuntimeException{

    public FailSearchException() {
    }

    public FailSearchException(String message) {
        super(message);
    }

    public FailSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailSearchException(Throwable cause) {
        super(cause);
    }

    public FailSearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
