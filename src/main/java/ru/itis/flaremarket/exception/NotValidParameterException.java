package ru.itis.flaremarket.exception;

public class NotValidParameterException extends RuntimeException {
    public NotValidParameterException() {
        super();
    }

    public NotValidParameterException(String message) {
        super(message);
    }

    public NotValidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidParameterException(Throwable cause) {
        super(cause);
    }

    protected NotValidParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
