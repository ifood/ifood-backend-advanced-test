package ifood.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherResponse {

    private final String cityname;
    private final Double temp;
    private final String country;
}
