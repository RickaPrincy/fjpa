package com.ricka.princy.fjpa.exceptions;

public class NotImplementedException extends RuntimeException {

    public NotImplementedException() {
        super("This feature is not implemented yet.");
    }

    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementedException(Throwable cause) {
        super(cause);
    }
}