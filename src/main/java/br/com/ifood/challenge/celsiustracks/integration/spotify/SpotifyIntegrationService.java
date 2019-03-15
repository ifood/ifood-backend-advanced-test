package br.com.ifood.challenge.celsiustracks.integration.spotify;

import br.com.ifood.challenge.celsiustracks.domain.spotify.CategoriesResource;
import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistsResource;
import br.com.ifood.challenge.celsiustracks.domain.spotify.TracksResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @see "https://developer.spotify.com/documentation/web-api/reference/browse/"
 */
@FeignClient(name = "spotify-integration", url = "${spotify.api.url}", fallback = SpotifyIntegrationFallbackServiceImpl.class)
public interface SpotifyIntegrationService {
    @GetMapping(value = "/browse/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoriesResource getCategories(@RequestParam(value = "offset", defaultValue = "0")final Integer offset,
                                     @RequestParam(value = "limit", defaultValue = "10")final Integer limit);

    @GetMapping(value = "/browse/categories/{category_id}/playlists", produces = MediaType.APPLICATION_JSON_VALUE)
    PlaylistsResource getPlaylistsByCategory(@PathVariable("category_id")final String categoryId,
                                             @RequestParam(value = "offset", defaultValue = "0")final Integer offset,
                                             @RequestParam(value = "limit", defaultValue = "10")final Integer limit);

    @GetMapping(value = "/playlists/{playlist_id}/tracks", produces = MediaType.APPLICATION_JSON_VALUE)
    TracksResource getTracksByPlaylist(@PathVariable("playlist_id")final String playlistId,
                                       @RequestParam(value = "offset", defaultValue = "0")final Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "10")final Integer limit);


}
