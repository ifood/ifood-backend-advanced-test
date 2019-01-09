package br.com.imood.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WeatherMainDTO {

    @JsonProperty("temp")
    private Double temperature;

}
