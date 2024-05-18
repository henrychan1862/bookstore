package com.automate.bookstore.order;

import jakarta.persistence.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(Exception cause) {
        super(cause);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
