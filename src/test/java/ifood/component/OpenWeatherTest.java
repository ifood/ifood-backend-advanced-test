package ifood.component;

import ifood.config.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OpenWeatherTest extends BaseTest {

    @Autowired
    private OpenWeather openWeather;

    @Test
    public void getCityTempSuccessTest() {

    }

    @Test
    public void getCityTempInvalidCityNameTest() {

    }

    @Test
    public void getCityTempLatTest() {

    }

    @Test
    public void getCityTempLonTest() {

    }

    @Test
    public void getCityTempNullLatLonTest() {

    }
}
