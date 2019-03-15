package br.com.ifood.challenge.celsiustracks.integration.openweathermap;

import br.com.ifood.challenge.celsiustracks.domain.openweathermap.WeatherResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenWeatherMapIntegrationFallbackServiceImpl implements OpenWeatherMapIntegrationService {

    @Override
    public WeatherResource getWeatherByCity(final String cityName, final String appId) {
        log.warn("Fallback for OpenWeatherMapIntegrationService.getWeatherByCity");
        return new WeatherResource(); //TODO implementar fallback
    }

    @Override
    public WeatherResource getWeatherByCoordinates(final Double latitude, final Double longitude, final String appId) {
        log.warn("Fallback for OpenWeatherMapIntegrationService.getWeatherByCoordinates");
        return new WeatherResource(); //TODO implementar fallback
    }

}
