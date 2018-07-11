package ifood.component.impl;

import ifood.component.Spotify;
import ifood.dto.SpotifyPlaylistResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class SpotifyImpl implements Spotify {

    private final RestTemplate restTemplate;

    private final String playlistBaseEndpoint;

    private final UriComponentsBuilder tracksUriBuilder;

    @Autowired
    public SpotifyImpl(@Value("${spotify.playlistEndpoint}") final String playlistBaseEndpoint,
                       @Value("${spotify.tracksEndpoint}") final String tracksEndpoint,
                       final RestTemplate restTemplate) {
        this.playlistBaseEndpoint = playlistBaseEndpoint;
        this.restTemplate = restTemplate;
        this.tracksUriBuilder = UriComponentsBuilder.fromUriString(tracksEndpoint);
    }

    private HttpEntity<String> createHttpHeader(final String token) {
        final HttpHeaders header = new HttpHeaders();
        header.set("Authorization", String.format("Bearer %s", token));
        return new HttpEntity<>(header);
    }

    @Override
    public SpotifyPlaylistResponse getPlaylist(final String category, final String country, final String token) {
        try {
            final String playlistEndpoint = String.format(playlistBaseEndpoint, category);
            final UriComponentsBuilder playlistUriBuilder = UriComponentsBuilder.fromUriString(playlistEndpoint);

            if (StringUtils.isNotBlank(country)) {
                playlistUriBuilder.queryParam("country", country);
            }

            final URI uri = playlistUriBuilder.build().toUri();

            log.info(uri.toString());
            ResponseEntity<SpotifyPlaylistResponse> response =
                    restTemplate.exchange(uri, HttpMethod.GET, createHttpHeader(token), SpotifyPlaylistResponse.class);

            if (response.hasBody()) {
                return response.getBody();
            }

        } catch (HttpClientErrorException hcee) {
            if (hcee.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw hcee;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new SpotifyPlaylistResponse(null);
    }
}
