package br.com.ariki.music.suggestion.by.weather.usecase.orchestrator.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;
import br.com.ariki.music.suggestion.by.weather.usecase.FindPlaylist;
import br.com.ariki.music.suggestion.by.weather.usecase.SearchWeatherByCity;
import br.com.ariki.music.suggestion.by.weather.usecase.SearchWeatherByLatLon;
import br.com.ariki.music.suggestion.by.weather.usecase.SpotifyGetToken;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionPlaylistOrchestratorImplTest {
	
	@InjectMocks
	private SuggestionPlaylistOrchestratorImpl orchestrator;
	
	@Mock
	private SearchWeatherByCity serviceByCity;
	
	@Mock
	private SearchWeatherByLatLon serviceByLatLon;
	
	@Mock
	private SpotifyGetToken serviceSpotifyToken;
	
	@Mock
	private FindPlaylist findService;

	@Test
	public void testExecuteByCityName() {
		
		when(serviceByCity.execute(anyString())).thenReturn(Temperature.builder()
				.build());
		when(serviceSpotifyToken.execute()).thenReturn("token");
		when(findService.execute(any(), any())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		Playlist actual = orchestrator.executeByCityName("New York");
		Playlist expected = Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExecuteByLatLon() {
		
		when(serviceByLatLon.execute(anyString(), anyString())).thenReturn(Temperature.builder()
				.build());
		when(serviceSpotifyToken.execute()).thenReturn("token");
		when(findService.execute(any(), any())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		Playlist actual = orchestrator.executeByLatLon("12", "130");
		Playlist expected = Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build();
		
		assertEquals(expected, actual);
		
		
	}
	
}
