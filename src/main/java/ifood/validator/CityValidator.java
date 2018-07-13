package ifood.validator;

import ifood.exception.InvalidCityException;
import org.apache.commons.lang3.StringUtils;

public class CityValidator {

    private static final double LAT_MIN = -90;
    private static final double LAT_MAX = 90;
    private static final double LON_MIN = -180;
    private static final double LON_MAX = 180;

    public static void validate(final String cityname, final Double lat, final Double lon) {
        if (StringUtils.isNotBlank(cityname)) {
            return;
        }

        if (lat == null || lon == null) {
            throw new InvalidCityException(
                    String.format("Dados de localização inválidos: [lat:%s] [lon:%s]", lat, lon));
        }

        if (lat < LAT_MIN || lat > LAT_MAX) {
            throw new InvalidCityException(
                    String.format("Valor de latitude inválido: [lat:%s]", lat));
        }

        if (lon < LON_MIN || lon > LON_MAX) {
            throw new InvalidCityException(
                    String.format("Valor de longitude inválido: [lon:%s]", lon));
        }
    }
}
