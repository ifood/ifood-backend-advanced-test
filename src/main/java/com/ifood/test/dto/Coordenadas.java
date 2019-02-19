package com.ifood.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Coordenadas implements Serializable {

    @JsonProperty("lon")
    private Double longitud;
    @JsonProperty("lat")
    private Double latitud;

}
