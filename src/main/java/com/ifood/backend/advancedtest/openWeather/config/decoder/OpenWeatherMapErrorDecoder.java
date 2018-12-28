package com.ifood.backend.advancedtest.openWeather.config.decoder;

import com.ifood.backend.advancedtest.openWeather.config.exception.OpenWeatherMapCityNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class OpenWeatherMapErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        if (status == HttpStatus.NOT_FOUND) {
            return new OpenWeatherMapCityNotFoundException(status);
        }

        return FeignException.errorStatus(s, response);
    }
}
