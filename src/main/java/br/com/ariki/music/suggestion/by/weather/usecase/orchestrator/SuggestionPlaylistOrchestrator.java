package br.com.ariki.music.suggestion.by.weather.usecase.orchestrator;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;

public interface SuggestionPlaylistOrchestrator {
	Playlist executeByCityName(String cityName);
	Playlist executeByLatLon(String lat, String lon);
}
