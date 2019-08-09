package br.com.ariki.music.suggestion.by.weather.usecase.orchestrator.impl;

import org.springframework.stereotype.Component;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.usecase.FindPlaylist;
import br.com.ariki.music.suggestion.by.weather.usecase.SearchWeatherByCity;
import br.com.ariki.music.suggestion.by.weather.usecase.SearchWeatherByLatLon;
import br.com.ariki.music.suggestion.by.weather.usecase.SpotifyGetToken;
import br.com.ariki.music.suggestion.by.weather.usecase.orchestrator.SuggestionPlaylistOrchestrator;

@Component
public class SuggestionPlaylistOrchestratorImpl implements SuggestionPlaylistOrchestrator {

	private SearchWeatherByCity serviceByCity;
	private SearchWeatherByLatLon serviceByLatLon;
	private SpotifyGetToken serviceSpotifyToken;
	private FindPlaylist findService;

	public SuggestionPlaylistOrchestratorImpl(
			SearchWeatherByCity serviceByCity, 
			SpotifyGetToken serviceSpotifyToken, 
			FindPlaylist findService, 
			SearchWeatherByLatLon serviceByLatLon) {
		this.serviceByCity = serviceByCity;
		this.serviceSpotifyToken = serviceSpotifyToken;
		this.findService= findService;
		this.serviceByLatLon = serviceByLatLon;
	}

	public Playlist executeByCityName(String cityName) {
		Temperature temperature = serviceByCity.execute(cityName);
		String token = serviceSpotifyToken.execute();
		return findService.execute(temperature, token);
	}

	@Override
	public Playlist executeByLatLon(String lat, String lon) {
		Temperature temperature = serviceByLatLon.execute(lat, lon);
		String token = serviceSpotifyToken.execute();
		return findService.execute(temperature, token);
	}

}
