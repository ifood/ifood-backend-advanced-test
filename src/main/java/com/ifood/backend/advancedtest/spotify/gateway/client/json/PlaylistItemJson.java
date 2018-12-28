package com.ifood.backend.advancedtest.spotify.gateway.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaylistItemJson {

    @JsonProperty("id")
    public String id;
}
