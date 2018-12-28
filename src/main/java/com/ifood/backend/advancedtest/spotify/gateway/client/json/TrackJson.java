package com.ifood.backend.advancedtest.spotify.gateway.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrackJson {

    @JsonProperty("name")
    private String name;
}
