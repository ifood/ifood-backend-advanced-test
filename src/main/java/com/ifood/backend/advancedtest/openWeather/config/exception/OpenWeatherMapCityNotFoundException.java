package com.ifood.backend.advancedtest.openWeather.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class OpenWeatherMapCityNotFoundException extends HttpClientErrorException {

    public OpenWeatherMapCityNotFoundException(HttpStatus status) {
        super(status);
    }
}
