package io.brunodoescoding.service.impl;

import com.google.common.cache.LoadingCache;
import io.brunodoescoding.dto.temperature.WeatherDto;
import io.brunodoescoding.service.TemperatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceImplTest {

    TemperatureService temperatureService;

    @Mock
    RestTemplate restTemplate;

    @Mock
    LoadingCache<String, String> cache;

    @Before
    public void setUp() {
        this.temperatureService = new TemperatureServiceImpl(restTemplate, cache);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCityWhenRequiredParameterIsNull() {
        temperatureService.findByCity(null);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCityWhenRequiredParametersIsEmpty() {
        temperatureService.findByCity("");
    }

    @Test
    public void testFindByCityWhenAvailableInCache() {
        when(cache.getIfPresent(anyString())).thenReturn("1.0");
        assertEquals(1.0d, temperatureService.findByCity("London"), 0.00001);
    }

    @Test(expected = HttpClientErrorException.NotFound.class)
    public void testFindByCityWhenNotAvailableInCacheNotFoundInApi() {
        ResponseEntity<WeatherDto> mockedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(cache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.getForEntity(anyString(), eq(WeatherDto.class))).thenReturn(mockedResponse);

        temperatureService.findByCity("New York");
    }

    @Test(expected = HttpClientErrorException.class)
    public void testFindByCityWhenNotAvailableInCacheAndServiceIsDown() {
        ResponseEntity<WeatherDto> mockedResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(cache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.getForEntity(anyString(), eq(WeatherDto.class))).thenReturn(mockedResponse);

        temperatureService.findByCity("Canada");
    }

    @Test
    public void testFindByCityWhenNotAvailableInCacheAndServiceIsOk() {
        WeatherDto expected = new WeatherDto();
        expected.setMain(new HashMap<String, Double>() {{ put("temp", 25.0d); }});

        ResponseEntity<WeatherDto> mockedResponse = new ResponseEntity<>(expected, HttpStatus.OK);
        String city = "Milan";

        when(cache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.getForEntity(contains(city), eq(WeatherDto.class))).thenReturn(mockedResponse);

        assertEquals(25.0, temperatureService.findByCity(city), 0.00001);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCoordinatesWhenBothRequiredParametersAreNull() {
        temperatureService.findByCoordinates(null, null);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCoordinatesWhenLatIsEmptyAndLonIsNull() {
        temperatureService.findByCoordinates("", null);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCoordinatesWhenLatIsNullAndLonIsEmpty() {
        temperatureService.findByCoordinates(null, "");
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCoordinatesWhenBothRequiredParametersAreEmpty() {
        temperatureService.findByCoordinates("", "");
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCoordinatesWhenBothRequiredParametersAreInvalidNumbers() {
        temperatureService.findByCoordinates("invalid", "invalid");
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCoordinatesWhenLatIsInvalidNumberAndLonIsValidNumber() {
        temperatureService.findByCoordinates("invalid", "25");
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testFindByCoordinatesWhenLatIsValidNumberAndLonIsInvalidNumber() {
        temperatureService.findByCoordinates("25", "invalid");
    }

    @Test
    public void testFindByCoordinatesWhenAvailableInCache() {
        when(cache.getIfPresent(anyString())).thenReturn("1.0");
        assertEquals(1.0d, temperatureService.findByCoordinates("35", "139"), 0.00001);
    }

    @Test(expected = HttpClientErrorException.NotFound.class)
    public void testFindByCoordinatesWhenNotAvailableInCacheNotFoundInApi() {
        ResponseEntity<WeatherDto> mockedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String lat = "12";
        String lon = "18";

        when(cache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.getForEntity(and(contains(lat), contains(lon)), eq(WeatherDto.class))).thenReturn(mockedResponse);

        temperatureService.findByCoordinates(lat, lon);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testFindByCoordinatesWhenNotAvailableInCacheAndServiceIsDown() {
        ResponseEntity<WeatherDto> mockedResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        String lat = "17";
        String lon = "30";

        when(cache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.getForEntity(and(contains(lat), contains(lon)), eq(WeatherDto.class))).thenReturn(mockedResponse);

        temperatureService.findByCoordinates(lat, lon);
    }

    @Test
    public void testFindByCoordinatesWhenNotAvailableInCacheAndServiceIsOk() {
        WeatherDto expected = new WeatherDto();
        expected.setMain(new HashMap<String, Double>() {{ put("temp", 25.0d); }});

        ResponseEntity<WeatherDto> mockedResponse = new ResponseEntity<>(expected, HttpStatus.OK);

        String lat = "13";
        String lon = "50";

        when(cache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.getForEntity(and(contains(lat), contains(lon)), eq(WeatherDto.class))).thenReturn(mockedResponse);

        assertEquals(25.0, temperatureService.findByCoordinates(lat, lon), 0.00001);
    }

}
