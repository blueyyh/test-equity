package com.trafigura.poc.equity.exception;

/**
 * Created by JACK YANG on 2020/9/24.
 */
public class InsertNotFirstVersionException extends RuntimeException {
    public InsertNotFirstVersionException(String message) {
        super(message);
    }

    public InsertNotFirstVersionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertNotFirstVersionException(Throwable cause) {
        super(cause);
    }
}
