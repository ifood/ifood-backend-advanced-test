package ifood.service;

import ifood.component.OpenWeather;
import ifood.component.Spotify;
import ifood.exception.SpotifyInvalidDataException;
import ifood.model.*;
import ifood.utils.CategoryTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderService {

    private final OpenWeather openWeather;

    private final Spotify spotify;

    private static int getRandom(final int max) {
        return max == 0 ? 0 : ThreadLocalRandom.current().nextInt(0, max);
    }

    private static String getOnePlaylist(final SpotifyPlaylistResponse playlists) {
        if (playlists.getPlaylistIds().size() == 0) {
            throw new SpotifyInvalidDataException(new String[] { "Nenhuma playlist!" });
        }
        return playlists.getPlaylistIds().get(getRandom(playlists.getPlaylistIds().size()-1));
    }

    private List<SpotifyTrackData> getTracksFromRandomPlaylists(final SpotifyPlaylistResponse playlists, final String token) {
        final SpotifyTracksResponse tracksResponse = spotify.getTracks(getOnePlaylist(playlists), token);
        return tracksResponse.getTracks();
    }

    private List<SpotifyTrackData> getTracksByTemperature(final WeatherResponse weather, final String token) {
        final TrackCategoryEnum trackCategoryEnum = CategoryTemp.getCategoryByTemp(weather.getTemp());
        final SpotifyPlaylistResponse playlistResponse = spotify.getPlaylist(trackCategoryEnum, weather.getCountry(), token);

        return getTracksFromRandomPlaylists(playlistResponse, token);
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
