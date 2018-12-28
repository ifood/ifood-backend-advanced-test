package com.ifood.backend.advancedtest.openWeather.usecases;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.openWeather.gateway.OpenWeatherGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetWeatherByCoordinatesUseCase {

    @Autowired
    private OpenWeatherGateway openWeatherGateway;

    public Weather execute(float latitude, float longitude) {
        return openWeatherGateway.getCondition(latitude, longitude);
    }
}
