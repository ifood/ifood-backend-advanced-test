package com.ifood.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Lugar implements Serializable {
    @JsonProperty("coord")
    private Coordenadas cordenadas;
    @JsonProperty("main")
    private Clima clima;

}
