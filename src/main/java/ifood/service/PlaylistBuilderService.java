package ifood.service;

import ifood.component.OpenWeather;
import ifood.component.Spotify;
import ifood.model.*;
import ifood.utils.CategoryTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderService {

    private final OpenWeather openWeather;

    private final Spotify spotify;

    private List<SpotifyTrackData> getTracksFromPlaylists(final SpotifyPlaylistResponse playlists, final String token) {
        final List<SpotifyTrackData> tracks = new ArrayList<>();

        playlists.getPlaylistIds().stream().forEach(pl -> {
            final SpotifyTracksResponse tracksResponse = spotify.getTracks(pl, token);
            tracks.addAll(tracksResponse.getTracks());
        });

        return tracks;
    }

    private List<SpotifyTrackData> getTracksByTemperature(final WeatherResponse weather, final String token) {
        final TrackCategoryEnum trackCategoryEnum = CategoryTemp.getCategoryByTemp(weather.getTemp());
        final SpotifyPlaylistResponse playlistResponse = spotify.getPlaylist(trackCategoryEnum, weather.getCountry(), token);

        return getTracksFromPlaylists(playlistResponse, token);
    }

    private WeatherResponse getWeather(final String cityname, final Double lat, final Double lon) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(cityname, lat, lon);
        return new WeatherResponse(weatherData.getCityname(), weatherData.getMainTemp(), weatherData.getCountry());
    }

    public List<SpotifyTrackData> getTracksByLocation(final String cityname, final Double lat, final Double lon,
                                                      final String token) {
        return this.getTracksByTemperature(this.getWeather(cityname, lat, lon), token);
    }
}
