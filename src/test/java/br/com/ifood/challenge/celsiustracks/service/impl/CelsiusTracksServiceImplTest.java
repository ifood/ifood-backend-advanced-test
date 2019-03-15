package br.com.ifood.challenge.celsiustracks.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
public class CelsiusTracksServiceImplTest {

    @InjectMocks
    private CelsiusTracksServiceImpl celsiusTracksService;
    private PageRequest page;

    @Before
    public void setUp() {
        page = PageRequest.of(0, 1);
    }

    @Test
    public void shouldGetTracksByCity() {
        //TODO
        final String cityName = "cityName";
//        celsiusTracksService.getTracksByCity(cityName, page);
    }

    //TODO nao esquecer dos testes dos fallbacks

    @Test
    public void shouldGetTracksByCoordinates() {
        //TODO
        final Double latitude = 0d;
        final Double longitude = 0d;
//        celsiusTracksService.getTracksByCoordinates(latitude, longitude, page);
    }

    //TODO nao esquecer dos testes dos fallbacks

}