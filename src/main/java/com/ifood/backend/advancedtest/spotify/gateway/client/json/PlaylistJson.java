package com.ifood.backend.advancedtest.spotify.gateway.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistJson {

    @JsonProperty("items")
    private List<PlaylistItemJson> items;
}
