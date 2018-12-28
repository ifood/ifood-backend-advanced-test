package com.ifood.backend.advancedtest.openWeather.gateway.client;

import com.ifood.backend.advancedtest.openWeather.config.interceptor.OpenWeatherMapFeignClientConfiguration;
import com.ifood.backend.advancedtest.openWeather.gateway.client.json.WeatherJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${openweather.service.name}", url = "${openweather.service.url}", configuration = OpenWeatherMapFeignClientConfiguration.class)
public interface OpenWeatherFeignClient {

    @GetMapping("/weather?q={city}&mode=json&units=metric")
    WeatherJson getCondition(@RequestParam(value = "city") String city);

    @GetMapping("/weather?lat={latitude}&lon={longitude}&mode=json&units=metric")
    WeatherJson getCondition(@RequestParam(value = "latitude") float latitude, @RequestParam(value = "longitude") float longitude);
}