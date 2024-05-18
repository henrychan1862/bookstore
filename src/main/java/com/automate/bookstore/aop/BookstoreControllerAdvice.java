package com.automate.bookstore.aop;

import com.automate.bookstore.book.BookNotFoundException;
import com.automate.bookstore.customer.CustomerInfoUpdateFailedException;
import com.automate.bookstore.order.OrderNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class BookstoreControllerAdvice {

    @ExceptionHandler({BookNotFoundException.class, OrderNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public PrettyError handleBookNotFoundException(EntityNotFoundException e) {
        String bookOrOrder = e.getClass().getSimpleName().equals("BookNotFoundException") ? "book" : "order";
        return new PrettyError(HttpStatus.NOT_FOUND, "We cannot find the " + bookOrOrder, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public PrettyError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new PrettyError(HttpStatus.BAD_REQUEST,
                "Invalid type of parameter. Please revise query",
                "Value " + e.getValue() + " provided for parameter " + e.getName() + " is not of type " + e.getRequiredType());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public PrettyError handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new PrettyError(HttpStatus.BAD_REQUEST, "Invalid request body. Please revise", e.getMessage());
    }

    @ExceptionHandler(CustomerInfoUpdateFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public PrettyError handleCustomerInfoUpdateFailedException(CustomerInfoUpdateFailedException e) {
        return new PrettyError(HttpStatus.BAD_REQUEST, "Update failed", e.getMessage());
    }


}
