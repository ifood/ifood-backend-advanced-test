package br.com.ariki.music.suggestion.by.weather.usecase;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.gateway.OpenWeatherMapGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchWeatherByCity {

	
	private OpenWeatherMapGateway gateway;

	public SearchWeatherByCity(OpenWeatherMapGateway gateway) {
		this.gateway = gateway;
	}
	
	@HystrixCommand(fallbackMethod = "executeRecover")
	public Temperature execute(String cityName) {
		return gateway.getWeatherByCity(cityName);
	}
	
	public Temperature executeRecover(String cityName, Throwable ex) {
		log.error("Hystrix called executeRecover for param {}", ex.getMessage());
		return null;
	}
}
