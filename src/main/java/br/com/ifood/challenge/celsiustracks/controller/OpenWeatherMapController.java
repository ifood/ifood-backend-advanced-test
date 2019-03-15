package br.com.ifood.challenge.celsiustracks.controller;


import br.com.ifood.challenge.celsiustracks.domain.openweathermap.WeatherResource;
import br.com.ifood.challenge.celsiustracks.integration.openweathermap.OpenWeatherMapIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.join;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OpenWeatherMapController {

	@Value("${openweathermap.app.id}")
	private String appId;

	private final OpenWeatherMapIntegrationService openWeatherMapIntegrationService;

	@GetMapping(value = "/cities/{city}/weather")
	public ResponseEntity<?> getWeatherByCity(@PathVariable(value = "city")final String cityName,
			@RequestParam(value = "country", required = false) final String country) {
		final WeatherResource weatherResource =
				openWeatherMapIntegrationService.getWeatherByCity(join(",", cityName, country), appId);
		return ResponseEntity.ok(weatherResource);
	}

	@GetMapping(value = "/coordinates/latitude/{latitude}/longitude/{longitude}/weather")
	public ResponseEntity<?> getWeatherByCoordinates(@PathVariable(value = "latitude")final Double latitude,
													 @PathVariable(value = "longitude")final Double longitude) {
		final WeatherResource weatherResource = openWeatherMapIntegrationService.getWeatherByCoordinates(latitude, longitude, appId);
		return ResponseEntity.ok(weatherResource);
	}
}
