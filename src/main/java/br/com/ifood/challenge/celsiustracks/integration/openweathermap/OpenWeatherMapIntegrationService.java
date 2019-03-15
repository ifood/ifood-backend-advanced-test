package br.com.ifood.challenge.celsiustracks.integration.openweathermap;

import br.com.ifood.challenge.celsiustracks.domain.openweathermap.WeatherResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @see "https://openweathermap.org/current"
 */
@FeignClient(name = "openweathermap-integration", url = "${openweathermap.api.url}",
        fallback = OpenWeatherMapIntegrationFallbackServiceImpl.class)
public interface OpenWeatherMapIntegrationService {
    //@Cacheable (cachear o resultado por um tempo)
    @GetMapping(value = "/weather?units=metric", produces = MediaType.APPLICATION_JSON_VALUE)
    WeatherResource getWeatherByCity(@RequestParam(value = "q") final String cityName,
                                     @RequestParam(value = "APPID") final String appId);

    //@Cacheable (cachear o resultado por um tempo)
    @GetMapping(value = "/weather?units=metric", produces = MediaType.APPLICATION_JSON_VALUE)
    WeatherResource getWeatherByCoordinates(@RequestParam(value = "lat") final Double latitude,
                                            @RequestParam(value = "lon") final Double longitude,
                                            @RequestParam(value = "APPID") final String appId);
}
