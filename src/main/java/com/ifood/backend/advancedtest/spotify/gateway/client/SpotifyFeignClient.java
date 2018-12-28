package com.ifood.backend.advancedtest.spotify.gateway.client;

import com.ifood.backend.advancedtest.spotify.config.interceptor.SpotifyFeignClientConfiguration;
import com.ifood.backend.advancedtest.spotify.gateway.client.json.PlaylistResponseJson;
import com.ifood.backend.advancedtest.spotify.gateway.client.json.TrackResponseJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${spotify.service.name}", url = "${spotify.service.url}", configuration = SpotifyFeignClientConfiguration.class)
public interface SpotifyFeignClient {

    @GetMapping("/browse/categories/{category_id}/playlists?country=BR&limit=1")
    PlaylistResponseJson getPlaylistsByCategory(@RequestParam(value = "category_id") String category);

    @GetMapping("/playlists/{playlist_id}/tracks?limit=5")
    TrackResponseJson getTracksByPlaylistId(@RequestParam(value = "playlist_id") String playlistId);
}
