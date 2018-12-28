package com.ifood.backend.advancedtest.spotify.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Playlist {

    @JsonProperty("id")
    private String id;
}
