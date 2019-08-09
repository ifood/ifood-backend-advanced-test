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
public class SearchWeatherByLatLonTest {
	
	@InjectMocks
	private SearchWeatherByLatLon usecase;
	
	@Mock
	private OpenWeatherMapGateway gateway;
	
	@Test
	public void testExecute() {
		
		when(gateway.getWeatherByLatLon(anyString(), anyString())).thenReturn(Temperature.builder()
				.temperature(10D)
				.build());
		
		Temperature actual = usecase.execute("12", "130");
		Temperature expected = Temperature.builder()
				.temperature(10D)
				.build();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExecuteRecover() {
		
		Temperature actual = usecase.executeRecover("12", "130", new RuntimeException());
		Temperature expected = null;
		
		assertEquals(expected, actual);
	}

}
