package com.ifood.backend.advancedtest.service.spotify;

import com.ifood.backend.advancedtest.exception.ExpiredSessionException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SpotifyErrorDecoder implements ErrorDecoder {

    private static final Logger logger =
            LoggerFactory.getLogger(SpotifyErrorDecoder.class);

    private static final int UNAUTHORIZED = 401;

    @Autowired
    SpotifyApiAuthenticationService spotifyApiAuthenticationService;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == UNAUTHORIZED) {
            //Caso o token tenha expirado remove o token do cache e lança uma exceção para forçar
            //o Feign a chamar novamente a API de auth
            logger.debug("Token has been expired. Removing from cache to try again");
            spotifyApiAuthenticationService.removeTokenFromCache();
            throw new ExpiredSessionException("Session Expired");
        }

        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
