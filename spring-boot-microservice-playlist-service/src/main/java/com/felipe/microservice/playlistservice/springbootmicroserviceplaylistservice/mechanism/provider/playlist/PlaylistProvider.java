package com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist;

import java.util.List;

/**
 * Contract for playlist providers
 * @author ffrazato
 *
 */
public interface PlaylistProvider {
    /**
     * Given a playlist genre it retrieves the playlist tracks
     * @param genre
     * @return
     * @throws Exception 
     */
    public List<String> getPlaylistTracksByGenres(GenreEnum genre) throws Exception; 
}
