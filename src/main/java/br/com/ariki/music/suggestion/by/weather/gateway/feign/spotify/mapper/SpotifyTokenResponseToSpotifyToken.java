package br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.mapper;

import br.com.ariki.music.suggestion.by.weather.domain.entity.SpotifyToken;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.SpotifyTokenResponse;

public class SpotifyTokenResponseToSpotifyToken {
	
	private SpotifyTokenResponseToSpotifyToken() { }
	
	public static SpotifyToken to(SpotifyTokenResponse response) {
		return SpotifyToken.builder()
				.accessToken(response.getAccessToken())
				.build();
	}

}
