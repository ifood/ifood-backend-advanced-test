package ifood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class OpenWeatherResponse {

    private String name;

    private double mainTemp;

    @JsonProperty("main")
    private void nestedTemp(Map<String, Double> main) {
        this.mainTemp = main.get("temp");
    }
}
