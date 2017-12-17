package com.ifood.backend.advancedtest.exception;

import java.util.Set;

public class GeneralExceptionError {
    private Integer status;
    private String error;
    private String errorCode;
    Set<String> messages;

    public GeneralExceptionError() {
        //Empty constructor
    }

    public GeneralExceptionError(Integer status, String error, String errorCode, Set<String> messages) {
        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
        this.messages = messages;
    }

    public GeneralExceptionError(Integer status, String error, Set<String> messages) {
        this.status = status;
        this.error = error;
        this.messages = messages;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void setMessages(Set<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "GeneralExceptionError{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", messages=" + messages +
                '}';
    }
}
