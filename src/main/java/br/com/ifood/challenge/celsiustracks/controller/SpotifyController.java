package br.com.ifood.challenge.celsiustracks.controller;


import br.com.ifood.challenge.celsiustracks.domain.spotify.CategoriesResource;
import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistsResource;
import br.com.ifood.challenge.celsiustracks.integration.spotify.SpotifyIntegrationService;
import br.com.ifood.challenge.celsiustracks.domain.spotify.TracksResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "API only for Spotify test purpose")
@RestController
@RequestMapping("/spotify")
@AllArgsConstructor
public class SpotifyController {

	private SpotifyIntegrationService spotifyIntegrationService;

	@ApiOperation(value = "Retrieve all playlist categories")
	@GetMapping(value = "/categories")
	public ResponseEntity<?> getCategories(
			@RequestParam(value = "offset", defaultValue = "0")final Long offset,
			@RequestParam(value = "limit", defaultValue = "10")final Integer limit) {
		final CategoriesResource categoriesResource = spotifyIntegrationService.getCategories(offset, limit);
		return ResponseEntity.ok(categoriesResource);
	}

	@ApiOperation(value = "Retrieve a list of playlist by category id")
	@GetMapping(value = "/categories/{category_id}/playlists")
	public ResponseEntity<?> getPlaylistsByCategory(@PathVariable("category_id") final String categoryId,
			@RequestParam(value = "offset", defaultValue = "0")final Long offset,
			@RequestParam(value = "limit", defaultValue = "10")final Integer limit) {
		final PlaylistsResource playlistsResource = spotifyIntegrationService.getPlaylistsByCategory(categoryId, offset, limit);
		return ResponseEntity.ok(playlistsResource);
	}

	@ApiOperation(value = "Retrieve a list of tracks by playlist id")
	@GetMapping(value = "/playlists/{playlist_id}/tracks")
	public ResponseEntity<?> getTracksByPlaylist(@PathVariable("playlist_id") final String playlistId,
			@RequestParam(value = "offset", defaultValue = "0")final Long offset,
			@RequestParam(value = "limit", defaultValue = "10")final Integer limit) {
		final TracksResource tracksResource = spotifyIntegrationService.getTracksByPlaylist(playlistId, offset, limit);
		return ResponseEntity.ok(tracksResource);
	}
}
