package br.com.ariki.music.suggestion.by.weather.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.gateway.OpenWeatherMapGateway;

@RunWith(MockitoJUnitRunner.class)
public class SearchWeatherByCityTest {
	
	@InjectMocks
	private SearchWeatherByCity usecase;
	
	@Mock
	private OpenWeatherMapGateway gateway;
	
	@Test
	public void testExecute() {
		
		when(gateway.getWeatherByCity(anyString())).thenReturn(Temperature.builder()
				.temperature(10D)
				.build());
		
		Temperature actual = usecase.execute("New York");
		Temperature expected = Temperature.builder()
				.temperature(10D)
				.build();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExecuteRecover() {
		
		Temperature actual = usecase.executeRecover("New York", new RuntimeException());
		Temperature expected = null;
		
		assertEquals(expected, actual);
	}

}
