package ifood.controller;

import ifood.config.MvcTest;
import ifood.exception.BaseException;
import ifood.exception.ExceptionOriginEnum;
import ifood.exception.InvalidCityException;
import ifood.exception.SpotifyUnnauthorizedException;
import ifood.model.SpotifyTrackData;
import ifood.service.PlaylistBuilderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlaylistBuilderControllerTest extends MvcTest {

    @MockBean
    private PlaylistBuilderService service;

    @Test
    public void successGetTracksByCity() throws Exception {

        final List<SpotifyTrackData> tracksMock = new ArrayList<>();
        tracksMock.add(new SpotifyTrackData("test 1"));

        when(service.getTracksByLocation("campinas", null, null, "test-token"))
                .thenReturn(tracksMock);

        final String expected = "[{\"name\":\"test 1\"}]";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/playlists/city/campinas")
                        .header("Spotify-Token", "test-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(actual ->
                        Assert.assertEquals(expected, actual.getResponse().getContentAsString())
                );
    }

    @Test
    public void successGetTracksByGeo() throws Exception {

        final List<SpotifyTrackData> tracksMock = new ArrayList<>();
        tracksMock.add(new SpotifyTrackData("test 1"));
        when(service.getTracksByLocation(null, -22.91, -47.00, "test-token"))
                .thenReturn(tracksMock);

        final String expected = "[{\"name\":\"test 1\"}]";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/playlists/lat/-22.91/lon/-47.00")
                        .header("Spotify-Token", "test-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(actual ->
                        Assert.assertEquals(expected, actual.getResponse().getContentAsString())
                );
    }

    @Test
    public void failGetTracksByCityNotFound() throws Exception {

        when(service.getTracksByLocation("aaa", null, null, "test-token"))
                .thenThrow(new InvalidCityException("aaa", null, null, new Exception("test")));

        final String expected = "{\"origin\":\"OPEN_WEATHER_API\",\"message\":\"Dados não encontrados: [cityname:aaa] [lat:null] [lon:null]\",\"uri\":\"/playlists/city/aaa\"}";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/playlists/city/aaa")
                        .header("Spotify-Token", "test-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(actual ->
                        Assert.assertEquals(expected, actual.getResponse().getContentAsString())
                );
    }

    @Test
    public void failGetTracksByInvalidGeo() throws Exception {

        when(service.getTracksByLocation(null, -200.0, -200.0, "test-token"))
                .thenThrow(new InvalidCityException("Valor de latitude inválido: [lat:-200.0]"));

        final String expected = "{\"origin\":\"OPEN_WEATHER_API\",\"message\":\"Valor de latitude inválido: [lat:-200.0]\",\"uri\":\"/playlists/lat/-200.0/lon/-200.0\"}";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/playlists/lat/-200.0/lon/-200.0")
                        .header("Spotify-Token", "test-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(actual ->
                        Assert.assertEquals(expected, actual.getResponse().getContentAsString())
                );
    }

    @Test
    public void failGetTracksInvalidToken() throws Exception {

        when(service.getTracksByLocation("campinas", null, null, "invalid-token"))
                .thenThrow(new SpotifyUnnauthorizedException(new Exception("invalid token")));

        final String expected = "{\"origin\":\"SPOTIFY_APY\",\"message\":\"Acesso não autorizado (verifique o token de acesso). Mensagem original: [invalid token]\",\"uri\":\"/playlists/city/campinas\"}";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/playlists/city/campinas")
                        .header("Spotify-Token", "invalid-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(actual ->
                        Assert.assertEquals(expected, actual.getResponse().getContentAsString())
                );
    }

    @Test
    public void failGetTracksInternalBaseError() throws Exception {

        when(service.getTracksByLocation("campinas", null, null, "token-token"))
                .thenThrow(new BaseException("internal error", new Exception("error"), ExceptionOriginEnum.INTERNAL));

        final String expected = "{\"origin\":\"INTERNAL\",\"message\":\"internal error\",\"uri\":\"/playlists/city/campinas\"}";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/playlists/city/campinas")
                        .header("Spotify-Token", "token-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andDo(actual ->
                        Assert.assertEquals(expected, actual.getResponse().getContentAsString())
                );
    }

    @Test
    public void failGetTracksInternalServerError() throws Exception {

        when(service.getTracksByLocation("campinas", null, null, "token-token"))
                .thenThrow(new RuntimeException("", new Exception("")));

        final String expected = "{\"origin\":\"INTERNAL\",\"message\":\"Internal server error.\",\"uri\":\"/playlists/city/campinas\"}";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/playlists/city/campinas")
                        .header("Spotify-Token", "token-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andDo(actual ->
                        Assert.assertEquals(expected, actual.getResponse().getContentAsString())
                );
    }
}
