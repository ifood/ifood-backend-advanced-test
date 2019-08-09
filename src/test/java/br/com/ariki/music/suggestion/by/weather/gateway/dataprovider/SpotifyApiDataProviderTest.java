package br.com.ariki.music.suggestion.by.weather.gateway.dataprovider;

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
import org.springframework.core.env.Environment;

import br.com.ariki.music.suggestion.by.weather.domain.entity.MusicStyle;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;
import br.com.ariki.music.suggestion.by.weather.domain.entity.TrackInfo;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.FeignSpotifyAPI;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.Artists;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.Items;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.PlaylistResponse;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.Track;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.Tracks;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyApiDataProviderTest {
	
	@InjectMocks
	private SpotifyApiDataProvider dataprovider;
	
	@Mock
	private FeignSpotifyAPI client;
	
	@Mock
	private Environment environment;
	
	@Test
	public void testExecuteByCityName() {

		when(client.getPlaylist(anyString(), anyString())).thenReturn(PlaylistResponse.builder()
				.name("description")
				.tracks(Tracks.builder()
						.items(Stream.of(Items.builder()
								.track(Track.builder()
										.artists(Stream.of(Artists.builder()
												.name("artistName")
												.build()).collect(Collectors.toList()))
										.href("href")
										.name("musicName")
										.build())
								.build()).collect(Collectors.toList()))
						.build())
				.build());
		when(environment.getProperty(anyString())).thenReturn("text");
		
		Playlist actual = dataprovider.getPlaylist(MusicStyle.ROCK, "token");
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
