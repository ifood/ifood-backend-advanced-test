package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallbackfactory.WeatherServiceProxyFallbackFactory;

/**
 * Feign client for sending HTTP request to weather service
 * 
 * @author ffrazato
 */
@FeignClient(name = "weather-service", fallbackFactory = WeatherServiceProxyFallbackFactory.class)
@RibbonClient(name = "weather-service")
public interface WeatherServiceProxy {
    /**
     * Get current weather temperature by city name
     * 
     * @param cityName
     * @return current temperature in celsius
     */
    @GetMapping("/weather/on/{cityName}")
    public Double retrieveCurrentTemperatureByCity(@PathVariable("cityName") String cityName);

    /**
     * Get current weather temperature by geo coordinates
     * 
     * @param lat
     * @param lon
     * @return current temperature in celsius
     */
    @GetMapping("/weather/on/{lat}/{lon}")
    public Double retrieveCurrentTemperatureByGeoCoordinates(@PathVariable("lat") double lat, @PathVariable("lon") double lon);
}
