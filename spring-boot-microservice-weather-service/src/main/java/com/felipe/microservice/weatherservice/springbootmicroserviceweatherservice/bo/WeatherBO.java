package com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.bo;

import org.apache.commons.lang3.StringUtils;

import com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.exception.BusinessException;
import com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.mechanism.provider.weather.OpenWeatherMapWeatherProvider;

import net.aksingh.owmjapis.api.APIException;

/**
 * Business class to handle the weather logic
 * 
 * @author ffrazato
 */
public class WeatherBO {

    /**
     * Get current weather by cityname
     * 
     * @param cityName
     * @return temperature in celsius
     */
    public double getWeatherByCityName(String cityName) {
        double temperature;
        if (StringUtils.isNotBlank(cityName)) {
            try {
                // get current tempreature for given city
                temperature = OpenWeatherMapWeatherProvider.getInstance().getCurrentCelsiusTemperatureByCityName(cityName);
            } catch (APIException e) {
                // TODO: Log the error
                throw new BusinessException("Error while getting temperature from weather provider", e);
            }
        } else {
            throw new BusinessException("City name can't be null");
        }

        return temperature;
    }

    /**
     * Get current weather by geo coordinates
     * 
     * @param lat
     *            latitude
     * @param lon
     *            longitude
     * @return temperature in celsius
     */
    public double getWeatherByGeoCoordinates(double lat, double lon) {
        double temperature;
        if (lat != 0D && lon != 0D) {
            try {
                // get current temperature for given geo coordinates
                temperature = OpenWeatherMapWeatherProvider.getInstance().getCurrentCelsiusTemperatureByGeoCoordinates(lat, lon);
            } catch (APIException e) {
                // TODO: Log the error
                throw new BusinessException("Error while getting temperature from weather provider", e);
            }
        } else {
            throw new BusinessException("Missing geo coordinates");
        }

        return temperature;
    }
}
