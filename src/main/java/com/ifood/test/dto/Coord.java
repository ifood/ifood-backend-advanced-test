package com.ifood.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Coord implements Serializable {

    @JsonProperty("lon")
    private Double longitude;
    @JsonProperty("lat")
    private Double latitude;

}
