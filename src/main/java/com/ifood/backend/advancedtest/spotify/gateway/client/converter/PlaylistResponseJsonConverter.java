package com.ifood.backend.advancedtest.spotify.gateway.client.converter;

import com.ifood.backend.advancedtest.spotify.domain.Playlist;
import com.ifood.backend.advancedtest.spotify.gateway.client.json.PlaylistResponseJson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PlaylistResponseJsonConverter implements Converter<PlaylistResponseJson, Playlist> {

    @Override
    public Playlist convert(PlaylistResponseJson playlistResponseJson) {
        return Playlist.builder()
                .id(playlistResponseJson.getPlaylist().getItems().get(0).getId()).build();
    }
}
