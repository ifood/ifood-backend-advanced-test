package com.ifood;

import com.ifood.api.OpenWeatherMapAPI;
import com.ifood.api.SpotifyAPI;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * Create the Beans based on the configuration (api.properties).
 */
@org.springframework.context.annotation.Configuration
@PropertySource("classpath:api.properties")
public class Configuration {
    private final Logger logger = LoggerFactory.getLogger(Configuration.class);

    @Value("${spotifyClientId}")
    private String clientID;

    @Value("${spotifySecret}")
    private String secret;

    @Value("${spotifyUri}")
    private String uri;

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
            this.uri = uri;
        }

    @Bean
    public SpotifyAPI spotifyAPI() {
        logger.info("SpotifyAPI creation.");

        return new SpotifyAPI(getClientID(), getSecret(), getUri());
    }

    @Value("${openWeatherMapKey}")
    private String openWeatherMapKey;

    public String getOpenWeatherMapKey() {
        return openWeatherMapKey;
    }

    public void setOpenWeatherMapKey(String key) {
        this.openWeatherMapKey = key;
    }

    @Bean
    public OpenWeatherMap owm() {
        logger.info("OpenWeatherMap creation.");

        return new OpenWeatherMap(getOpenWeatherMapKey());
    }

    @Bean
    public OpenWeatherMapAPI owmAPI() {
        logger.info("OpenWeatherMapAPI creation.");

        return new OpenWeatherMapAPI();
    }
}
