package com.ifood.api;

import com.ifood.entity.Track;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Handle Spotify connection and functionality.
 */
public class SpotifyAPI {
    private Api api;
    private final String clientID;
    private final String secret;
    private final String uri;
    private String code;

    private final Logger logger = LoggerFactory.getLogger(SpotifyAPI.class);

    /**
     * Constructor to configure the Spotify connection.
     * @param clientID Client ID
     * @param secret Client secret
     * @param uri Return URi
     */
    public SpotifyAPI(String clientID, String secret, String uri) {
        this.clientID = clientID;
        this.secret = secret;
        this.uri = uri;
        this.code = null;
    }

    /**
     * Create a connection to Spotify based on the configurations.
     *
     * @throws IOException Problem to connect to Spotify
     * @throws WebApiException Problem to retrieve information from Spotify.
     */
    public void connect() throws IOException, WebApiException {
        if (code != null)
            return;

        logger.info("Connecting to Spotify");
        code = obtainAuthorizedCode();

        api = Api.builder()
                .clientId(clientID)
                .clientSecret(secret)
                .redirectURI(uri)
                .accessToken(code)
                .build();
    }

    private String obtainAuthorizedCode() throws IOException, WebApiException {
        Api credential = Api.builder()
                .clientId(clientID)
                .clientSecret(secret)
                .redirectURI(uri)
                .build();

        ClientCredentialsGrantRequest request = credential.clientCredentialsGrant().build();
        return request.get().getAccessToken();
    }

    /**
     * Retrieve track based on category.
     *
     * @param category Category name
     * @return List of tracks
     * @throws IOException Problem to connect to Spotify
     * @throws WebApiException Problem to retrieve information from Spotify
     */
    public Collection<Track> getTrackByCategory(String category) throws IOException, WebApiException {
        logger.info("Retrieving Tracks from Spotify");

        TrackSearchRequest request = api.searchTracks(category).build();
        List<com.wrapper.spotify.models.Track> tracks = request.get().getItems();

        List<Track> out = new ArrayList<>();
        
        for (com.wrapper.spotify.models.Track t : tracks)
            out.add(new Track(t.getName()));

        return out;
    }

    /**
     * Set to reconnect to Spotify.
     */
    public void reconnect() {
        this.code = null;
    }
}
