package com.ifood.backend.advancedtest.openWeather.usecases;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.openWeather.gateway.OpenWeatherGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetWeatherByCityUseCase {

    @Autowired
    private OpenWeatherGateway openWeatherGateway;

    public Weather execute(String cityName) {
        return openWeatherGateway.getCondition(cityName);
    }
}
