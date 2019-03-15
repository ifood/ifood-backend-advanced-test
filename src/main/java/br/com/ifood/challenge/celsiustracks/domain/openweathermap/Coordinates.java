package br.com.ifood.challenge.celsiustracks.domain.openweathermap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lon",
        "lat"
})
public class Coordinates {

    @JsonProperty("lon")
    public Double lon;
    @JsonProperty("lat")
    public Double lat;

}
