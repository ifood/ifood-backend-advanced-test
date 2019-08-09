package br.com.ariki.music.suggestion.by.weather.gateway.controller.mapper;

import java.util.stream.Collectors;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.gateway.controller.response.PlaylistResponse;

public class PlaylistToPlaylistResponse {
	
	public static PlaylistResponse to(Playlist playlist) {
		return PlaylistResponse.builder()
				.descricao(playlist.getDescription())
				.trackResponses(playlist.getTrackInfo().stream()
						.map(TrackInfoToTrackInfoResponse::to).collect(Collectors.toList()))
				.build();
	}

}
