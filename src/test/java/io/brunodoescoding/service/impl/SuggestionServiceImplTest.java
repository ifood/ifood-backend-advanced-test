package io.brunodoescoding.service.impl;

import io.brunodoescoding.business.impl.TemperaturePicker;
import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.MusicPlatformService;
import io.brunodoescoding.service.SuggestionService;
import io.brunodoescoding.service.TemperatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionServiceImplTest {

    @Mock
    MusicPlatformService musicPlatformService;

    @Mock
    TemperatureService temperatureService;

    SuggestionService suggestionService;

    @Before
    public void setUp() {
        this.suggestionService = new SuggestionServiceImpl(musicPlatformService);

        TemperaturePicker.init(temperatureService);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testPickSongsWhenRequiredParamIsNull() {
        suggestionService.pickSongs(null);
    }

    @Test
    public void testPickSongsWhenGenreIsParty() {
        PlaylistSuggestionDto params = PlaylistSuggestionDto.builder().city("Guaruja").build();

        when(temperatureService.findByCity(eq(params.getCity()))).thenReturn(32.0);
        when(musicPlatformService.pickSongs(eq("party"))).thenReturn(Arrays.asList("I Got a Feeling"));

        List<String> songs = suggestionService.pickSongs(params);
        assertNotNull(songs);

        assertEquals(1, songs.size());
        assertEquals("I Got a Feeling", songs.get(0));
    }

    @Test
    public void testPickSongsWhenGenreIsPop() {
        PlaylistSuggestionDto params = PlaylistSuggestionDto.builder().lat("-23.561109").lon("-46.655860").build();

        when(temperatureService.findByCoordinates(eq(params.getLat()), eq(params.getLon()))).thenReturn(24.0);
        when(musicPlatformService.pickSongs(eq("pop"))).thenReturn(Arrays.asList("Slumber Party"));

        List<String> songs = suggestionService.pickSongs(params);
        assertNotNull(songs);

        assertEquals(1, songs.size());
        assertEquals("Slumber Party", songs.get(0));
    }

    @Test
    public void testPickSongsWhenGenreIsRock() {
        PlaylistSuggestionDto params = PlaylistSuggestionDto.builder().city("London").build();

        when(temperatureService.findByCity(eq(params.getCity()))).thenReturn(11.0);
        when(musicPlatformService.pickSongs(eq("rock"))).thenReturn(Arrays.asList("Wonderwall"));

        List<String> songs = suggestionService.pickSongs(params);
        assertNotNull(songs);

        assertEquals(1, songs.size());
        assertEquals("Wonderwall", songs.get(0));
    }

    @Test
    public void testPickSongsWhenGenreIsClassical() {
        PlaylistSuggestionDto params = PlaylistSuggestionDto.builder().lat("47.386390").lon("8.517251").build();

        when(temperatureService.findByCoordinates(eq(params.getLat()), eq(params.getLon()))).thenReturn(0.0);
        when(musicPlatformService.pickSongs(eq("classical"))).thenReturn(Arrays.asList("Ode to Joy"));

        List<String> songs = suggestionService.pickSongs(params);
        assertNotNull(songs);

        assertEquals(1, songs.size());
        assertEquals("Ode to Joy", songs.get(0));
    }

}
