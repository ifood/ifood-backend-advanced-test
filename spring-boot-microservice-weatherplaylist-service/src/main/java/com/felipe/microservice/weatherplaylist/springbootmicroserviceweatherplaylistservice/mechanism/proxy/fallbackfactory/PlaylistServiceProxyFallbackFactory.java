package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallbackfactory;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.PlaylistServiceProxy;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallback.PlaylistServiceProxyFallback;

import feign.hystrix.FallbackFactory;

/**
 * Fallback factory for the playlist service which is used for Hystrix circuit-break pattern
 * 
 * @author ffrazato
 */
public class PlaylistServiceProxyFallbackFactory implements FallbackFactory<PlaylistServiceProxy> {

    /**
     * Create a playlist proxy fallback for handling issues
     */
    @Override
    public PlaylistServiceProxy create(Throwable throwable) {
        return new PlaylistServiceProxyFallback(throwable);
    }

}