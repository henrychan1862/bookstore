package com.automate.bookstore.aop;

import org.springframework.http.HttpStatus;

public class PrettyError {

    private HttpStatus responseStatus;
    private String message;
    private String reason;

    public PrettyError() {
    }

    public PrettyError(HttpStatus responseStatus, String message, String reason) {
        this.responseStatus = responseStatus;
        this.message = message;
        this.reason = reason;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}


