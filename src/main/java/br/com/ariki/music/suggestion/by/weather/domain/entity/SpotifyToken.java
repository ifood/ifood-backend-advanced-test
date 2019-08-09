package br.com.ariki.music.suggestion.by.weather.domain.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SpotifyToken {

	private String accessToken;
}
