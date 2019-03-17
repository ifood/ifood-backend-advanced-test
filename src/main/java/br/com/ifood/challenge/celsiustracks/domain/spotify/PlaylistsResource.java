package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "playlists"
})
@Data
public class PlaylistsResource {

    @JsonProperty("playlists")
    public Playlists playlists;

    public List<String> getPlaylistsId() {
        return ofNullable(playlists.getPlaylistId()).orElse(emptyList());
    }

    public Integer getTotalPlaylists() {
        return playlists.getTotal();
    }
}
