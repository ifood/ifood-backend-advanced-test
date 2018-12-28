package com.ifood.backend.advancedtest.openWeather.gateway;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.openWeather.gateway.client.OpenWeatherFeignClient;
import com.ifood.backend.advancedtest.openWeather.gateway.client.converter.WeatherJsonConverter;
import com.ifood.backend.advancedtest.openWeather.gateway.client.json.WeatherJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherGateway {

    @Autowired
    private OpenWeatherFeignClient openWeatherFeignClient;

    @Autowired
    private WeatherJsonConverter weatherJsonConverter;

    public Weather getCondition(String cityName) {
        WeatherJson weatherJson = openWeatherFeignClient.getCondition(cityName);
        return weatherJsonConverter.convert(weatherJson);
    }

    public Weather getCondition(float latitude, float longitude) {
        WeatherJson weatherJson =  openWeatherFeignClient.getCondition(latitude, longitude);
        return weatherJsonConverter.convert(weatherJson);
    }
}
