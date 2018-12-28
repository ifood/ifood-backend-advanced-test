package com.ifood.backend.advancedtest.spotify.gateway;

import com.ifood.backend.advancedtest.spotify.domain.Playlist;
import com.ifood.backend.advancedtest.spotify.domain.Track;
import com.ifood.backend.advancedtest.spotify.gateway.client.SpotifyFeignClient;
import com.ifood.backend.advancedtest.spotify.gateway.client.converter.PlaylistResponseJsonConverter;
import com.ifood.backend.advancedtest.spotify.gateway.client.converter.TrackResponseJsonConverter;
import com.ifood.backend.advancedtest.spotify.gateway.client.json.PlaylistResponseJson;
import com.ifood.backend.advancedtest.spotify.gateway.client.json.TrackResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpotifyGateway {

    @Autowired
    private SpotifyFeignClient spotifyFeignClient;

    @Autowired
    private PlaylistResponseJsonConverter playlistResponseJsonConverter;

    @Autowired
    private TrackResponseJsonConverter trackResponseJsonConverter;


    public Playlist getPlaylistByCategory(String category) {

        PlaylistResponseJson playlistResponseJson = spotifyFeignClient.getPlaylistsByCategory(category);
        Playlist playlist = playlistResponseJsonConverter.convert(playlistResponseJson);

        return playlist;
    }

    public List<Track> getTracksByPlaylist(Playlist playlist) {
        TrackResponseJson trackResponseJson = spotifyFeignClient.getTracksByPlaylistId(playlist.getId());
        List<Track> tracks = trackResponseJsonConverter.convert(trackResponseJson);

        return tracks;
    }

}
