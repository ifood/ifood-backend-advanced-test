package br.com.ifood.challenge.celsiustracks.domain.openweathermap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Random;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "temp",
        "humidity",
        "pressure",
        "temp_min",
        "temp_max"
})
public class Weather {

    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("humidity")
    public Double humidity;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;

    public static Weather ofFallback() {
        final Weather fallbackWeather = new Weather();
        fallbackWeather.temp = Double.valueOf(new Random().nextInt(40));
        return fallbackWeather;
    }
}
