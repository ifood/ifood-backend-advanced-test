package com.ifood.backend.advancedtest.spotify.usecases;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.openWeather.usecases.GetWeatherByCityUseCase;
import com.ifood.backend.advancedtest.spotify.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetSongsByCityUseCase {

    @Autowired
    private GetWeatherByCityUseCase getWeatherByCityUseCase;

    @Autowired
    private GetSongsByWeatherUseCase getSongsByWeatherUseCase;

    public List<Track> execute(String cityName) {
        Weather weather = getWeatherByCityUseCase.execute(cityName);
        return getSongsByWeatherUseCase.execute(weather);
    }
}
