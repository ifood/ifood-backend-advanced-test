package br.com.imood.integration.service;

import br.com.imood.domain.dto.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * For more information see <a href="https://openweathermap.org">OpenWeatherAPI</a>
 */
@FeignClient(name = "openWeather", url = "${openweather.url}")
public interface OpenWeatherMapService {

    /**
     * Default params for many requests
     */
    String DEFAULT_PARAMS = "&units=metric&appid=${openweather.apiKey}";

    /**
     * Retrieves the city weather
     * @param city cityName
     * @return city weather
     */
    @GetMapping(value = "/weather?q={city}" + DEFAULT_PARAMS)
    WeatherDTO getWeather(@PathVariable("city") String city);

    /**
     * Retrieves the city weather
     * @param lat city latitude
     * @param lon city longitude
     * @return city weather
     */
    @GetMapping(value = "/weather?lat={lat}&lon={lon}" + DEFAULT_PARAMS)
    WeatherDTO getWeather(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon);

}