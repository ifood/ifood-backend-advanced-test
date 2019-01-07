package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallback;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.WeatherServiceProxy;

import feign.FeignException;

/**
 * Concrete fallback class for handling the issues when requesting data for weather microservice
 * 
 * @author ffrazato
 */
public class WeatherServiceProxyFallback implements WeatherServiceProxy {

    private final Throwable cause;

    /**
     * Constructor for keeping the root cause
     * 
     * @param cause
     */
    public WeatherServiceProxyFallback(Throwable cause) {
        this.cause = cause;
    }

    /**
     * Handle circuit open for retrieving temperature by city
     */
    @Override
    public Double retrieveCurrentTemperatureByCity(String cityName) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            // Here we can treat the exception and also get a previous value from the cache
        }
        return 0D;
    }

    /**
     * Handle circuit open for retrieving for geo coords
     */
    @Override
    public Double retrieveCurrentTemperatureByGeoCoordinates(double lat, double lon) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            // Here we can treat the exception and also get a previous value from the cache
        }
        return 0D;
    }

}
