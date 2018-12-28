package com.ifood.backend.advancedtest.openWeather.gateway.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherJson {

    @JsonProperty("main")
    private WeatherMainInfoJson weatherMainInfoJson;
}
