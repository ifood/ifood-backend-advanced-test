package com.ifood.test.service.impl;


import com.ifood.test.service.SpotifyService;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpotifyServiceImpl implements SpotifyService {


    @SneakyThrows
    public List<String> getTrackNames( String id) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId("67d730cddec742aa9bb24d1f3cf83ccb")
                .setClientSecret("bd1fafce1dae4013994a9faceb690b4b")
                .build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();
        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi
                .getPlaylistsTracks(id)
                .limit(10)
                .offset(0)
                .market(CountryCode.SE)
                .build();
        Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();
        return Arrays.stream(playlistTrackPaging.getItems()).map(playlistTrack -> playlistTrack.getTrack().getName()).collect(Collectors.toList());

    }
}
