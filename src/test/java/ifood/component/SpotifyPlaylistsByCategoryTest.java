package ifood.component;

import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import ifood.config.BaseTest;
import ifood.config.WiremockStarter;
import ifood.exception.BaseException;
import ifood.exception.SpotifyInvalidDataException;
import ifood.exception.SpotifyUnnauthorizedException;
import ifood.model.SpotifyPlaylistResponse;
import ifood.model.TrackCategoryEnum;
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

public class SpotifyPlaylistsByCategoryTest extends BaseTest {

    @Autowired
    private Spotify spotify;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        configureFor(WiremockStarter.PORT_NUMBER);
    }

    @Test
    public void getClassicalPlaylistOneItemSuccessTest() {
        stubFor(get(urlPathEqualTo("/spotify/classical/playlists"))
                .withQueryParam("country", equalTo("BR"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/spotify/playlists/classical_BR.json")));

        final int expectedPlaylistSize = 1;
        final String expectedPlaylistId = "37i9dQZF1DWV0gynK7G6pD";

        final SpotifyPlaylistResponse actual = spotify.getPlaylist(TrackCategoryEnum.CLASSICAL, "BR", "test-token");

        Assert.assertEquals(expectedPlaylistSize, actual.getPlaylistIds().size());
        Assert.assertEquals(expectedPlaylistId, actual.getPlaylistIds().get(0));
    }

    @Test
    public void getRockPlaylistFourItemsSuccessTest() {
        stubFor(get(urlPathEqualTo("/spotify/rock/playlists"))
                .withQueryParam("country", equalTo("US"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/spotify/playlists/rock_US.json")));

        final int expectedPlaylistSize = 4;
        final String[] expectedPlaylistIds = new String[]
                {"37i9dQZF1DXcF6B6QPhFDv", "37i9dQZF1DWT2jS7NwYPVI", "37i9dQZF1DX82GYcclJ3Ug", "37i9dQZF1DWWJOmJ7nRx0C"};

        final SpotifyPlaylistResponse actual = spotify.getPlaylist(TrackCategoryEnum.ROCK, "US", "test-token");

        Assert.assertEquals(expectedPlaylistSize, actual.getPlaylistIds().size());
        Assert.assertEquals(expectedPlaylistIds[0], actual.getPlaylistIds().get(0));
        Assert.assertEquals(expectedPlaylistIds[1], actual.getPlaylistIds().get(1));
        Assert.assertEquals(expectedPlaylistIds[2], actual.getPlaylistIds().get(2));
        Assert.assertEquals(expectedPlaylistIds[3], actual.getPlaylistIds().get(3));
    }

    @Test
    public void getPartyPlaylistTwoItemsWithoutCountrySuccessTest() {
        stubFor(get(urlPathEqualTo("/spotify/party/playlists"))
                .withQueryParam("country", StringValuePattern.ABSENT)
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/spotify/playlists/party_NO_COUNTRY.json")));

        final int expectedPlaylistSize = 2;
        final String[] expectedPlaylistIds = new String[] {"37i9dQZF1DX8mBRYewE6or", "37i9dQZF1DWXYz7MGRnPtc"};

        final SpotifyPlaylistResponse actual = spotify.getPlaylist(TrackCategoryEnum.PARTY, null, "test-token");

        Assert.assertEquals(expectedPlaylistSize, actual.getPlaylistIds().size());
        Assert.assertEquals(expectedPlaylistIds[0], actual.getPlaylistIds().get(0));
        Assert.assertEquals(expectedPlaylistIds[1], actual.getPlaylistIds().get(1));
    }

    @Test
    public void playlistInvalidCountryBadRequestTest() {
        stubFor(get(urlPathEqualTo("/spotify/party/playlists"))
                .withQueryParam("country", equalTo("AA"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/spotify/playlists/invalid_country_code.json")));

        expectedException.expect(SpotifyInvalidDataException.class);
        expectedException.expectMessage("Dados não encontrados: [inputs:PARTY, AA]");

        spotify.getPlaylist(TrackCategoryEnum.PARTY, "AA", "test-token");
    }

    @Test
    public void playlistServerErrorTest() {
        stubFor(get(urlPathEqualTo("/spotify/party/playlists"))
                .withQueryParam("country", equalTo("DE"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));

        expectedException.expect(BaseException.class);
        expectedException.expectMessage("500 Server Error");

        spotify.getPlaylist(TrackCategoryEnum.PARTY, "DE", "test-token");
    }

    @Test
    public void gplaylistUnnauthorizedTest() {
        stubFor(get(urlPathEqualTo("/spotify/rock/playlists"))
                .withQueryParam("country", equalTo("CA"))
                .withHeader("Authorization", equalTo("Bearer test-token"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.UNAUTHORIZED.value())));

        expectedException.expect(SpotifyUnnauthorizedException.class);
        expectedException.expectMessage("Acesso não autorizado (verifique o token de acesso). Mensagem original: [401 Unauthorized]");

        spotify.getPlaylist(TrackCategoryEnum.ROCK, "CA", "test-token");
    }
}
