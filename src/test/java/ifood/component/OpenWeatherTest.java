package ifood.component;

import com.github.tomakehurst.wiremock.client.WireMock;
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

public class OpenWeatherTest extends BaseTest {

    @Autowired
    private OpenWeather openWeather;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        WireMock.configureFor(WiremockStarter.PORT_NUMBER);
    }

    @Test
    public void getCityTempSuccessTest() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/weather?appid=test-key&units=metric&q=campinas"))
                .willReturn(WireMock.aResponse()
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
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/weather?appid=test-key&units=metric&q=invalidcity"))
                .willReturn(WireMock.aResponse()
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
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/weather?appid=test-key&units=metric&lat=-23.55&lon=-46.63"))
                .willReturn(WireMock.aResponse()
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
    public void getCityTempNullLatTest() {
        final String expectedMesage = "Dados de localização inválidos: [lat:null] [lon:-46.63]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, null, -46.63);
    }

    @Test
    public void getCityTempNullLonTest() {
        final String expectedMesage = "Dados de localização inválidos: [lat:-23.55] [lon:null]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, -23.55, null);
    }

    @Test
    public void getCityTempNullLatLonTest() {
        final String expectedMesage = "Dados de localização inválidos: [lat:null] [lon:null]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, null, null);
    }

    @Test
    public void getCityTempUnderEdgeLatTest() {
        final String expectedMesage = "Valor de latitude inválido: [lat:-90.01]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, -90.01, 10.0);
    }

    @Test
    public void getCityTempOverEdgeLatTest() {
        final String expectedMesage = "Valor de latitude inválido: [lat:90.01]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, 90.01, 10.0);
    }

    @Test
    public void getCityTempUnderEdgeLonTest() {
        final String expectedMesage = "Valor de longitude inválido: [lon:-180.01]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, 10.0, -180.01);
    }

    @Test
    public void getCityTempOverEdgeLonTest() {
        final String expectedMesage = "Valor de longitude inválido: [lon:180.01]";

        expectedException.expect(InvalidCityException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, 10.0, 180.01);
    }

    @Test
    public void getCityZeroLatLonTest() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/weather?appid=test-key&units=metric&lat=0.0&lon=0.0"))
                .willReturn(WireMock.aResponse()
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
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/weather?appid=test-key&units=metric&lat=9.9&lon=9.9"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));

        final String expectedMesage = "500 Server Error";

        expectedException.expect(BaseException.class);
        expectedException.expectMessage(expectedMesage);

        openWeather.getCityTemp(null, 9.9, 9.9);
    }
}
