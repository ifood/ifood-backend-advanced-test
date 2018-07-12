package ifood.component;

import ifood.model.SpotifyPlaylistResponse;
import ifood.model.SpotifyTracksResponse;
import ifood.model.TrackCategoryEnum;

public interface Spotify {

    SpotifyPlaylistResponse getPlaylist(final TrackCategoryEnum trackCategoryEnum, final String country, final String token);

    SpotifyTracksResponse getTracks(final String playListId, final String token);
}
