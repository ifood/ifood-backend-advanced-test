package ifood.service;

import ifood.component.OpenWeather;
import ifood.dto.OpenWeatherResponse;
import ifood.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderService {

    private final OpenWeather openWeather;

    public WeatherResponse getTemp(final String cityname) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(cityname);
        return new WeatherResponse(weatherData.getName(), weatherData.getMainTemp());
    }

    public WeatherResponse getTemp(final Double lat, final Double lon) {
        final OpenWeatherResponse weatherData = openWeather.getCityTemp(lat, lon);
        return new WeatherResponse(weatherData.getName(), weatherData.getMainTemp());
    }
}
