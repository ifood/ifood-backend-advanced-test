package br.com.ariki.music.suggestion.by.weather.gateway.controller.mapper;

import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;
import br.com.ariki.music.suggestion.by.weather.gateway.controller.response.TrackInfoResponse;

public class TrackInfoToTrackInfoResponse {
	
	public static TrackInfoResponse to(TrackInfo trackInfo) {
		return TrackInfoResponse.builder()
				.artista(trackInfo.getArtistName())
				.musica(trackInfo.getMusicName())
				.href(trackInfo.getHref())
				.build();
	}

}
