package com.trafigura.poc.equity.exception;

/**
 * Created by JACK YANG on 2020/9/24.
 */
public class TransactionAlreadyCanceledException extends RuntimeException {
    public TransactionAlreadyCanceledException() {
        super();
    }

    public TransactionAlreadyCanceledException(String message) {
        super(message);
    }

    public TransactionAlreadyCanceledException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionAlreadyCanceledException(Throwable cause) {
        super(cause);
    }

    protected TransactionAlreadyCanceledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
