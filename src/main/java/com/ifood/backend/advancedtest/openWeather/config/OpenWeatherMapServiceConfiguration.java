package com.ifood.backend.advancedtest.openWeather.config;

import com.ifood.backend.advancedtest.openWeather.config.decoder.OpenWeatherMapErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherMapServiceConfiguration {

    @Bean
    public ErrorDecoder getErrorDecoder() {
        return new OpenWeatherMapErrorDecoder();
    }
}
