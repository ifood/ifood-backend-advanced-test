package ifood.component;

import ifood.config.BaseTest;
import ifood.config.WiremockStarter;
import ifood.exception.BaseException;
import ifood.exception.SpotifyInvalidDataException;
import ifood.exception.SpotifyUnnauthorizedException;
import ifood.model.SpotifyTracksResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class SpotifyTracksByPlaylistsTest extends BaseTest {

    @Autowired
    private Spotify spotify;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        configureFor(WiremockStarter.PORT_NUMBER);
    }

    @Test
    public void getOneClassicalTrackFromPlaylistTest() {
        stubFor(get(urlPathEqualTo("/spotify/playlists/37i9dQZF1DWV0gynK7G6pD/tracks"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/spotify/tracks/classical_37i9dQZF1DWV0gynK7G6pD.json")));

        final int expectedPlaylistSize = 1;
        final String expectedTrackName = "Bagatelles I-XIII: Bagatelle I";

        final SpotifyTracksResponse actual = spotify.getTracks("37i9dQZF1DWV0gynK7G6pD", "test-token");

        Assert.assertEquals(expectedPlaylistSize, actual.getTracks().size());
        Assert.assertEquals(expectedTrackName, actual.getTracks().get(0).getName());
    }

    @Test
    public void getFourRockTracksFromPlaylistsTest() {
        stubFor(get(urlPathEqualTo("/spotify/playlists/37i9dQZF1DXcF6B6QPhFDv/tracks"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/spotify/tracks/rock_37i9dQZF1DXcF6B6QPhFDv.json")));

        final int expectedPlaylistSize = 4;
        final String[] expectedTrackName = new String[]
                {"Jumpsuit", "Nico And The Niners", "Anarchist", "High Hopes"};

        final SpotifyTracksResponse actual = spotify.getTracks("37i9dQZF1DXcF6B6QPhFDv", "test-token");

        Assert.assertEquals(expectedPlaylistSize, actual.getTracks().size());
        Assert.assertEquals(expectedTrackName[0], actual.getTracks().get(0).getName());
        Assert.assertEquals(expectedTrackName[1], actual.getTracks().get(1).getName());
        Assert.assertEquals(expectedTrackName[2], actual.getTracks().get(2).getName());
        Assert.assertEquals(expectedTrackName[3], actual.getTracks().get(3).getName());
    }

    @Test
    public void invalidPlaylistBadRequestTest() {
        stubFor(get(urlPathEqualTo("/spotify/playlists/invalid-playlist/tracks"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/spotify/tracks/invalid_playlist_id.json")));

        expectedException.expect(SpotifyInvalidDataException.class);
        expectedException.expectMessage("Dados não encontrados: [inputs:invalid-playlist]");

        spotify.getTracks("invalid-playlist", "test-token");
    }

    @Test
    public void playlistServerErrorTest() {
        stubFor(get(urlPathEqualTo("/spotify/playlists/servererror-playlist/tracks"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));

        expectedException.expect(BaseException.class);
        expectedException.expectMessage("500 Server Error");

        spotify.getTracks("servererror-playlist", "test-token");
    }

    @Test
    public void tracksUnnauthorizedTest() {
        stubFor(get(urlPathEqualTo("/spotify/playlists/test-playlist/tracks"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.UNAUTHORIZED.value())));

        expectedException.expect(SpotifyUnnauthorizedException.class);
        expectedException.expectMessage("Acesso não autorizado (verifique o token de acesso). Mensagem original: [401 Unauthorized]");

        spotify.getTracks("test-playlist", "test-token");
    }
}
