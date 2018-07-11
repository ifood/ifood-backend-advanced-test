package ifood.component;

import ifood.dto.SpotifyPlaylistResponse;

public interface Spotify {

    SpotifyPlaylistResponse getPlaylist(final String category, final String country, final String token);
}
