package ifood.component;

import ifood.model.SpotifyPlaylistResponse;
import ifood.model.SpotifyTracksResponse;
import ifood.model.TrackCategory;

public interface Spotify {

    SpotifyPlaylistResponse getPlaylist(final TrackCategory trackCategory, final String country, final String token);

    SpotifyTracksResponse getTracks(final String playListId, final String token);
}
