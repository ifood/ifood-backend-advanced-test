package com.ifood.backend.configuration;


import com.ifood.backend.advancedtest.service.openweather.OpenWeatherApiClient;
import com.ifood.backend.advancedtest.service.openweather.OpenWeatherErrorDecoder;
import com.ifood.backend.advancedtest.service.openweather.interceptor.OpenWeatherRequestInterceptor;
import com.ifood.backend.advancedtest.util.ApiClient;
import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherServiceConfiguration {

    @Value("${services.weather.uri}")
    private String apiUri;

    @Value("${services.weather.timeout}")
    private int spotifyTimeout;

    @Bean
    public OpenWeatherApiClient createApi() {
        return buildBaseApiClient()
                .setBasePath(apiUri)
                .buildClient(OpenWeatherApiClient.class);
    }

    @Bean
    public ErrorDecoder createOpenWeatherErrorDecoder() {
        return new OpenWeatherErrorDecoder();
    }

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new OpenWeatherRequestInterceptor();
    }

    @Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }

    private ApiClient buildBaseApiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.getFeignBuilder().options(new Request.Options(spotifyTimeout, spotifyTimeout));
        apiClient.getFeignBuilder().logger(new Slf4jLogger());
        apiClient.getFeignBuilder().logLevel(Logger.Level.FULL);
        //apiClient.getFeignBuilder().errorDecoder(new OpenWeatherErrorDecoder());
        apiClient.getFeignBuilder().requestInterceptor(getRequestInterceptor());
        return apiClient;
    }

}
