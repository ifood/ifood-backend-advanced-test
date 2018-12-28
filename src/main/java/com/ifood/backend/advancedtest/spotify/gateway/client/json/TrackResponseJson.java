package com.ifood.backend.advancedtest.spotify.gateway.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TrackResponseJson {

    @JsonProperty("items")
    private List<TrackItemJson> items;
}
