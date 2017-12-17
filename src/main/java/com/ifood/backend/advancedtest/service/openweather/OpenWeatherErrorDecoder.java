package com.ifood.backend.advancedtest.service.openweather;

import com.ifood.backend.advancedtest.exception.PlaylistClientException;
import com.ifood.backend.advancedtest.exception.PlaylistServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import static feign.FeignException.errorStatus;

public class OpenWeatherErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus httpStatus = org.springframework.http.HttpStatus.valueOf(response.status());

        if (response.status() >= 400 && response.status() <= 499) {
            return new PlaylistClientException(httpStatus);
        }
        if (response.status() >= 500 && response.status() <= 599) {
            return new PlaylistServerException(httpStatus);
        }
        return errorStatus(methodKey, response);
    }
}
