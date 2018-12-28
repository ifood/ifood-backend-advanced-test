package com.ifood.backend.advancedtest.spotify.usecases;

import com.ifood.backend.advancedtest.spotify.domain.Playlist;
import com.ifood.backend.advancedtest.spotify.domain.Track;
import com.ifood.backend.advancedtest.spotify.gateway.SpotifyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetSongsByPlaylistUseCase {

    @Autowired
    private SpotifyGateway spotifyGateway;

    public List<Track> execute(Playlist playlist) {
        return spotifyGateway.getTracksByPlaylist(playlist);
    }
}
