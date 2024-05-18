package com.automate.bookstore.book;

import jakarta.persistence.EntityNotFoundException;

public class BookNotFoundException extends EntityNotFoundException {

    public BookNotFoundException() {
    }

    public BookNotFoundException(Exception cause) {
        super(cause);
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
