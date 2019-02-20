package com.ifood.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Weather implements Serializable {
    @JsonProperty("temp")
    private Double temperature;


}
