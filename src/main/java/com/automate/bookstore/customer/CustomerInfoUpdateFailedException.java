package com.automate.bookstore.customer;

public class CustomerInfoUpdateFailedException extends RuntimeException {
    public CustomerInfoUpdateFailedException() {
    }

    public CustomerInfoUpdateFailedException(String message) {
        super(message);
    }

    public CustomerInfoUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerInfoUpdateFailedException(Throwable cause) {
        super(cause);
    }

    public CustomerInfoUpdateFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
