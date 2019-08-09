package br.com.ariki.music.suggestion.by.weather.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.ariki.music.suggestion.by.weather.domain.entity.SpotifyToken;
import br.com.ariki.music.suggestion.by.weather.gateway.SpotifyAccountGateway;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyGetTokenTest {
	
	@InjectMocks
	private SpotifyGetToken usecase;
	
	@Mock
	private SpotifyAccountGateway gateway;
	
	@Test
	public void testExecute() {
		
		when(gateway.getToken()).thenReturn(SpotifyToken.builder()
				.accessToken("accessToken")
				.build());
		
		String actual = usecase.execute();
		String expected = "accessToken";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExecuteRecover() {
		
		String actual = usecase.executeRecover();
		String expected = null;
		
		assertEquals(expected, actual);
	}

}
