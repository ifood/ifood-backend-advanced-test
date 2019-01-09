package br.com.imood.integration.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

public class SpotifyFeignConfiguration {

    @Value("${spotify.auth.url}")
    private String accessTokenUri;

    @Value("${spotify.auth.clientId}")
    private String clientId;

    @Value("${spotify.auth.clientSecret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(accessTokenUri);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), details);
    }

}
