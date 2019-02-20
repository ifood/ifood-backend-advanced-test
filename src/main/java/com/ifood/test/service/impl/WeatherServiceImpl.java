package com.ifood.test.service.impl;

import com.ifood.test.dto.Coord;
import com.ifood.test.dto.Place;
import com.ifood.test.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Place getWeatherPlace(String place){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Place> response = restTemplate.exchange(
                "https://api.openweathermap.org/data/2.5/weather?q={place}&units=metric&appid=81ef5182095ba290ebde1492b963fb36",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Place>(){},place);
        return response.getBody();
    }

    @Override
    public Place getWeatherCoord(Coord coord){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Place> response = restTemplate.exchange(
                "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&appid=81ef5182095ba290ebde1492b963fb36",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Place>(){},coord.getLatitude(),coord.getLongitude());
        return response.getBody();
    }



}
