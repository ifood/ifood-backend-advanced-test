package com.ifood.backend.advancedtest.service.spotify;


import com.ifood.backend.advancedtest.service.spotify.interceptor.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class SpotifyRequestInterceptor extends RequestInterceptor {

    public static final String AUTHORIZATION_PARAMETER = "Authorization";

    @Value("${services.spotify.token.path}")
    private String authenticationEndpoint;

    @Autowired
    private SpotifyApiAuthenticationService spotifyApiAuthenticationService;

    @Override
    public void apply(RequestTemplate template) {
        if (authenticationEndpoint.equals(template.url())){
            return;
        }
        template.header(AUTHORIZATION_PARAMETER, "Bearer "+ spotifyApiAuthenticationService.getSpotifyAuthToken());
        super.apply(template);
    }
}
