package ifood.component;

import ifood.dto.SpotifyPlaylistResponse;
import ifood.dto.SpotifyTracksResponse;

public interface Spotify {

    SpotifyPlaylistResponse getPlaylist(final String category, final String country, final String token);

    SpotifyTracksResponse getTracks(final String playListId, final String token);
}
