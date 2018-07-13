package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidCityException extends BaseException {

    private static final String FULL_MESSAGE = "Dados não encontrados: [cityname:%s] [lat:%s] [lon:%s]";

    public InvalidCityException(final String cityname, final Double lat, final Double lon, final Throwable cause) {
        super(String.format(FULL_MESSAGE, cityname, lat, lon), cause, ExceptionOriginEnum.INTERNAL);
    }

    public InvalidCityException(final String cityname, final Double lat, final Double lon) {
        super(String.format(FULL_MESSAGE, cityname, lat, lon), ExceptionOriginEnum.INTERNAL);
    }

    public InvalidCityException(final String message, final Throwable cause) {
        super(message, cause, ExceptionOriginEnum.INTERNAL);
    }

    public InvalidCityException(final String message) {
        super(message, ExceptionOriginEnum.INTERNAL);
    }
}
