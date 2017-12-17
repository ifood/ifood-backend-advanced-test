package com.ifood.backend.advancedtest.service.openweather.interceptor;


import com.ifood.backend.advancedtest.service.spotify.interceptor.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class OpenWeatherRequestInterceptor extends RequestInterceptor {

    @Value("${services.weather.id}")
    private String applicationId;

    @Override
    public void apply(RequestTemplate template) {
        template.query("APPID", applicationId);
        super.apply(template);
    }
}
