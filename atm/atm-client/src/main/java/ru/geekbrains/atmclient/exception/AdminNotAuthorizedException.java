package ru.geekbrains.atmclient.exception;

public class AdminNotAuthorizedException extends RuntimeException {

    public AdminNotAuthorizedException() {
    }

    public AdminNotAuthorizedException(String message) {
        super(message);
    }

    public AdminNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotAuthorizedException(Throwable cause) {
        super(cause);
    }

    public AdminNotAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
