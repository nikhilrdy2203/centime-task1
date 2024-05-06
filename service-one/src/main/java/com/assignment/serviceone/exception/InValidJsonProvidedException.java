package com.assignment.serviceone.exception;

public class InValidJsonProvidedException extends RuntimeException {
    public InValidJsonProvidedException(String errorMessage) {
        super(errorMessage);
    }
}
