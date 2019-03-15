package br.com.ifood.challenge.celsiustracks.domain.openweathermap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

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
    public Double temp;
    @JsonProperty("humidity")
    public Double humidity;
    @JsonProperty("pressure")
    public Double pressure;
    @JsonProperty("temp_min")
    public Double tempMin;
    @JsonProperty("temp_max")
    public Double tempMax;

}
