package br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.mapper;

import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.Track;

public class TrackToTrackInfo {

	private TrackToTrackInfo() { }
	
	public static TrackInfo to(Track response) {
		return TrackInfo.builder()
				.artistName(response.getArtists().get(0).getName())
				.musicName(response.getName())
				.href(response.getHref())
				.build();
	}
}
