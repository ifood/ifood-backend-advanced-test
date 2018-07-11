package ifood.dto;

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

    private List<String> playlistIds;

    @JsonProperty("playlists")
    private void getPlaylistsIds(Map<String, Object> playlists) {
        this.playlistIds = ((ArrayList<Map<String, String>>) playlists.get("items"))
                .stream().map(item -> item.get("id"))
                .collect(Collectors.toList());
    }
}
