package br.com.ariki.music.suggestion.by.weather.gateway.dataprovider;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import br.com.ariki.music.suggestion.by.weather.domain.entity.Temperature;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.FeignOpenWeatherMapAPI;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.response.MainResponse;
import br.com.ariki.music.suggestion.by.weather.gateway.feign.openweathermap.response.OpenWeatherMapResponse;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapDataProviderTest {
	
	@InjectMocks
	private OpenWeatherMapDataProvider dataprovider;
	
	@Mock
	private FeignOpenWeatherMapAPI client;
	
	@Mock
	private Environment environment;
	
	@Test
	public void testExecuteByCityName() {

		when(client.getWeatherByCity(anyString(), anyString(), anyString())).thenReturn(OpenWeatherMapResponse.builder()
				.main(MainResponse.builder()
						.temperature(10D)
						.build())
				.build());
		when(environment.getProperty(anyString())).thenReturn("token");
		
		Temperature actual = dataprovider.getWeatherByCity("New York");
		Temperature expected = Temperature.builder()
				.temperature(10D)
				.build();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetWeatherByLatLon() {

		when(client.getWeatherByLatLon(anyString(), anyString(), anyString(), anyString())).thenReturn(OpenWeatherMapResponse.builder()
				.main(MainResponse.builder()
						.temperature(10D)
						.build())
				.build());
		when(environment.getProperty(anyString())).thenReturn("token");
		
		Temperature actual = dataprovider.getWeatherByLatLon("10", "130");
		Temperature expected = Temperature.builder()
				.temperature(10D)
				.build();
		assertEquals(expected, actual);
	}
	
	

	

}
