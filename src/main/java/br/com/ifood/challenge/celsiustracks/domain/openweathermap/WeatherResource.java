package br.com.ifood.challenge.celsiustracks.domain.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResource {

    @JsonProperty("coord")
    private Coordinates coord;
    @JsonProperty("main")
    private Weather weather;
    @JsonProperty("dt")
    private Integer dataCalculationTime;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cod")
    private Integer cod;

    public static WeatherResource ofFallback() {
        final WeatherResource fallbackWeather = new WeatherResource();
        fallbackWeather.weather = Weather.ofFallback();
        return fallbackWeather;
    }

    public Double getCurrentTemperature() {
        return weather.getTemp();
    }
}

