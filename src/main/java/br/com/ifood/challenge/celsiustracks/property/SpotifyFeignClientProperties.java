package br.com.ifood.challenge.celsiustracks.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spotify")
public class SpotifyFeignClientProperties {
    private String accessTokenUri;
    private String username;
    private String password;
}
