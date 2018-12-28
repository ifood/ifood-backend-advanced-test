package com.ifood.backend.advancedtest.spotify.config.interceptor;

import com.ifood.backend.advancedtest.spotify.config.security.SpotifyOAuth2Properties;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

public class SpotifyFeignClientConfiguration {

    @Autowired
    private SpotifyOAuth2Properties spotifyOAuth2Properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }

    private OAuth2ProtectedResourceDetails resource() {
        final ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(spotifyOAuth2Properties.getUrl());
        details.setClientId(spotifyOAuth2Properties.getClientId());
        details.setClientSecret(spotifyOAuth2Properties.getClientSecret());
        details.setScope(Arrays.asList("read", "write"));
        return details;
    }
}
