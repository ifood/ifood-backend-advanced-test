package br.com.ariki.music.suggestion.by.weather.gateway.dataprovider;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.gateway.OpenWeatherMapGateway;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.FeignOpenWeatherMapAPI;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.mapper.OpenWeatherMapResponseToTemperature;

@Component
public class OpenWeatherMapDataProvider implements OpenWeatherMapGateway {
	
	private FeignOpenWeatherMapAPI client;
	private Environment environment;

	public OpenWeatherMapDataProvider(FeignOpenWeatherMapAPI client, Environment environment) {
		this.client = client;
		this.environment = environment;
		
	}
	
	public Temperature getWeatherByCity(String cityName) {
		String appid = environment.getProperty("openweathermap.appid");
		String units = environment.getProperty("openweathermap.units");
		
		return OpenWeatherMapResponseToTemperature.to(client.getWeatherByCity(cityName, appid, units));
	}

	@Override
	public Temperature getWeatherByLatLon(String lat, String lon) {
		String appid = environment.getProperty("openweathermap.appid");
		String units = environment.getProperty("openweathermap.units");
		return OpenWeatherMapResponseToTemperature.to(client.getWeatherByLatLon(lat, lon, appid, units));
	}

}
