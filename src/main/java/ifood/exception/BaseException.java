package ifood.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends RuntimeException {

    @Getter
    private final ExceptionOriginEnum origin;

    public BaseException(final String message, final Throwable cause, final ExceptionOriginEnum origin) {
        super(message, cause);
        this.origin = origin;
    }

    public BaseException(final String message, final ExceptionOriginEnum origin) {
        super(message);
        this.origin = origin;
    }
}
