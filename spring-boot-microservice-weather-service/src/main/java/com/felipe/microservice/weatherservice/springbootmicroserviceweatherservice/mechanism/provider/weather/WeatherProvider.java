package com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.mechanism.provider.weather;

import net.aksingh.owmjapis.api.APIException;

/**
 * Contract for weather providers
 * 
 * @author ffrazato
 */
public interface WeatherProvider {
    /**
     * Given a city name, this method retrieves the current temperature in Celsius degrees
     *
     * @param cityName
     * @return current temperature
     * @throws APIException
     */
    public double getCurrentCelsiusTemperatureByCityName(String cityName) throws APIException;

    /**
     * Given geographic coordinates, this method retrieves the current temperature in Celsius degrees
     *
     * @param latitude
     * @param longitude
     * @return current temperature
     * @throws APIException
     */
    public double getCurrentCelsiusTemperatureByGeoCoordinates(double latitude, double longitude) throws APIException;
}
