package com.ifood.backend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ifood.backend.advancedtest.service.spotify.*;
import com.ifood.backend.advancedtest.service.spotify.client.SpotifyAuthApiClient;
import com.ifood.backend.advancedtest.service.spotify.client.SpotifyPlaylistApiClient;
import com.ifood.backend.advancedtest.util.ApiClient;
import com.ifood.backend.advancedtest.service.spotify.interceptor.ResponseInterceptor;
import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyServiceConfiguration {

    @Value("${services.spotify.token.uri}")
    private String tokenUri;

    @Value("${services.spotify.token.path}")
    private String tokenPath;

    @Value("${services.spotify.uri}")
    private String apiUri;

    @Value("${services.spotify.token.id}")
    private String clientId;

    @Value("${services.spotify.token.secret}")
    private String clientSecret;

    @Value("${services.spotify.timeout}")
    private int spotifyTimeout;

    @Bean
    public SpotifyAuthApiClient createAuthenticateApi() {
        return buildAuthenticationApiClient()
                .buildClient(SpotifyAuthApiClient.class);
    }

    @Bean
    public SpotifyPlaylistApiClient createPlaylistApi() {
        return buildBaseApiClient()
                .setBasePath(apiUri)
                .buildClient(SpotifyPlaylistApiClient.class);
    }

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new SpotifyRequestInterceptor();
    }

    @Bean
    public RequestInterceptor getAuthRequestInterceptor() {
        return new SpotifyAuthenticationRequestInterceptor();
    }

    @Bean
    public Decoder getDecoder() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new ResponseInterceptor(objectMapper);
    }

    @Bean
    public ErrorDecoder createSpotifyErrorDecoder() {
        return new SpotifyErrorDecoder();
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
        apiClient.getFeignBuilder().requestInterceptor(getRequestInterceptor());
        apiClient.getFeignBuilder().errorDecoder(createSpotifyErrorDecoder());
        return apiClient;
    }

    private ApiClient buildAuthenticationApiClient() {
        final ApiClient apiClient = buildBaseApiClient();
        apiClient.setBasePath(tokenUri);
        apiClient.getFeignBuilder().requestInterceptor(getAuthRequestInterceptor());
        apiClient.getFeignBuilder().encoder(getEncoder());
        apiClient.getFeignBuilder().decoder(getDecoder());
        return apiClient;
    }

    @Bean
    public FormEncoder getEncoder() {
        FormEncoder encoder = new FormEncoder();
        encoder.getContentProcessor(ContentType.URLENCODED);
        return encoder;
    }
}
