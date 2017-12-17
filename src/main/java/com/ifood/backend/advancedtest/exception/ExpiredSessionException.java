package com.ifood.backend.advancedtest.exception;

import feign.RetryableException;

public class ExpiredSessionException extends RetryableException {
    public ExpiredSessionException(String message) {
        super(message, null);
    }
}