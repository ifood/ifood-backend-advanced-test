package ifood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class OpenWeatherResponse {

    private static final String TEMP_NODE = "temp";
    private static final String MAIN_NODE = "main";
    private static final String SYS_NODE = "sys";
    private static final String COUNTRY_NODE = "country";

    private String name;
    private double mainTemp;
    private String country;

    @JsonProperty(MAIN_NODE)
    private void getTemp(Map<String, Double> main) {
        this.mainTemp = main.get(TEMP_NODE);
    }

    @JsonProperty(SYS_NODE)
    private void getCountry(Map<String, String> sys) {
        this.country = sys.get(COUNTRY_NODE);
    }
}
