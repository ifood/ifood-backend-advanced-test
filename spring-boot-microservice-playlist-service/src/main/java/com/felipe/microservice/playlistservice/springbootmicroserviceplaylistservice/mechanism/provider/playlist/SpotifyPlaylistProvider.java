package com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist;

import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;

/**
 * Spotify playlist provider
 * 
 * @author ffrazato
 */
public class SpotifyPlaylistProvider implements PlaylistProvider {

    private static SpotifyApi spotifyApi;
    private static ClientCredentialsRequest clientCredentialsRequest;

    /**
     * Singleton instance
     */
    private static volatile SpotifyPlaylistProvider instance;

    /**
     * Initializer spotify api including the oauth2 authentication
     * 
     * @throws Exception
     */
    private SpotifyPlaylistProvider() throws Exception {
        spotifyApi = new SpotifyApi.Builder().setClientId(getClientId()).setClientSecret(getClientSecret()).build();
        clientCredentialsRequest = spotifyApi.clientCredentials().build();

        final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

        // Set access token for further "spotifyApi" object usage
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
    }

    @Override
    public List<String> getPlaylistTracksByGenres(GenreEnum genre) throws Exception {
        List<String> playlistTracks = new ArrayList<>();

        String playListID = null;

        switch (genre) {
            case PARTY:
                playListID = "37i9dQZF1DXaXB8fQg7xif";
                break;
            case POP:
                playListID = "37i9dQZF1DX6aTaZa0K6VA";
                break;
            case ROCK:
                playListID = "37i9dQZF1DWXRqgorJj26U";
                break;
            case CLASSICAL:
                playListID = "37i9dQZF1DWWEJlAGA9gs0";
                break;

            default:
                break;
        }

        GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi.getPlaylistsTracks(playListID).build();

        Paging<PlaylistTrack> playlistTrackPaging = null;

        playlistTrackPaging = getPlaylistsTracksRequest.execute();

        for (PlaylistTrack playlistTrack : playlistTrackPaging.getItems()) {
            playlistTracks.add(playlistTrack.getTrack().getName());
        }

        return playlistTracks;
    }

    /**
     * Singleton method to get the instance of SpotifyPlaylistProvider
     *
     * @return singleton instance for SpotifyPlaylistProvider
     * @throws Exception
     */
    public static SpotifyPlaylistProvider getInstance() throws Exception {
        // double check to avoid synchronizing it
        if (instance == null) {
            synchronized (SpotifyPlaylistProvider.class) {
                if (instance == null) {
                    instance = new SpotifyPlaylistProvider();
                }
            }
        }

        return instance;
    }

    private String getClientId() {
        return "3d80084d55ed48c2b76596f20c5f7e7d";
    }

    private String getClientSecret() {
        return "1e09b995924e443f9dfa459e7d81f1b8";
    }

}
