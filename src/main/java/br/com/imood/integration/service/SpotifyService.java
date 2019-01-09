package br.com.imood.integration.service;

import br.com.imood.domain.dto.ItemsEnvelopeDTO;
import br.com.imood.domain.dto.PlayListEnvelopeDTO;
import br.com.imood.domain.dto.TrackInfoDTO;
import br.com.imood.integration.configuration.SpotifyFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * For more information see <a href="https://developer.spotify.com/documentation/web-api">SpotifyAPI</a>
 */
@FeignClient(name = "spotify", url = "${spotify.url}", configuration = SpotifyFeignConfiguration.class)
public interface SpotifyService {

    /**
     * Retrieves playlists based on a genre
     * @param category category name
     * @return a list of playlists
     */
    @GetMapping("/browse/categories/{category_id}/playlists?limit=1")
    PlayListEnvelopeDTO getPlayList(@PathVariable("category_id") String category);

    /**
     * Retrieves the tracks of one playlist
     * @param playlistId - id of the playlist
     * @return a list of tracks
     */
    @GetMapping("/playlists/{playlist_id}/tracks")
    ItemsEnvelopeDTO<TrackInfoDTO> getTracks(@PathVariable("playlist_id") String playlistId);

}
