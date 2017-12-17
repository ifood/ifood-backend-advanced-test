package com.ifood.backend.advancedtest.service;

import com.ifood.backend.advancedtest.domain.Category;
import com.ifood.backend.advancedtest.domain.PlaylistItem;
import com.ifood.backend.advancedtest.domain.PlaylistResponse;
import com.ifood.backend.advancedtest.domain.Weather;
import com.ifood.backend.advancedtest.service.openweather.OpenWeatherApiClient;
import com.ifood.backend.advancedtest.service.spotify.client.SpotifyPlaylistApiClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringRunner.class)
public class TrackSuggestionServiceTest {

    @Spy
    @InjectMocks
    TrackSuggestionService trackSuggestionService;

    @Mock
    OpenWeatherApiClient openWeatherApiClient;

    @Mock
    SpotifyPlaylistApiClient spotifyPlaylistApiClient;

    @Before
    public void setUp() throws Exception {
        when(openWeatherApiClient.getWeatherInfo("manaus")).thenReturn(mockWeather(30.f));
        when(openWeatherApiClient.getWeatherInfo("salvador")).thenReturn(mockWeather(25.f));
        when(openWeatherApiClient.getWeatherInfo("campinas")).thenReturn(mockWeather(14.f));
        when(openWeatherApiClient.getWeatherInfo("siberia")).thenReturn(mockWeather(0.f));

        when(spotifyPlaylistApiClient.getPlayList("spotify", Category.PARTY.getPlaylistId())).thenReturn(mockPlaylistResponse(2));
        when(spotifyPlaylistApiClient.getPlayList("spotify", Category.POP.getPlaylistId())).thenReturn(mockPlaylistResponse(3));
        when(spotifyPlaylistApiClient.getPlayList("spotify", Category.ROCK.getPlaylistId())).thenReturn(mockPlaylistResponse(4));
        when(spotifyPlaylistApiClient.getPlayList("spotify", Category.CLASSICAL.getPlaylistId())).thenReturn(mockPlaylistResponse(5));
    }

    @Test
    public void suggestPartyTracks() throws Exception {
        PlaylistResponse playlistResponse = trackSuggestionService.suggestTracks("manaus");
        assertEquals(2, playlistResponse.getItems().length);
    }

    @Test
    public void suggestPopTracks() throws Exception {
        PlaylistResponse playlistResponse = trackSuggestionService.suggestTracks("salvador");
        assertEquals(3, playlistResponse.getItems().length);
    }

    @Test
    public void suggestRockTracks() throws Exception {
        PlaylistResponse playlistResponse = trackSuggestionService.suggestTracks("campinas");
        assertEquals(4, playlistResponse.getItems().length);
    }

    @Test
    public void suggestClassicalTracks() throws Exception {
        PlaylistResponse playlistResponse = trackSuggestionService.suggestTracks("siberia");
        assertEquals(5, playlistResponse.getItems().length);
    }

    private Weather mockWeather(float temperature) {
        Weather weather = new Weather();
        Weather.MainInfo mainInfo = weather.new MainInfo();
        mainInfo.temp = temperature;
        weather.setMain(mainInfo);
        return weather;
    }

    private PlaylistResponse mockPlaylistResponse(int size) {
        PlaylistResponse playlistResponse = new PlaylistResponse();
        playlistResponse.setItems(new PlaylistItem[size]);
        return playlistResponse;
    }

}