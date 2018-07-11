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

    private List<SpotifyTrackData> tracks;

    @JsonProperty("items")
    private void getTrackNames(List<Map<String, Object>> playlists) {
        this.tracks = playlists.stream().map(track -> {
            final Map<String, String> items = (Map<String, String>) track.get("track");
            return new SpotifyTrackData(items.get("name"));
        }).collect(Collectors.toList());
    }
}
