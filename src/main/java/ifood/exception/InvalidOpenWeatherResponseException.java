package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidOpenWeatherResponseException extends BaseException {

    private static final String FULL_MESSAGE = "Erro ao obter dados de clima: [url:%s]";

    public InvalidOpenWeatherResponseException(final String url, final Throwable cause) {
        super(String.format(FULL_MESSAGE, url), cause, ExceptionOriginEnum.OPEN_WEATHER_API);
    }

    public InvalidOpenWeatherResponseException(final String url) {
        super(String.format(FULL_MESSAGE, url), ExceptionOriginEnum.OPEN_WEATHER_API);
    }
}
