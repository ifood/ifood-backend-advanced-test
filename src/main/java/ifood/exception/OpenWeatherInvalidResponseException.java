package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class OpenWeatherInvalidResponseException extends BaseException {

    private static final String FULL_MESSAGE = "Erro ao obter dados de clima: [url:%s]";

    public OpenWeatherInvalidResponseException(final String url, final Throwable cause) {
        super(String.format(FULL_MESSAGE, url), cause, ExceptionOriginEnum.OPEN_WEATHER_API);
    }

    public OpenWeatherInvalidResponseException(final String url) {
        super(String.format(FULL_MESSAGE, url), ExceptionOriginEnum.OPEN_WEATHER_API);
    }
}
