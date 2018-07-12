package ifood.service;

import ifood.component.OpenWeather;
import ifood.component.Spotify;
import ifood.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderService {

    private final OpenWeather openWeather;

    private final Spotify spotify;

    // TODO: ao final rever os métodos que deverão ser realmente expostos e os que deverão ser apagados

    public WeatherResponse getTemp(final String cityname) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(cityname);
        return new WeatherResponse(weatherData.getName(), weatherData.getMainTemp(), weatherData.getCountry());
    }

    public WeatherResponse getTemp(final Double lat, final Double lon) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(lat, lon);
        return new WeatherResponse(weatherData.getName(), weatherData.getMainTemp(), weatherData.getCountry());
    }

    public SpotifyPlaylistResponse getPlaylist(final TrackCategory category, final String country, final String token) {
        return spotify.getPlaylist(category, country, token);
    }

    public SpotifyTracksResponse getTracks(final String playlistId, final String token) {
        return spotify.getTracks(playlistId, token);
    }

    private WeatherResponse getWeather(final String cityname, final Double lat, final Double lon) {
        if (StringUtils.isNotBlank(cityname)) {
            return getTemp(cityname);
        }
        return getTemp(lat, lon);
    }

    private TrackCategory getCategory(final double temp) {
        if (temp > 30) {
            return TrackCategory.PARTY;
        } else if (temp >= 15) {
            return TrackCategory.POP;
        } else if (temp >= 10) {
            return TrackCategory.ROCK;
        }
        return TrackCategory.CLASSICAL;
    }

    private List<SpotifyTrackData> getTracksFromPlaylists(final SpotifyPlaylistResponse playlists, final String token) {
        final List<SpotifyTrackData> tracks = new ArrayList<>();

        playlists.getPlaylistIds().stream().forEach(pl -> {
            final SpotifyTracksResponse tracksResponse = spotify.getTracks(pl, token);
            tracks.addAll(tracksResponse.getTracks());
        });

        return tracks;
    }

    private List<SpotifyTrackData> getTracksByTemperature(final WeatherResponse weather, final String token) {
        final TrackCategory trackCategory = getCategory(weather.getTemp());
        final SpotifyPlaylistResponse playlistResponse = spotify.getPlaylist(trackCategory, weather.getCountry(), token);

        return getTracksFromPlaylists(playlistResponse, token);
    }

    public List<SpotifyTrackData> getTracksByLocation(final String cityname, final Double lat, final Double lon,
                                                      final String token) {
        return getTracksByTemperature(getWeather(cityname, lat, lon), token);
    }
}
