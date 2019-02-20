package com.ifood.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Place implements Serializable {
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("main")
    private Weather weather;

}
