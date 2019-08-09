package br.com.ariki.music.suggestion.by.weather.usecase;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.ariki.music.suggestion.by.weather.domain.entity.MusicStyle;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.gateway.SpotifyAPIGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpotifyPlaylist {
	
	private static final String BEARER = "Bearer "; 
	
	private SpotifyAPIGateway gateway;

	public SpotifyPlaylist(SpotifyAPIGateway gateway) {
		this.gateway = gateway;
	}
	
	@HystrixCommand(fallbackMethod = "executeRecover")
	public Playlist execute(MusicStyle musicStyle, String token) {
		log.debug("Init execute");
		Playlist playlist = gateway.getPlaylist(musicStyle, BEARER + token);
		return playlist;
	}
	
	public Playlist executeRecover(MusicStyle musicStyle, String token) {
		log.debug("Init executeRecover");
		log.error("Hystrix called executeRecover because playlist service is not working");
		return null;
	}

}
