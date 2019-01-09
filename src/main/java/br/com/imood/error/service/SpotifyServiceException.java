package br.com.imood.error.service;

public class SpotifyServiceException extends ExternalServiceException {

    public SpotifyServiceException(){
        super();
    }

    public SpotifyServiceException(String message, Object... params){
        super(message, params);
    }

}
