package br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainResponse {
	
	@JsonProperty("temp")
	private Double temperature;
}
