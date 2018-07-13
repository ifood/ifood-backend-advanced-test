package ifood.service;

import ifood.component.OpenWeather;
import ifood.component.Spotify;
import ifood.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderService {

    private static final int PARTY_MIN_TEMP = 30;
    private static final int POP_MIN_TEMP = 15;
    private static final int ROCK_MIN_TEMP = 10;

    private final OpenWeather openWeather;

    private final Spotify spotify;

    // TODO: ao final rever os métodos que deverão ser realmente expostos e os que deverão ser apagados

    private TrackCategoryEnum getCategory(final double temp) {
        if (temp > PARTY_MIN_TEMP) {
            return TrackCategoryEnum.PARTY;
        } else if (temp >= POP_MIN_TEMP) {
            return TrackCategoryEnum.POP;
        } else if (temp >= ROCK_MIN_TEMP) {
            return TrackCategoryEnum.ROCK;
        }
        return TrackCategoryEnum.CLASSICAL;
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
        final TrackCategoryEnum trackCategoryEnum = getCategory(weather.getTemp());
        final SpotifyPlaylistResponse playlistResponse = spotify.getPlaylist(trackCategoryEnum, weather.getCountry(), token);

        return getTracksFromPlaylists(playlistResponse, token);
    }

    public SpotifyPlaylistResponse getPlaylist(final TrackCategoryEnum category, final String country, final String token) {
        return spotify.getPlaylist(category, country, token);
    }

    public SpotifyTracksResponse getTracks(final String playlistId, final String token) {
        return spotify.getTracks(playlistId, token);
    }

    public WeatherResponse getWeather(final String cityname, final Double lat, final Double lon) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(cityname, lat, lon);
        return new WeatherResponse(weatherData.getCityname(), weatherData.getMainTemp(), weatherData.getCountry());
    }

    public List<SpotifyTrackData> getTracksByLocation(final String cityname, final Double lat, final Double lon,
                                                      final String token) {
        return getTracksByTemperature(getWeather(cityname, lat, lon), token);
    }
}
