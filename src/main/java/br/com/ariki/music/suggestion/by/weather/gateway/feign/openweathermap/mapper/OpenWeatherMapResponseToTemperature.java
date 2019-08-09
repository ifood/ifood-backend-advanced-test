package br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.mapper;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.response.OpenWeatherMapResponse;

public class OpenWeatherMapResponseToTemperature {
	
	private OpenWeatherMapResponseToTemperature() { }
	
	public static Temperature to(OpenWeatherMapResponse response) {
		return Temperature.builder()
				.temperature(response.getMain().getTemperature())
				.build();
	}

}
