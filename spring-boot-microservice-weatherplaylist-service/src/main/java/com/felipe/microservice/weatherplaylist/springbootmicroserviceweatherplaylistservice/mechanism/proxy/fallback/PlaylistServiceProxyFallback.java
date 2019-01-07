package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallback;

import java.util.ArrayList;
import java.util.List;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.PlaylistServiceProxy;

import feign.FeignException;

/**
 * Concrete fallback class for handling the issues when requesting data for playlist microservice
 * @author ffrazato
 *
 */
public class PlaylistServiceProxyFallback implements PlaylistServiceProxy {

    private final Throwable cause;

    /**
     * Constructor for keeping the root cause
     * @param cause
     */
    public PlaylistServiceProxyFallback(Throwable cause) {
        this.cause = cause;
    }

    /**
     * Handle when circuit is open
     */
    @Override
    public List<String> retrieveSoundtrackNamesByGenre(String genre) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            // Here we can treat the exception and also get a previous value from the cache
        }
        return new ArrayList<>();
    }

}