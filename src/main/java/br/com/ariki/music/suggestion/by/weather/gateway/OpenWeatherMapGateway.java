package br.com.ariki.music.suggestion.by.weather.gateway;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;

public interface OpenWeatherMapGateway {
	
	Temperature getWeatherByCity(String cityName);
	Temperature getWeatherByLatLon(String lat, String lon);

}
