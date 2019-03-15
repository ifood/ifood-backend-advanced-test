package br.com.ifood.challenge.celsiustracks.domain.openweathermap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "coord",
        "sys",
        "weather",
        "main",
        "wind",
        "rain",
        "clouds",
        "dt",
        "id",
        "name",
        "cod"
})
public class WeatherResource {

    @JsonProperty("coord")
    public Coordinates coord;
//    @JsonProperty("sys")
//    public Sys sys;
//    @JsonProperty("weather")
//    public List<Weather> weather = null;
    @JsonProperty("main")
    public Weather main;
//    @JsonProperty("wind")
//    public Wind wind;
//    @JsonProperty("rain")
//    public Rain rain;
//    @JsonProperty("clouds")
//    public Clouds clouds;
    @JsonProperty("dt")
    public Integer dataCalculationTime;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("cod")
    public Integer cod;

    public Double getCurrentTemperature() {
        return main.getTemp();
    }
}

