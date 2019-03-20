package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import static java.util.Optional.ofNullable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "playlists"
})
@Data
public class PlaylistsResource {

    @JsonProperty("playlists")
    private Playlists playlists;

    public Integer getTotalPlaylists() {
        return ofNullable(playlists).map(Playlists::getTotal).orElse(0);
    }
}
