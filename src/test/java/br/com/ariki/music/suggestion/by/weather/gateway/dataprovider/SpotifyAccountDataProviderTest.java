package br.com.ariki.music.suggestion.by.weather.gateway.dataprovider;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.ariki.music.suggestion.by.weather.domain.entity.SpotifyToken;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.FeignSpotifyAccountAPI;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.spotify.response.SpotifyTokenResponse;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyAccountDataProviderTest {
	
	@InjectMocks
	private SpotifyAccountDataProvider dataprovider;
	
	@Mock
	private FeignSpotifyAccountAPI client;
	
	@Test
	public void testExecuteByCityName() {

		when(client.getToken(anyString(), any())).thenReturn(SpotifyTokenResponse.builder()
				.accessToken("accessToken")
				.build());
		
		SpotifyToken actual = dataprovider.getToken();
		SpotifyToken expected = SpotifyToken.builder()
				.accessToken("accessToken")
				.build();
		assertEquals(expected, actual);
	}
}
