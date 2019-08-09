package br.com.ariki.music.suggestion.by.weather.gateway.controller.response;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaylistResponse {
	
	private String descricao;
	private List<TrackInfoResponse> trackResponses;

}
