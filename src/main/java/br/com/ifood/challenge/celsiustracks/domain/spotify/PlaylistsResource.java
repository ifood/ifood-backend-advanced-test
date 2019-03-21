package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static java.util.Optional.ofNullable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistsResource {

    @JsonProperty("playlists")
    private Playlists playlists;

    public Integer getTotalPlaylists() {
        return ofNullable(playlists).map(Playlists::getTotal).orElse(0);
    }
}
