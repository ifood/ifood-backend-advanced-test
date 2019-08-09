package br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artists {
	
	private String name;

}
