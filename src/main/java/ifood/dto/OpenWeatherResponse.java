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

    private String country;

    @JsonProperty("main")
    private void getTemp(Map<String, Double> main) {
        this.mainTemp = main.get("temp");
    }

    @JsonProperty("sys")
    private void getCountry(Map<String, String> sys) {
        this.country = sys.get("country");
    }
}
