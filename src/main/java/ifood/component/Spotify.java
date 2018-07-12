package ifood.component;

import ifood.dto.SpotifyPlaylistResponse;
import ifood.dto.SpotifyTracksResponse;
import ifood.dto.TrackCategory;

public interface Spotify {

    SpotifyPlaylistResponse getPlaylist(final TrackCategory trackCategory, final String country, final String token);

    SpotifyTracksResponse getTracks(final String playListId, final String token);
}
