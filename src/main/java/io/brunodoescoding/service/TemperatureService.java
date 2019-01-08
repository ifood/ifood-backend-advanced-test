package io.brunodoescoding.service;

import org.springframework.web.client.HttpClientErrorException;

public interface TemperatureService {
    public double findByCity(String city) throws HttpClientErrorException;
    public double findByCoordinates(String lat, String lon) throws HttpClientErrorException;
}
