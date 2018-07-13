package ifood.component;

import ifood.config.BaseTest;
import ifood.config.WiremockStarter;
import ifood.exception.BaseException;
import ifood.exception.InvalidCityException;
import ifood.model.OpenWeatherResponse;
import org.apache.http.HttpHeaders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class OpenWeatherTest extends BaseTest {

    @Autowired
    private OpenWeather openWeather;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        configureFor(WiremockStarter.PORT_NUMBER);
    }

    @Test
    public void getCityTempSuccessTest() {
        stubFor(get(urlPathEqualTo("/weather"))
                .withQueryParam("appid", equalTo("test-key"))
                .withQueryParam("units", equalTo("metric"))
                .withQueryParam("q", equalTo("campinas"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/openWeather/campinas_BR_20.json")));

        final double expectedTemp = 20;
        final String expectedCountry = "BR";
        final String expectedCity = "Campinas";

        final OpenWeatherResponse actual = openWeather.getCityTemp("campinas", null, null);

        Assert.assertEquals(expectedTemp, actual.getMainTemp(), 0);
        Assert.assertEquals(expectedCountry, actual.getCountry());
        Assert.assertEquals(expectedCity, actual.getCityname());
    }

    @Test
    public void getCityTempInvalidCityNameTest() {
        stubFor(get(urlPathEqualTo("/weather"))
                .withQueryParam("appid", equalTo("test-key"))
                .withQueryParam("units", equalTo("metric"))
                .withQueryParam("q", equalTo("invalidcity"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/openWeather/city_not_found.json")));

        final String expectedMesage = "Dados não encontrados: [cityname:invalidcity] [lat:null] [lon:null]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp("invalidcity", null, null);
    }

    @Test
    public void getLatLonTempSuccessTest() {
        stubFor(get(urlPathEqualTo("/weather"))
                .withQueryParam("appid", equalTo("test-key"))
                .withQueryParam("units", equalTo("metric"))
                .withQueryParam("lat", equalTo("-23.55"))
                .withQueryParam("lon", equalTo("-46.63"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/openWeather/lat_-23.55_lon_-46.63_BR_20.json")));

        final double expectedTemp = 20;
        final String expectedCountry = "BR";
        final String expectedCity = "Campinas";

        final OpenWeatherResponse actual = openWeather.getCityTemp(null, -23.55, -46.63);

        Assert.assertEquals(expectedTemp, actual.getMainTemp(), 0);
        Assert.assertEquals(expectedCountry, actual.getCountry());
        Assert.assertEquals(expectedCity, actual.getCityname());
    }

    @Test
    public void getCityZeroLatLonTest() {
        stubFor(get(urlPathEqualTo("/weather"))
                .withQueryParam("appid", equalTo("test-key"))
                .withQueryParam("units", equalTo("metric"))
                .withQueryParam("lat", equalTo("0.0"))
                .withQueryParam("lon", equalTo("0.0"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBodyFile("mock/openWeather/lat_0_lon_0_EARTH_24.45.json")));

        final double expectedTemp = 24.45;
        final String expectedCountry = null;
        final String expectedCity = "Earth";

        final OpenWeatherResponse actual = openWeather.getCityTemp(null, 0.0, 0.0);

        Assert.assertEquals(expectedTemp, actual.getMainTemp(), 0);
        Assert.assertEquals(expectedCountry, actual.getCountry());
        Assert.assertEquals(expectedCity, actual.getCityname());
    }

    @Test
    public void getCityInternalServerErrorTest() {
        stubFor(get(urlPathEqualTo("/weather"))
                .withQueryParam("appid", equalTo("test-key"))
                .withQueryParam("units", equalTo("metric"))
                .withQueryParam("lat", equalTo("9.9"))
                .withQueryParam("lon", equalTo("9.9"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));

        expectedException.expect(BaseException.class);
        expectedException.expectMessage("500 Server Error");

        openWeather.getCityTemp(null, 9.9, 9.9);
    }

    @Test
    public void getCityTempNullLatTest() {
        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Dados de localização inválidos: [lat:null] [lon:-46.63]");

        openWeather.getCityTemp(null, null, -46.63);
    }

    @Test
    public void getCityTempNullLonTest() {
        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Dados de localização inválidos: [lat:-23.55] [lon:null]");

        openWeather.getCityTemp(null, -23.55, null);
    }

    @Test
    public void getCityTempNullLatLonTest() {
        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Dados de localização inválidos: [lat:null] [lon:null]");

        openWeather.getCityTemp(null, null, null);
    }

    @Test
    public void getCityTempUnderEdgeLatTest() {
        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Valor de latitude inválido: [lat:-90.01]");

        openWeather.getCityTemp(null, -90.01, 10.0);
    }

    @Test
    public void getCityTempOverEdgeLatTest() {
        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Valor de latitude inválido: [lat:90.01]");

        openWeather.getCityTemp(null, 90.01, 10.0);
    }

    @Test
    public void getCityTempUnderEdgeLonTest() {
        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Valor de longitude inválido: [lon:-180.01]");

        openWeather.getCityTemp(null, 10.0, -180.01);
    }

    @Test
    public void getCityTempOverEdgeLonTest() {
        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage("Valor de longitude inválido: [lon:180.01]");

        openWeather.getCityTemp(null, 10.0, 180.01);
    }
}
