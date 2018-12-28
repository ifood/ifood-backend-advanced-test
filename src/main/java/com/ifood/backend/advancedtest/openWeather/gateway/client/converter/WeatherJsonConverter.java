package com.ifood.backend.advancedtest.openWeather.gateway.client.converter;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.openWeather.gateway.client.json.WeatherJson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WeatherJsonConverter implements Converter<WeatherJson, Weather> {

    @Override
    public Weather convert(WeatherJson weatherJson) {
        return Weather.builder()
                .temperature(weatherJson.getWeatherMainInfoJson().getTemperature())
                .build();
    }
}
