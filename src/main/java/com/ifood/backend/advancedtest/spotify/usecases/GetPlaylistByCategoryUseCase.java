package com.ifood.backend.advancedtest.spotify.usecases;

import com.ifood.backend.advancedtest.spotify.domain.Playlist;
import com.ifood.backend.advancedtest.spotify.gateway.SpotifyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPlaylistByCategoryUseCase {

    @Autowired
    private SpotifyGateway spotifyGateway;

    public Playlist execute(String category) {
        return spotifyGateway.getPlaylistByCategory(category);
    }
}
