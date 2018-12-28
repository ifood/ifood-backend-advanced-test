package com.ifood.backend.advancedtest.spotify.usecases;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.spotify.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryByWeatherUseCase {

    public Category execute(Weather weather) {
        Category category;

        if (weather.getTemperature() > 30) {
            category = Category.PARTY;
        } else if ((weather.getTemperature() >= 15) && (weather.getTemperature() <= 30)) {
            category = Category.POP;
        } else if ((weather.getTemperature() >= 10) && (weather.getTemperature() <= 14)) {
            category = Category.ROCK;
        } else {
            category = Category.CLASSICAL;
        }

        return category;
    }
}
