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

import br.com.ariki.music.suggestion.by.weather.domain.entity.MusicStyle;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;
import br.com.ariki.music.suggestion.by.weather.gateway.SpotifyAPIGateway;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyPlaylistTest {
	
	@InjectMocks
	private SpotifyPlaylist usecase;
	
	@Mock
	private SpotifyAPIGateway gateway;
	
	@Test
	public void testExecute() {
		
		when(gateway.getPlaylist(any(), anyString())).thenReturn(Playlist.builder()
				.description("description")
				.trackInfo(Stream.of(TrackInfo.builder()
						.artistName("artistName")
						.href("href")
						.musicName("musicName")
						.build()).collect(Collectors.toList()))
				.build());
		
		Playlist actual = usecase.execute(MusicStyle.ROCK, "token");
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
	public void testExecuteRecover() {
		
		Playlist actual = usecase.executeRecover(MusicStyle.CLASSICAL, "");
		Playlist expected = null;
		
		assertEquals(expected, actual);
	}

}
