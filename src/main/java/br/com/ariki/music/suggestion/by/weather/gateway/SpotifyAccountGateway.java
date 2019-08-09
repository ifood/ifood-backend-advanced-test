package br.com.ariki.music.suggestion.by.weather.gateway;

import br.com.ariki.music.suggestion.by.weather.domain.entity.SpotifyToken;

public interface SpotifyAccountGateway {
	
	SpotifyToken getToken();
	
}
