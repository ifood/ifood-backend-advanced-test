package ifood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class SpotifyTracksResponse {

    private static final String ITEM_NODE = "item";
    private static final String TRACK_NODE = "track";
    private static final String NAME_NODE = "name";

    private List<SpotifyTrackData> tracks;

    @JsonProperty(ITEM_NODE)
    private void getTrackNames(List<Map<String, Object>> playlists) {
        this.tracks = playlists.stream().map(track -> {
            final Map<String, String> items = (Map<String, String>) track.get(TRACK_NODE);
            return new SpotifyTrackData(items.get(NAME_NODE));
        }).collect(Collectors.toList());
    }
}
