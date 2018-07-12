package ifood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class SpotifyPlaylistResponse {

    private static final String PLAYLISTS_NODE = "playlists";
    private static final String ITEMS_NODE = "items";
    private static final String ID_NODE = "id";

    private List<String> playlistIds;

    @JsonProperty(PLAYLISTS_NODE)
    private void getPlaylistsIds(Map<String, Object> playlists) {
        this.playlistIds = ((ArrayList<Map<String, String>>) playlists.get(ITEMS_NODE))
                .stream().map(item -> item.get(ID_NODE))
                .collect(Collectors.toList());
    }
}
