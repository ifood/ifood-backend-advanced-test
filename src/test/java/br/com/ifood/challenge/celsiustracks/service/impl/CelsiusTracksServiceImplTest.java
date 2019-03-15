package br.com.ifood.challenge.celsiustracks.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CelsiusTracksServiceImplTest {

    @InjectMocks
    private CelsiusTracksServiceImpl celsiusTracksService;

    @Test
    public void shouldGetTracksByCity() {
        //TODO
        final String cityName = "cityName";
        celsiusTracksService.getTracksByCity(cityName);
    }

    //TODO nao esquecer dos testes dos fallbacks

    @Test
    public void shouldGetTracksByCoordinates() {
        //TODO
        final Double latitude = 0d;
        final Double longitude = 0d;
        celsiusTracksService.getTracksByCoordinates(latitude, longitude);
    }

    //TODO nao esquecer dos testes dos fallbacks

}