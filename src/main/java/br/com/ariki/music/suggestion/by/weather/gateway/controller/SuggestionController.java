package br.com.ariki.music.suggestion.by.weather.gateway.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.gateway.controller.mapper.PlaylistToPlaylistResponse;
import br.com.ariki.music.suggestion.by.weather.gateway.controller.response.PlaylistResponse;
import br.com.ariki.music.suggestion.by.weather.usecase.orchestrator.SuggestionPlaylistOrchestrator;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/suggestion")
@Slf4j
public class SuggestionController {
	
	private SuggestionPlaylistOrchestrator orchestrator;
	
	public SuggestionController(SuggestionPlaylistOrchestrator orchestrator) {
		this.orchestrator = orchestrator;
	}
	
	@GetMapping(path = "/bycity", params = "q")
	public ResponseEntity<PlaylistResponse> getSuggestionByCity(@RequestParam(name = "q") String cityName ) {
		log.debug("Init getSuggestion");
		
		Optional<Playlist> optional = Optional.ofNullable(orchestrator.executeByCityName(cityName));
		
		return optional.map(playlist -> ResponseEntity.ok(PlaylistToPlaylistResponse.to(playlist)))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping(path = "/bylatlon", params = {"lat", "lon"})
	public ResponseEntity<PlaylistResponse> getSuggestionByLonLat(
			@RequestParam(name = "lat") String lat, 
			@RequestParam(name = "lon") String lon) {
		
		Optional<Playlist> optional = Optional.ofNullable(orchestrator.executeByLatLon(lat, lon));
		
		return optional.map(playlist -> ResponseEntity.ok(PlaylistToPlaylistResponse.to(playlist)))
				.orElse(ResponseEntity.noContent().build());
	}

}
