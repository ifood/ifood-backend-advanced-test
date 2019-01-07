package com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.bo;

import java.util.List;

import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.exception.BusinessException;
import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist.GenreEnum;
import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist.SpotifyPlaylistProvider;

/**
 * Business class to handle playlist business logic
 * 
 * @author ffrazato
 */
public class PlaylistBO {
    /**
     * Retrieve playlist sound track names by genre
     * 
     * @param genre
     * @return soud track names
     */
    public List<String> getPlaylisSoundtrackNamesByGenre(GenreEnum genre) {
        List<String> playlistTrackNames = null;

        try {
            // get playlist for chosen genre
            playlistTrackNames = SpotifyPlaylistProvider.getInstance().getPlaylistTracksByGenres(genre);

        } catch (Exception e) {
            // TODO: LOG THE ERROR
            throw new BusinessException("Problems while getting playlist from provider", e);
        }

        return playlistTrackNames;
    }
}
