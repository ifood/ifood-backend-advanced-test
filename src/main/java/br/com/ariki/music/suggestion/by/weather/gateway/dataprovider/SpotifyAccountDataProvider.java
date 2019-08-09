package br.com.ariki.music.suggestion.by.weather.gateway.dataprovider;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.ariki.music.suggestion.by.weather.domain.entity.SpotifyToken;
import br.com.ariki.music.suggestion.by.weather.gateway.SpotifyAccountGateway;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.FeignSpotifyAccountAPI;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.mapper.SpotifyTokenResponseToSpotifyToken;

@Component
public class SpotifyAccountDataProvider implements SpotifyAccountGateway {
	
	private FeignSpotifyAccountAPI client;
	
	public SpotifyAccountDataProvider(FeignSpotifyAccountAPI client) {
		this.client = client;
	}
	
	public SpotifyToken getToken() {
		String token = "Basic MTUyYWYzODllYWZjNDc5NmI3NDg1MmQ5MWJiYzY0YTQ6ZDM0M2ExMTk5NmQwNGEyMjlmZWNmNTk4ZTk5NmIzYTc=";
		MultiValueMap<String, String> bodyRequestMap = new LinkedMultiValueMap<>();
		bodyRequestMap.add("grant_type", "client_credentials");
		return SpotifyTokenResponseToSpotifyToken.to(client.getToken(token, bodyRequestMap));
	}

}
