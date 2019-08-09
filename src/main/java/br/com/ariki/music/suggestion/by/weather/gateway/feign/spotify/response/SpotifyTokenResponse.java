package br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyTokenResponse {

	@JsonProperty("access_token")
	private String accessToken;
	
}
