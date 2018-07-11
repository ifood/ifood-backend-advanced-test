package ifood.service;

import ifood.component.OpenWeather;
import ifood.component.Spotify;
import ifood.dto.OpenWeatherResponse;
import ifood.dto.SpotifyPlaylistResponse;
import ifood.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderService {

    private final OpenWeather openWeather;

    private final Spotify spotify;

    public WeatherResponse getTemp(final String cityname) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(cityname);
        return new WeatherResponse(weatherData.getName(), weatherData.getMainTemp(), weatherData.getCountry());
    }

    public WeatherResponse getTemp(final Double lat, final Double lon) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(lat, lon);
        return new WeatherResponse(weatherData.getName(), weatherData.getMainTemp(), weatherData.getCountry());
    }

    public SpotifyPlaylistResponse getPlaylist(final String category, final String country, final String token) {
        return spotify.getPlaylist(category, country, token);
    }
}
