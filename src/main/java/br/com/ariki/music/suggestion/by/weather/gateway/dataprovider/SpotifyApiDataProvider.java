package br.com.ariki.music.suggestion.by.weather.gateway.dataprovider;

import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.ariki.music.suggestion.by.weather.domain.entity.MusicStyle;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.gateway.SpotifyAPIGateway;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.FeignSpotifyAPI;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.mapper.TrackToTrackInfo;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.PlaylistResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SpotifyApiDataProvider implements SpotifyAPIGateway {
	
	private FeignSpotifyAPI client;
	private Environment environment;
	
	public SpotifyApiDataProvider(FeignSpotifyAPI client, Environment environment) {
		this.client = client;
		this.environment = environment;
	}
	
	public Playlist getPlaylist(MusicStyle musicStyle, String token) {
		log.debug("Init getToken");
		PlaylistResponse response = client.getPlaylist(token, environment.getProperty("spotify.feign.playlist." + musicStyle.toString().toLowerCase() + "-id"));
		return Playlist.builder()
				.description(response.getName())
				.trackInfo(response.getTracks().getItems().stream()
						.map(item -> TrackToTrackInfo.to(item.getTrack()))
						.collect(Collectors.toList()))
			.build();
	}

}
