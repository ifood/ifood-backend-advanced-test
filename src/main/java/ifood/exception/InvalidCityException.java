package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCityException extends BaseException {

    public InvalidCityException(final String message, final Throwable cause) {
        super(message, cause, ExceptionOriginEnum.INTERNAL);
    }

    public InvalidCityException(final String message) {
        super(message, ExceptionOriginEnum.INTERNAL);
    }
}
