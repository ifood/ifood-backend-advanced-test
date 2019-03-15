package br.com.ifood.challenge.celsiustracks.config;

import br.com.ifood.challenge.celsiustracks.property.SpotifyFeignClientProperties;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(SpotifyFeignClientProperties.class)
public class SpotifyFeignClientOAuthConfiguration {

    private final SpotifyFeignClientProperties config;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), credentialDetails());
    }

    private OAuth2ProtectedResourceDetails credentialDetails() {
        final ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(config.getAccessTokenUri());
        details.setClientId(config.getUsername());
        details.setClientSecret(config.getPassword());
        return details;
    }
}
