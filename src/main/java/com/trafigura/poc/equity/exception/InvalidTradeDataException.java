package com.trafigura.poc.equity.exception;

/**
 * Created by JACK YANG on 2020/9/24.
 */
public class InvalidTradeDataException extends RuntimeException {
    public InvalidTradeDataException() {
        super();
    }

    public InvalidTradeDataException(String message) {
        super(message);
    }

    public InvalidTradeDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTradeDataException(Throwable cause) {
        super(cause);
    }

    protected InvalidTradeDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
