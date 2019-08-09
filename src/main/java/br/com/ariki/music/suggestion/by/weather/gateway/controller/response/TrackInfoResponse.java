package br.com.ariki.music.suggestion.by.weather.gateway.controller.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TrackInfoResponse {

	private String artista;
	private String musica;
	private String href;
}
