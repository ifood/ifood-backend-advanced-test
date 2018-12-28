package com.ifood.backend.advancedtest.spotify.usecases;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.openWeather.usecases.GetWeatherByCoordinatesUseCase;
import com.ifood.backend.advancedtest.spotify.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetSongsByLatLongUseCase {

    @Autowired
    private GetWeatherByCoordinatesUseCase getWeatherByCoordinatesUseCase;

    @Autowired
    private GetSongsByWeatherUseCase getSongsByWeatherUseCase;

    public List<Track> execute(float latitude, float longitude) {
        Weather weather = getWeatherByCoordinatesUseCase.execute(latitude, longitude);
        return getSongsByWeatherUseCase.execute(weather);
    }
}
