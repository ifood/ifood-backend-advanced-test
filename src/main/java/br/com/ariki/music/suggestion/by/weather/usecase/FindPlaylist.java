package br.com.ariki.music.suggestion.by.weather.usecase;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.ariki.music.suggestion.by.weather.domain.entity.MusicStyle;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;

@Component
public class FindPlaylist {

	private SpotifyPlaylist service;
	private Environment environment;
	
	public FindPlaylist(SpotifyPlaylist service, Environment environment) {
		this.service = service;
		this.environment = environment;
	}

	public Playlist execute(Temperature temperature, String token) {
		
		Playlist playlist = null;
		if(temperature == null) {
			playlist = this.getLocalSuggestion(environment.getProperty("openweathermap.error"));
		}else if(temperature.getTemperature() > 30) {
			playlist = service.execute(MusicStyle.PARTY, token);
		} else if (temperature.getTemperature() >= 15) {
			playlist = service.execute(MusicStyle.POP, token);
		} else if(temperature.getTemperature() > 10) {
			playlist = service.execute(MusicStyle.ROCK, token);
		} else {
			playlist = service.execute(MusicStyle.CLASSICAL, token);
		}
		
		Optional<Playlist> optional = Optional.ofNullable(playlist);		
		return optional.orElse(this.getLocalSuggestion(environment.getProperty("spotify.error")));
	}
	
	
	private Playlist getLocalSuggestion(String description) {
		return Playlist.builder()
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("Dream Theater")
						.musicName("Pull Me Under")
						.build(),
						TrackInfo.builder()
						.artistName("Liquid Tension Experiment")
						.musicName("Acid Rain")
						.build(),
						TrackInfo.builder()
						.artistName("Dream Theater")
						.musicName("Honor Thy Father")
						.build(),
						TrackInfo.builder()
						.artistName("Symphony X")
						.musicName("Evolution")
						.build()).collect(Collectors.toList()))
				.description(description)
				.build();
	}
	
}
