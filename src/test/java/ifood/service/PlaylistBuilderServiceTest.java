package ifood.service;

import ifood.component.OpenWeather;
import ifood.component.Spotify;
import ifood.config.BaseTest;
import ifood.config.WiremockStarter;
import ifood.exception.InvalidCityException;
import ifood.exception.SpotifyInvalidDataException;
import ifood.model.*;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.mockito.Mockito.when;

public class PlaylistBuilderServiceTest extends BaseTest {

    @Autowired
    private PlaylistBuilderService service;

    @MockBean
    private OpenWeather openWeather;

    @MockBean
    private Spotify spotify;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        configureFor(WiremockStarter.PORT_NUMBER);
    }

    @Test
    public void successCampinasOnePlaylistOneRockTrackTest() {
        final List<String> playlistMock = new ArrayList<>();
        playlistMock.add("test-rock-playlist");

        final List<SpotifyTrackData> tracksMock = new ArrayList<>();
        tracksMock.add(new SpotifyTrackData("rock-track-1"));

        when(openWeather.getCityTemp("campinas", null, null))
                .thenReturn(new OpenWeatherResponse("Campinas", 13, "BR"));

        when(spotify.getPlaylist(TrackCategoryEnum.ROCK, "BR", "test-token"))
                .thenReturn(new SpotifyPlaylistResponse(playlistMock));

        when(spotify.getTracks(playlistMock.get(0), "test-token"))
                .thenReturn(new SpotifyTracksResponse(tracksMock));

        final int expectedSize = 1;
        final String expectedTrackName = "rock-track-1";

        final List<SpotifyTrackData> actual = service.getTracksByLocation("campinas", null, null, "test-token");

        Assert.assertEquals(expectedSize, actual.size());
        Assert.assertEquals(expectedTrackName, actual.get(0).getName());
    }

    @Test
    public void successSorocabaTwoPlaylistsFourPopTrackTest() {
        final List<String> playlistMock = new ArrayList<>();
        playlistMock.add("test-list-1");
        playlistMock.add("test-list-2");

        final List<SpotifyTrackData> tracks1Mock = new ArrayList<>();
        tracks1Mock.add(new SpotifyTrackData("1-pop-track-1"));

        final List<SpotifyTrackData> tracks2Mock = new ArrayList<>();
        tracks2Mock.add(new SpotifyTrackData("2-pop-track-1"));
        tracks2Mock.add(new SpotifyTrackData("2-pop-track-2"));
        tracks2Mock.add(new SpotifyTrackData("2-pop-track-3"));

        when(openWeather.getCityTemp("sorocaba", null, null))
                .thenReturn(new OpenWeatherResponse("Sorocaba", 25, "BR"));

        when(spotify.getPlaylist(TrackCategoryEnum.POP, "BR", "test-token"))
                .thenReturn(new SpotifyPlaylistResponse(playlistMock));

        when(spotify.getTracks(playlistMock.get(0), "test-token"))
                .thenReturn(new SpotifyTracksResponse(tracks1Mock));

        when(spotify.getTracks(playlistMock.get(1), "test-token"))
                .thenReturn(new SpotifyTracksResponse(tracks2Mock));

        final int expectedSize = 4;
        final String[] expectedTrackName = new String[]
                {"1-pop-track-1", "2-pop-track-1", "2-pop-track-2", "2-pop-track-3"};

        final List<SpotifyTrackData> actual = service.getTracksByLocation("sorocaba", null, null, "test-token");

        Assert.assertEquals(expectedSize, actual.size());
        Assert.assertEquals(expectedTrackName[0], actual.get(0).getName());
        Assert.assertEquals(expectedTrackName[1], actual.get(1).getName());
        Assert.assertEquals(expectedTrackName[2], actual.get(2).getName());
        Assert.assertEquals(expectedTrackName[3], actual.get(3).getName());
    }

    @Test
    public void successGeoTwoPlaylistsFivePopTrackTest() {
        final List<String> playlistMock = new ArrayList<>();
        playlistMock.add("test-list-1");
        playlistMock.add("test-list-2");

        final List<SpotifyTrackData> tracks1Mock = new ArrayList<>();
        tracks1Mock.add(new SpotifyTrackData("1-pop-track-1"));
        tracks1Mock.add(new SpotifyTrackData("1-pop-track-2"));

        final List<SpotifyTrackData> tracks2Mock = new ArrayList<>();
        tracks2Mock.add(new SpotifyTrackData("2-pop-track-1"));
        tracks2Mock.add(new SpotifyTrackData("2-pop-track-2"));
        tracks2Mock.add(new SpotifyTrackData("2-pop-track-3"));

        when(openWeather.getCityTemp(null, -23.5, -47.46))
                .thenReturn(new OpenWeatherResponse("Sorocaba", 25, "BR"));

        when(spotify.getPlaylist(TrackCategoryEnum.POP, "BR", "test-token"))
                .thenReturn(new SpotifyPlaylistResponse(playlistMock));

        when(spotify.getTracks(playlistMock.get(0), "test-token"))
                .thenReturn(new SpotifyTracksResponse(tracks1Mock));

        when(spotify.getTracks(playlistMock.get(1), "test-token"))
                .thenReturn(new SpotifyTracksResponse(tracks2Mock));

        final int expectedSize = 5;
        final String[] expectedTrackName = new String[]
                {"1-pop-track-1", "1-pop-track-2", "2-pop-track-1", "2-pop-track-2", "2-pop-track-3"};

        final List<SpotifyTrackData> actual = service.getTracksByLocation(null, -23.5, -47.46, "test-token");

        Assert.assertEquals(expectedSize, actual.size());
        Assert.assertEquals(expectedTrackName[0], actual.get(0).getName());
        Assert.assertEquals(expectedTrackName[1], actual.get(1).getName());
        Assert.assertEquals(expectedTrackName[2], actual.get(2).getName());
        Assert.assertEquals(expectedTrackName[3], actual.get(3).getName());
        Assert.assertEquals(expectedTrackName[4], actual.get(4).getName());
    }

    @Test
    public void failGettingTempByCityTest() {
        when(openWeather.getCityTemp("campinas", null, null))
                .thenThrow(new InvalidCityException("Campinas", null, null, new NotFoundException("test")));

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Dados não encontrados: [cityname:Campinas] [lat:null] [lon:null]");

        service.getTracksByLocation("campinas", null, null, "test-token");
    }

    @Test
    public void failGettingTempByGeoTest() {
        when(openWeather.getCityTemp(null, 10.0, 10.0))
                .thenThrow(new InvalidCityException(null, 10.0, 10.0, new NotFoundException("test")));

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Dados não encontrados: [cityname:null] [lat:10.0] [lon:10.0]");

        service.getTracksByLocation(null, 10.0, 10.0, "test-token");
    }

    @Test
    public void failGettingPlaylistByCityTest() {
        when(openWeather.getCityTemp("campinas", null, null))
                .thenReturn(new OpenWeatherResponse("Campinas", 13, "ZZ"));

        when(spotify.getPlaylist(TrackCategoryEnum.ROCK, "ZZ", "test-token"))
                .thenThrow(new SpotifyInvalidDataException(new String[]{"ROCK"}));

        expectedException.expect(SpotifyInvalidDataException.class);
        expectedException.expectMessage("Dados não encontrados: [inputs:ROCK]");

        service.getTracksByLocation("campinas", null, null, "test-token");
    }

    @Test
    public void failGettingPlaylistByGeoTest() {
        when(openWeather.getCityTemp(null, -23.5, -47.46))
                .thenReturn(new OpenWeatherResponse("Sorocaba", 25, "ZZ"));

        when(spotify.getPlaylist(TrackCategoryEnum.POP, "ZZ", "test-token"))
                .thenThrow(new SpotifyInvalidDataException(new String[]{"POP"}));

        expectedException.expect(SpotifyInvalidDataException.class);
        expectedException.expectMessage("Dados não encontrados: [inputs:POP]");

        service.getTracksByLocation(null, -23.5, -47.46, "test-token");
    }

    @Test
    public void failGettingTracksByCityTest() {
        final List<String> playlistMock = new ArrayList<>();
        playlistMock.add("test-rock-playlist");

        when(openWeather.getCityTemp("campinas", null, null))
                .thenReturn(new OpenWeatherResponse("Campinas", 13, "BR"));

        when(spotify.getPlaylist(TrackCategoryEnum.ROCK, "BR", "test-token"))
                .thenReturn(new SpotifyPlaylistResponse(playlistMock));

        when(spotify.getTracks(playlistMock.get(0), "test-token"))
                .thenThrow(new SpotifyInvalidDataException(new String[]{"test-rock-playlist"}));

        expectedException.expect(SpotifyInvalidDataException.class);
        expectedException.expectMessage("Dados não encontrados: [inputs:test-rock-playlist]");

        service.getTracksByLocation("campinas", null, null, "test-token");
    }

    @Test
    public void failGettingTracksByGeoTest() {
        final List<String> playlistMock = new ArrayList<>();
        playlistMock.add("test-pop-playlist");

        when(openWeather.getCityTemp(null, -23.5, -47.46))
                .thenReturn(new OpenWeatherResponse("Sorocaba", 25, "BR"));

        when(spotify.getPlaylist(TrackCategoryEnum.POP, "BR", "test-token"))
                .thenReturn(new SpotifyPlaylistResponse(playlistMock));

        when(spotify.getTracks(playlistMock.get(0), "test-token"))
                .thenThrow(new SpotifyInvalidDataException(new String[]{"test-pop-playlist"}));

        expectedException.expect(SpotifyInvalidDataException.class);
        expectedException.expectMessage("Dados não encontrados: [inputs:test-pop-playlist]");

        service.getTracksByLocation(null, -23.5, -47.46, "test-token");
    }
}
