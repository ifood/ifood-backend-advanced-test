package com.ifood.backend.advancedtest.service.spotify.client;

import com.ifood.backend.advancedtest.domain.PlaylistResponse;
import com.ifood.backend.advancedtest.util.ApiClient;
import com.ifood.backend.configuration.SpotifyServiceConfiguration;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FunctionalInterface
@FeignClient(name = "services.spotify", url = "${services.spotify.uri}",
        configuration = SpotifyServiceConfiguration.class)
public interface SpotifyPlaylistApiClient extends ApiClient.Api {

    @RequestLine("GET /v1/users/{userId}/playlists/{playlistId}/tracks?fields=items(track(name))&limit_param=10")
    @Headers({"Accept: */*", "Cache-Control: no-cache",
            "Content-Type: application/json"})
    PlaylistResponse getPlayList(@Param("userId") String userId, @Param("playlistId") String playlistId);
    
}
