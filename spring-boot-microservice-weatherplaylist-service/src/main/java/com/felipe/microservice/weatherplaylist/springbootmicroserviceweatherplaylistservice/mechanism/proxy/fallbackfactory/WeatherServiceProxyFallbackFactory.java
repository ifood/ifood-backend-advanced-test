package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallbackfactory;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.WeatherServiceProxy;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallback.WeatherServiceProxyFallback;

import feign.hystrix.FallbackFactory;

/**
 * Fallback factory for the weather service which is used for Hystrix circuit-break pattern
 * 
 * @author ffrazato
 */
public class WeatherServiceProxyFallbackFactory implements FallbackFactory<WeatherServiceProxy> {

    /**
     * Create a weather proxy fallback for handling issues
     */
    @Override
    public WeatherServiceProxy create(Throwable cause) {
        return new WeatherServiceProxyFallback(cause);
    }

}
