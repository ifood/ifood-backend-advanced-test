package br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaylistResponse {

	private String name;
	private Tracks tracks;
}
