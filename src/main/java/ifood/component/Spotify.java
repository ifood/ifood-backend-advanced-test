package ifood.component;

import ifood.model.SpotifyPlaylistResponse;
import ifood.model.SpotifyTracksResponse;
import ifood.model.TrackCategoryEnum;
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
public class Spotify {

    private final RestTemplate restTemplate;

    private final String playlistBaseEndpoint;

    private final String tracksBaseEndpoint;

    @Autowired
    public Spotify(@Value("${spotify.playlistEndpoint}") final String playlistBaseEndpoint,
                   @Value("${spotify.tracksEndpoint}") final String tracksBaseEndpoint,
                   final RestTemplate restTemplate) {
        this.tracksBaseEndpoint = tracksBaseEndpoint;
        this.playlistBaseEndpoint = playlistBaseEndpoint;
        this.restTemplate = restTemplate;
    }

    private HttpEntity<String> createHttpHeader(final String token) {
        final HttpHeaders header = new HttpHeaders();
        header.set("Authorization", String.format("Bearer %s", token));
        return new HttpEntity<>(header);
    }

    public SpotifyPlaylistResponse getPlaylist(final TrackCategoryEnum trackCategoryEnum,
                                               final String country,
                                               final String token) {
        try {
            final String playlistEndpoint = String.format(playlistBaseEndpoint, trackCategoryEnum.toString());
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

    public SpotifyTracksResponse getTracks(final String playListId, final String token) {
        try {
            final String tracksEndpoint = String.format(tracksBaseEndpoint, playListId);
            final UriComponentsBuilder trackUriBuilder = UriComponentsBuilder.fromUriString(tracksEndpoint);
            final URI uri = trackUriBuilder.build().toUri();

            log.info(uri.toString());
            ResponseEntity<SpotifyTracksResponse> response =
                    restTemplate.exchange(uri, HttpMethod.GET, createHttpHeader(token), SpotifyTracksResponse.class);

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
        return new SpotifyTracksResponse(null);
    }
}
