package ifood.service;

import ifood.component.OpenWeather;
import ifood.config.BaseTest;
import ifood.model.OpenWeatherResponse;
import ifood.model.WeatherResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

public class PlaylistBuilderServiceTest extends BaseTest {

    @Autowired
    private PlaylistBuilderService service;

    @MockBean
    private OpenWeather openWeather;

    @Test
    public void successCitynameTest() {
        when(openWeather.getCityTemp("Campinas"))
                .thenReturn(new OpenWeatherResponse("Campinas", 25.0, "BR"));

        final double expectedTemp = 25;
        final String expectedCity = "Campinas";

        final WeatherResponse actual = service.getTemp("Campinas");

        Assert.assertEquals(expectedTemp, actual.getTemp(), 0);
        Assert.assertEquals(expectedCity, actual.getCityname());
    }

    @Test
    public void successGeoTest() {
        when(openWeather.getCityTemp(10.0, 20.0))
                .thenReturn(new OpenWeatherResponse("Sorocaba", 15.0, "BR"));

        final double expectedTemp = 15;
        final String expectedCity = "Sorocaba";

        final WeatherResponse actual = service.getTemp(10.0, 20.0);

        Assert.assertEquals(expectedTemp, actual.getTemp(), 0);
        Assert.assertEquals(expectedCity, actual.getCityname());
    }
}
