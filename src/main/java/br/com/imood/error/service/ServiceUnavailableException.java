package br.com.imood.error.service;

public class ServiceUnavailableException extends ExternalServiceException {

    public ServiceUnavailableException(){
        super();
    }

    public ServiceUnavailableException(String message){
        super(message);
    }
}
