package br.com.imood.error.service;

import br.com.imood.error.ExceptionWithCustomMessage;

public class ExternalServiceException extends ExceptionWithCustomMessage {

    public ExternalServiceException(String message, Object... params){
        super(String.format(message, params));
    }

    public ExternalServiceException(){
        super();
    }

}
