package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.BaseUnitTest;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.ifood.challenge.celsiustracks.domain.openweathermap.WeatherResource;
import br.com.ifood.challenge.celsiustracks.integration.openweathermap.OpenWeatherMapIntegrationService;
import br.com.ifood.challenge.celsiustracks.property.OpenWeatherMapProperties;
import br.com.ifood.challenge.celsiustracks.service.FinderPlaylistByCategory;
import br.com.ifood.challenge.celsiustracks.service.FinderPlaylistCategoryByTemperatureService;
import br.com.ifood.challenge.celsiustracks.template.FixtureLabel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CelsiusTracksServiceImplTest extends BaseUnitTest {

    public static final String APP_ID = "1234567890";

    @InjectMocks
    private CelsiusTracksServiceImpl celsiusTracksService;
    @Mock
    private OpenWeatherMapProperties openWeatherMapProperties;
    @Mock
    private OpenWeatherMapIntegrationService openWeatherMapIntegrationService;
    @Mock
    private FinderPlaylistCategoryByTemperatureService finderPlaylistCategoryByTemperatureService;
    @Mock
    private FinderPlaylistByCategory finderPlaylistByCategory;

    private PageRequest page;

    private WeatherResource weatherResource;

    private PlaylistCategory playlistCategory;

    private CelsiusPlaylist celsiusPlaylist;

    @Before
    public void setUp() {
        page = PageRequest.of(0, 1);
        weatherResource = createObject(WeatherResource.class, FixtureLabel.RANDOM_WEATHER);
        playlistCategory = createObject(PlaylistCategory.class, FixtureLabel.RANDOM_CATEGORY);
        celsiusPlaylist = createObject(CelsiusPlaylist.class, FixtureLabel.RANDOM_PLAYLIST);
    }

    @Test
    public void shouldGetTracksByCity() {
        final String cityName = "cityName";
        when(openWeatherMapProperties.getAppId()).thenReturn(APP_ID);
        when(openWeatherMapIntegrationService.getWeatherByCity(cityName, APP_ID)).thenReturn(weatherResource);
        when(finderPlaylistCategoryByTemperatureService.find(weatherResource.getWeather().getTemp()))
                .thenReturn(playlistCategory);
        when(finderPlaylistByCategory.find(playlistCategory, page)).thenReturn(celsiusPlaylist);

        final CelsiusPlaylist celsiusPlaylistFound = celsiusTracksService.getTracksByCity(cityName, page);

        assertEquals(celsiusPlaylist, celsiusPlaylistFound);
    }

    @Test
    public void shouldGetTracksByCoordinates() {
        final Double latitude = 0d;
        final Double longitude = 0d;
        when(openWeatherMapProperties.getAppId()).thenReturn(APP_ID);
        when(openWeatherMapIntegrationService.getWeatherByCoordinates(latitude, longitude, APP_ID)).thenReturn(weatherResource);
        when(finderPlaylistCategoryByTemperatureService.find(weatherResource.getWeather().getTemp()))
                .thenReturn(playlistCategory);
        when(finderPlaylistByCategory.find(playlistCategory, page)).thenReturn(celsiusPlaylist);

        final CelsiusPlaylist celsiusPlaylistFound = celsiusTracksService.getTracksByCoordinates(latitude, longitude, page);

        assertEquals(celsiusPlaylist, celsiusPlaylistFound);
    }

    //TODO create unit testes for other cases, such as failure cases

}