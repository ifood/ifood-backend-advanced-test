package com.ifood.api;

import com.ifood.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
public class OpenWeatherMapAPITest {
    @Autowired
    private OpenWeatherMapAPI openWeatherMapAPI;

    @Test(expected = OpenWeatherMapAPI.CityNotFoundException.class) public void
    getTemperatureFromNonExistingCity_CityNotFoundException() throws Exception {
        openWeatherMapAPI.getTemperatureByCity("as124asd");
    }

    @Test public void
    getTemperatureFromExistingCity_notMinimumFloatValue() throws Exception {
        float t = openWeatherMapAPI.getTemperatureByCity("Jaú");
        Assert.assertNotEquals(t, (float) 0, (float) 2);
    }

}
