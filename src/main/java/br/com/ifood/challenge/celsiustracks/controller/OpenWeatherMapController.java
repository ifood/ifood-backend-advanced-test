package br.com.ifood.challenge.celsiustracks.controller;


import br.com.ifood.challenge.celsiustracks.domain.openweathermap.WeatherResource;
import br.com.ifood.challenge.celsiustracks.integration.openweathermap.OpenWeatherMapIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OpenWeatherMapController {

	@Value("${openweathermap.appId}")
	private String appId;

	private final OpenWeatherMapIntegrationService openWeatherMapIntegrationService;

	@GetMapping(value = "/cities/{city}/weather")
	public ResponseEntity<?> getWeatherByCity(@PathVariable(value = "city")final String cityName,
			@RequestParam(value = "country", required = false) final String country) {
		final String city = cityName + (country != null ? ("," + country) : "");
		log.info("Search weather for city: {}", city);
		final WeatherResource weatherResource = openWeatherMapIntegrationService.getWeatherByCity(city, appId);
		log.info("Weather found: {} C", weatherResource.getCurrentTemperature());

		return ResponseEntity.ok(weatherResource);
	}

	@GetMapping(value = "/coordinates/latitude/{latitude}/longitude/{longitude}/weather")
	public ResponseEntity<?> getWeatherByCoordinates(@PathVariable(value = "latitude")final Double latitude,
													 @PathVariable(value = "longitude")final Double longitude) {
		log.info("Search weather for coordinates(lat/lon): {}/{}", latitude, longitude);
		final WeatherResource weatherResource = openWeatherMapIntegrationService.getWeatherByCoordinates(latitude, longitude, appId);
		log.info("Weather found: {} C", weatherResource.getCurrentTemperature());
		return ResponseEntity.ok(weatherResource);
	}
}
