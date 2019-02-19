package com.ifood.test.service.impl;

import com.ifood.test.dto.Lugar;
import com.ifood.test.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ClimaServiceImpl implements ClimaService {

    @Autowired
    private RestTemplate restTemplate;

    public Lugar obtieneClimaDelLugar(String ciudad){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Lugar> response = restTemplate.exchange(
                "https://api.openweathermap.org/data/2.5/weather?q={ciudad}&units=metric&appid=81ef5182095ba290ebde1492b963fb36",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Lugar>(){},ciudad);
        return response.getBody();
    }


}
