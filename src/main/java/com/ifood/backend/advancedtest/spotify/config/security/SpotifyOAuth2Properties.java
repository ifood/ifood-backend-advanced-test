package com.ifood.backend.advancedtest.spotify.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spotify.oauth2")
@Data
@Component
public class SpotifyOAuth2Properties {

    private String url;
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String scope;

}
