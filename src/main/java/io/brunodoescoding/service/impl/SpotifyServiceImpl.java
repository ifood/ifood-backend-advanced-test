package io.brunodoescoding.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.cache.LoadingCache;
import io.brunodoescoding.dto.track.ItemDto;
import io.brunodoescoding.dto.track.SpotifyResultDto;
import io.brunodoescoding.service.MusicPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpotifyServiceImpl implements MusicPlatformService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String rootUri;
    private final String credentialsUri;

    private final String clientId;
    private final String clientSecret;

    private final String ACCESS_TOKEN_KEY = "access_token";

    private final LoadingCache<String, String> credentialsCache;
    private final LoadingCache<String, String> tracksCache;

    @Autowired
    public SpotifyServiceImpl(RestTemplate restTemplate,
                              @Qualifier("credentialsCache") LoadingCache<String, String> credentialsCache,
                              @Qualifier("tracksCache") LoadingCache<String, String> tracksCache) {
        this.rootUri = "https://api.spotify.com";
        this.credentialsUri = "https://accounts.spotify.com/api/token";

        this.clientId = "REPLACE_TOKEN_HERE";
        this.clientSecret = "REPLACE_TOKEN_HERE";

        this.restTemplate = restTemplate;
        this.mapper = new ObjectMapper();

        this.credentialsCache = credentialsCache;
        this.tracksCache = tracksCache;
    }

    private void storeTracksInCache(String genre, List<String> tracks) {
        try {
            tracksCache.put(genre, Base64.getEncoder().encodeToString(mapper.writeValueAsString(tracks).getBytes()));
        } catch (JsonProcessingException cause) {
            System.out.println(String.format("Houston, we have a problem... encoding the tracks for genre %s.", genre));
        }
    }

    private List<String> loadTracksFromCache(String genre) {
        String encodedTracks = tracksCache.getIfPresent(genre);
        if(encodedTracks == null) {
            return Collections.emptyList();
        }

        List<String> decodedTracks = Collections.emptyList();

        try {
            decodedTracks = mapper.readValue(new String(Base64.getDecoder().decode(encodedTracks), "UTF-8"), List.class);
        }catch (IOException cause) {
            System.out.println(String.format("Houston, we have a problem... decoding tracks for genre %s.", genre));
        }

        return decodedTracks;
    }

    @Override
    public List<String> pickSongs(String genre) throws HttpClientErrorException {
        if(Strings.isNullOrEmpty(genre)) {
            throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, null, null,
                    "Genre parameter must not be null nor empty.".getBytes(),
                    Charset.forName("UTF-8"));
        }

        List<String> decodedTracks = loadTracksFromCache(genre);
        if(!decodedTracks.isEmpty()) {
            System.out.println(String.format("[genre: %s] Cache Hit!", genre));
            return decodedTracks;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Bearer %s", generateToken()));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                                            .fromUriString("/v1/search")
                                            .queryParam("q", String.format("genre:%s", genre))
                                            .queryParam("type", "track")
                                            .queryParam("limit", 15);

        ResponseEntity<SpotifyResultDto> found = restTemplate.exchange(this.rootUri + builder.build().toUriString(),
                                                    HttpMethod.GET, entity, SpotifyResultDto.class);

        if(found.getStatusCodeValue() == HttpStatus.OK.value()) {
            List<String> results = found.getBody().getTracks().getItems().stream().map(ItemDto::getName).collect(Collectors.toList());
            storeTracksInCache(genre, results);

            return results;
        }

        throw HttpClientErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, null, null,
                                                String.format("Problems while fetching tracks of genre %s.", genre).getBytes(),
                                                Charset.forName("UTF-8"));
    }

    @Override
    public String generateToken() {
        String cachedToken = credentialsCache.getIfPresent(ACCESS_TOKEN_KEY);
        if(!Strings.isNullOrEmpty(cachedToken)) {
            return cachedToken;
        }

        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Basic %s", encodedCredentials));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>("grant_type=client_credentials", headers);
        ResponseEntity<Map> found = restTemplate.exchange(credentialsUri, HttpMethod.POST,
                                        entity, Map.class);

        if(found.getStatusCodeValue() == HttpStatus.OK.value()) {
            String token = String.valueOf(found.getBody().get(ACCESS_TOKEN_KEY));

            credentialsCache.put(ACCESS_TOKEN_KEY, token);
            return token;
        }

        throw HttpClientErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, null, null,
                                              "Problems while authenticating to Spotify API.".getBytes(), Charset.forName("UTF-8"));
    }
}
