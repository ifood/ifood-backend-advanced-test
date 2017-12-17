package com.ifood.backend.advancedtest.service.openweather;

import com.ifood.backend.advancedtest.domain.Weather;
import com.ifood.backend.advancedtest.util.ApiClient;
import com.ifood.backend.configuration.OpenWeatherServiceConfiguration;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "services.weather", url = "${services.weather.uri}",
        configuration = OpenWeatherServiceConfiguration.class)
public interface OpenWeatherApiClient extends ApiClient.Api {

    @RequestLine("GET ?q={name}&units=metric")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Weather getWeatherInfo(@Param("name") String cityName);


    @RequestLine("GET ?lat={lat}&lon={lon}&units=metric")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Weather getWeatherInfo(@Param("lat") float lat, @Param("lon") float lon);

}
