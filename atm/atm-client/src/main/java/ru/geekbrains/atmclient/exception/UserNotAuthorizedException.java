package ru.geekbrains.atmclient.exception;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException() {
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }

    public UserNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAuthorizedException(Throwable cause) {
        super(cause);
    }

    public UserNotAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
