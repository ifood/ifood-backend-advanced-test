package com.ifood.backend.advancedtest.openWeather.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class OpenWeatherMapFeignClientConfiguration implements RequestInterceptor {

    @Value("${openweather.auth.appId}")
    private String appId;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("APPID", appId);
    }
}
