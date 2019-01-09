package br.com.imood.error.service;

public class OpenWeatherServiceException extends ExternalServiceException {

    public OpenWeatherServiceException(){
        super();
    }

    public OpenWeatherServiceException(String message, Object... params){
        super(message, params);
    }

}
