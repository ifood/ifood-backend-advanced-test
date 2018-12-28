package com.ifood.backend.advancedtest.openWeather.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {

    private float temperature;
}
