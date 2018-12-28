package com.ifood.backend.advancedtest.openWeather.gateway.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherMainInfoJson {

    @JsonProperty("temp")
    private float temperature;
}
