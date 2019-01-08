package io.brunodoescoding.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.LoadingCache;
import io.brunodoescoding.dto.track.ItemDto;
import io.brunodoescoding.dto.track.SpotifyResultDto;
import io.brunodoescoding.dto.track.TrackDto;
import io.brunodoescoding.service.MusicPlatformService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyServiceImplTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    LoadingCache<String, String> credentialsCache;

    @Mock
    LoadingCache<String, String> tracksCache;

    MusicPlatformService musicPlatformService;

    @Before
    public void setUp() {
        this.musicPlatformService = new SpotifyServiceImpl(restTemplate, credentialsCache, tracksCache);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testPickSongsWhenGenreIsNull() {
        musicPlatformService.pickSongs(null);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testPickSongsWhenGenreIsEmpty() {
        musicPlatformService.pickSongs("");
    }

    @Test(expected = HttpClientErrorException.class)
    public void testPickSongsWhenCredentialsAreNullAndApiIsDown() {
        ResponseEntity<Map> apiResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        String genre = "Sertanejo";

        when(tracksCache.getIfPresent(eq(genre))).thenReturn(null);
        when(credentialsCache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
            .thenReturn(apiResponse);

        musicPlatformService.pickSongs(genre);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testPickSongsWhenCredentialsAreEmptyAndApiIsDown() {
        ResponseEntity<Map> apiResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        String genre = "Sertanejo";

        when(tracksCache.getIfPresent(eq(genre))).thenReturn(null);
        when(credentialsCache.getIfPresent(anyString())).thenReturn("");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(apiResponse);

        musicPlatformService.pickSongs(genre);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testPickSongsWhenCredentialsAreFoundAndApiIsDown() {
        ResponseEntity<Map> credentialsResponse = new ResponseEntity<>(new HashMap<String, Object>() {{
                                                                            put("access_token", "valid");
                                                                       }}, HttpStatus.OK);
        ResponseEntity<SpotifyResultDto> apiResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        String genre = "Indie";

        when(tracksCache.getIfPresent(eq(genre))).thenReturn(null);
        when(credentialsCache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(credentialsResponse);
        when(restTemplate.exchange(contains(genre), eq(HttpMethod.GET), any(HttpEntity.class), eq(SpotifyResultDto.class)))
                .thenReturn(apiResponse);

        musicPlatformService.pickSongs(genre);
    }

    @Test
    public void testPickSongsWhenCredentialsAreFoundAndApiIsOk() {
        ResponseEntity<Map> credentialsResponse = new ResponseEntity<>(new HashMap<String, Object>() {{
            put("access_token", "valid");
        }}, HttpStatus.OK);

        ItemDto song = new ItemDto();
        song.setName("toxic");

        TrackDto track = new TrackDto();
        track.setItems(Arrays.asList(song));

        SpotifyResultDto body = new SpotifyResultDto();
        body.setTracks(track);

        ResponseEntity<SpotifyResultDto> apiResponse = new ResponseEntity<>(body, HttpStatus.OK);
        String genre = "pop";

        when(tracksCache.getIfPresent(eq(genre))).thenReturn(null);
        when(credentialsCache.getIfPresent(anyString())).thenReturn(null);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(credentialsResponse);
        when(restTemplate.exchange(contains(genre), eq(HttpMethod.GET), any(HttpEntity.class), eq(SpotifyResultDto.class)))
                .thenReturn(apiResponse);

        List<String> songs = musicPlatformService.pickSongs(genre);

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("toxic", songs.get(0));
    }

    @Test
    public void testPickSongsWhenCredentialsAreCachedAndApiIsOk() {
        ItemDto song = new ItemDto();
        song.setName("stronger");

        TrackDto track = new TrackDto();
        track.setItems(Arrays.asList(song));

        SpotifyResultDto body = new SpotifyResultDto();
        body.setTracks(track);

        ResponseEntity<SpotifyResultDto> apiResponse = new ResponseEntity<>(body, HttpStatus.OK);
        String genre = "pop";

        when(tracksCache.getIfPresent(eq(genre))).thenReturn(null);
        when(credentialsCache.getIfPresent(anyString())).thenReturn("valid");
        when(restTemplate.exchange(contains(genre), eq(HttpMethod.GET), any(HttpEntity.class), eq(SpotifyResultDto.class)))
                .thenReturn(apiResponse);

        List<String> songs = musicPlatformService.pickSongs(genre);

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("stronger", songs.get(0));
    }

    @Test
    public void testPickSongsWhenCredentialsAreCachedAndApiResponseIsCached() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String genre = "pop";
        String jsonString = mapper.writeValueAsString(Arrays.asList("gimme more"));

        when(tracksCache.getIfPresent(eq(genre))).thenReturn(Base64.getEncoder().encodeToString(jsonString.getBytes()));

        List<String> songs = musicPlatformService.pickSongs(genre);

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("gimme more", songs.get(0));
    }

}
