package com.trafigura.poc.equity.exception;

/**
 * Created by JACK YANG on 2020/9/24.
 */
public class NoTranactionExistException extends RuntimeException {
    public NoTranactionExistException() {
        super();
    }

    public NoTranactionExistException(String message) {
        super(message);
    }

    public NoTranactionExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTranactionExistException(Throwable cause) {
        super(cause);
    }

    protected NoTranactionExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
