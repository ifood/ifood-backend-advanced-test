package com.ifood.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Clima implements Serializable {
    @JsonProperty("temp")
    private Double temperatura;


}
