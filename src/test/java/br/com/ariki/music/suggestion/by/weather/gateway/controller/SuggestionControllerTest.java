package br.com.ariki.music.suggestion.by.weather.gateway.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;
import br.com.ariki.music.suggestion.by.weather.gateway.controller.response.PlaylistResponse;
import br.com.ariki.music.suggestion.by.weather.gateway.controller.response.TrackInfoResponse;
import br.com.ariki.music.suggestion.by.weather.usecase.orchestrator.impl.SuggestionPlaylistOrchestratorImpl;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionControllerTest {
	
	@InjectMocks
	private SuggestionController controller;
	
	@Mock
	private SuggestionPlaylistOrchestratorImpl orchestrator;
	
	@Test
	public void testGetSuggestionCityOk() {

		when(orchestrator.executeByCityName(anyString())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		ResponseEntity<PlaylistResponse> actual = controller.getSuggestion("New York");
		ResponseEntity<PlaylistResponse> expected = ResponseEntity.ok(PlaylistResponse.builder()
				.descricao("description")
				.trackResponses(Stream.of(TrackInfoResponse.builder()
						.artista("artistName")
						.href("href")
						.musica("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSuggestionCityNotOk() {

		when(orchestrator.executeByCityName(anyString())).thenReturn(null);
		
		ResponseEntity<PlaylistResponse> actual = controller.getSuggestion("New York");
		ResponseEntity<PlaylistResponse> expected = ResponseEntity.noContent().build();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSuggestionLonLatOk() {

		when(orchestrator.executeByCityName(anyString())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		ResponseEntity<PlaylistResponse> actual = controller.getSuggestion("10", "130");
		ResponseEntity<PlaylistResponse> expected = ResponseEntity.ok(PlaylistResponse.builder()
				.descricao("description")
				.trackResponses(Stream.of(TrackInfoResponse.builder()
						.artista("artistName")
						.href("href")
						.musica("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSuggestionLonLatNotOk() {

		when(orchestrator.executeByCityName(anyString())).thenReturn(null);
		
		ResponseEntity<PlaylistResponse> actual = controller.getSuggestion("10", "130");
		ResponseEntity<PlaylistResponse> expected = ResponseEntity.noContent().build();
		
		assertEquals(expected, actual);
	}
}
