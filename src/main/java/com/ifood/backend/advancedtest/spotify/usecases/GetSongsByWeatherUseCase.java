package com.ifood.backend.advancedtest.spotify.usecases;

import com.ifood.backend.advancedtest.openWeather.domain.Weather;
import com.ifood.backend.advancedtest.spotify.domain.Category;
import com.ifood.backend.advancedtest.spotify.domain.Playlist;
import com.ifood.backend.advancedtest.spotify.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetSongsByWeatherUseCase {

    @Autowired
    private GetCategoryByWeatherUseCase getCategoryByWeatherUseCase;

    @Autowired
    private GetPlaylistByCategoryUseCase getPlaylistByCategoryUseCase;

    @Autowired
    private GetSongsByPlaylistUseCase getSongsByPlaylistUseCase;

    public List<Track> execute(Weather weather) {

        Category category = getCategoryByWeatherUseCase.execute(weather);
        Playlist playList = getPlaylistByCategoryUseCase.execute(category.getCategoryId());
        List<Track> tracks = getSongsByPlaylistUseCase.execute(playList);

        return tracks;
    }
}
