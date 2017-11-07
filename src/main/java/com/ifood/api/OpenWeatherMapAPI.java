package com.ifood.api;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Handle OpenWeatherMap connection and functionality.
 */
public class OpenWeatherMapAPI {

    @Autowired
    private OpenWeatherMap owm;

    /**
     * Retrieve currently temperature by city name.
     *
     * @param cityName City name
     * @return City currently temperature
     * @throws IOException Error to connect OpenWeatherMap
     * @throws CityNotFoundException City not found
     */
    public float getTemperatureByCity(String cityName) throws IOException, CityNotFoundException {
        return getTemperatureFromWeatherResponse(owm.currentWeatherByCityName(cityName));
    }

    /**
     * Retrieve currently temperature by latitude and longitude.
     *
     * @param latitude Latitude value
     * @param longitude Longitude value
     * @return Place currently temperature
     * @throws IOException Error to connect OpenWeatherMap
     * @throws CityNotFoundException City not found
     */
    public float getTemperatureByLocation(float latitude, float longitude) throws IOException,
            CityNotFoundException {
        CurrentWeather cw = owm.currentWeatherByCoordinates(latitude, longitude);
        return getTemperatureFromWeatherResponse(cw);
    }

    private float getTemperatureFromWeatherResponse(CurrentWeather currentWeather) {
        if (currentWeather.isValid())
            return currentWeather.getMainInstance().getTemperature();
        else
            throw new CityNotFoundException("City not found [" + currentWeather.getCityName() + "]");
    }

    /**
     * Signalizes city/place not found.
     */
    public class CityNotFoundException extends RuntimeException {
        public CityNotFoundException(String arg) {
            super(arg);
        }
    }
}
