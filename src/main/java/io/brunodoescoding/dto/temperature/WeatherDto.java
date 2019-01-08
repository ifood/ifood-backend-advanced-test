package io.brunodoescoding.dto.temperature;

import lombok.Data;

import java.util.Map;

@Data
public class WeatherDto {
    private Map<String, Double> main;
}
