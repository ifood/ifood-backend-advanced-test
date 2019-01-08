package io.brunodoescoding.business.impl;

import io.brunodoescoding.business.TemperatureStrategy;
import io.brunodoescoding.business.impl.temperature.BadRequestTemperatureImpl;
import io.brunodoescoding.business.impl.temperature.CityBasedTemperatureImpl;
import io.brunodoescoding.business.impl.temperature.CoordinatesBasedTemperatureImpl;
import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.TemperatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemperaturePickerTest {

    @Mock
    TemperatureService temperatureService;

    @Before
    public void setUp() {
        TemperaturePicker.init(temperatureService);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testWhenNullableParameter() {
        PlaylistSuggestionDto data = null;
        TemperatureStrategy found = TemperaturePicker.pick(data);

        assertNotNull(found);
        assertEquals(BadRequestTemperatureImpl.class, found.getClass());

        found.retrieve(data);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testWhenNoParametersFound() {
        PlaylistSuggestionDto data = PlaylistSuggestionDto.builder().build();
        TemperatureStrategy found = TemperaturePicker.pick(data);

        assertNotNull(data);
        assertNotNull(found);

        assertEquals(BadRequestTemperatureImpl.class, found.getClass());

        found.retrieve(data);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testWhenOnlyLatIsFound() {
        PlaylistSuggestionDto data = PlaylistSuggestionDto.builder().lat("1").build();
        TemperatureStrategy found = TemperaturePicker.pick(data);

        assertNotNull(data);
        assertNotNull(found);

        assertEquals(BadRequestTemperatureImpl.class, found.getClass());

        found.retrieve(data);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testWhenOnlyLonIsFound() {
        PlaylistSuggestionDto data = PlaylistSuggestionDto.builder().lon("1").build();
        TemperatureStrategy found = TemperaturePicker.pick(data);

        assertNotNull(data);
        assertNotNull(found);

        assertEquals(BadRequestTemperatureImpl.class, found.getClass());

        found.retrieve(data);
    }

    @Test
    public void testSuccessfulCityCall(){
        String city = "London";

        PlaylistSuggestionDto data = PlaylistSuggestionDto.builder().city(city).build();
        TemperatureStrategy found = TemperaturePicker.pick(data);

        assertNotNull(data);
        assertEquals(CityBasedTemperatureImpl.class, found.getClass());

        double expectedValue = 15.0;

        when(temperatureService.findByCity(city)).thenReturn(expectedValue);
        assertEquals(expectedValue, found.retrieve(data), 0.0001);
    }

    public void testSuccessfulCoordinatesCall() {
        String lat = "1.0";
        String lon = "1.0";

        PlaylistSuggestionDto data = PlaylistSuggestionDto.builder().lat(lat).lon(lon).build();
        TemperatureStrategy found = TemperaturePicker.pick(data);

        assertNotNull(data);
        assertEquals(CoordinatesBasedTemperatureImpl.class, found.getClass());

        double expectedValue = 15.0;

        when(temperatureService.findByCoordinates(lat, lon)).thenReturn(expectedValue);
        assertEquals(expectedValue, found.retrieve(data), 0.0001);

    }

}
