package com.trafigura.poc.equity.exception;

/**
 * Created by JACK YANG on 2020/9/24.
 */
public class TradeVersionNotCorrectException extends RuntimeException {
    public TradeVersionNotCorrectException() {
        super();
    }

    public TradeVersionNotCorrectException(String message) {
        super(message);
    }

    public TradeVersionNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeVersionNotCorrectException(Throwable cause) {
        super(cause);
    }

    protected TradeVersionNotCorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
