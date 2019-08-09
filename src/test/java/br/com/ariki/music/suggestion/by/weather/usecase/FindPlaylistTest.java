package br.com.ariki.music.suggestion.by.weather.usecase;

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
import org.springframework.core.env.Environment;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;

@RunWith(MockitoJUnitRunner.class)
public class FindPlaylistTest {
	
	@InjectMocks
	private FindPlaylist usecase;
	
	@Mock
	private SpotifyPlaylist service;
	
	@Mock
	private Environment environment;
	
	@Test
	public void testExecuteByCityNameNULL() {
		
		when(environment.getProperty(anyString())).thenReturn("mensagem");
		
		Playlist actual = usecase.execute(null, "token");
		
		Playlist expected = Playlist.builder()
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
				.description("mensagem")
				.build();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testExecuteByCityNamePARTY() {
		
		when(service.execute(any(), anyString())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		when(environment.getProperty(anyString())).thenReturn("mensagem");
		
		Playlist actual = usecase.execute(Temperature.builder()
				.temperature(31D)
				.build(), "token");
		
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
	public void testExecuteByCityNamePOP() {
		
		when(service.execute(any(), anyString())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		when(environment.getProperty(anyString())).thenReturn("mensagem");
		
		Playlist actual = usecase.execute(Temperature.builder()
				.temperature(15D)
				.build(), "token");
		
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
	public void testExecuteByCityNameROCK() {
		
		when(service.execute(any(), anyString())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		when(environment.getProperty(anyString())).thenReturn("mensagem");
		
		Playlist actual = usecase.execute(Temperature.builder()
				.temperature(11D)
				.build(), "token");
		
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
	public void testExecuteByCityNameCLASSICAL() {
		
		when(service.execute(any(), anyString())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		when(environment.getProperty(anyString())).thenReturn("mensagem");
		
		Playlist actual = usecase.execute(Temperature.builder()
				.temperature(1D)
				.build(), "token");
		
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
