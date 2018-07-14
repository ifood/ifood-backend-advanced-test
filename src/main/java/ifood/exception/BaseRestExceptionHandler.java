package ifood.exception;

import ifood.model.PlaylistErrorReturn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class BaseRestExceptionHandler extends ResponseEntityExceptionHandler {

    private HttpStatus extractHttpStatus(final BaseException e) {
        return Arrays.stream(e.getClass()
                .getAnnotationsByType(ResponseStatus.class))
                .findFirst()
                .map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getMessage(final Exception ex) {
        return StringUtils.isBlank(ex.getMessage()) ? "" : ex.getMessage();
    }

    private void logError(final Exception ex, final String message, final String uri, final ExceptionOriginEnum origin) {
        final StringBuilder finalMessage = new StringBuilder();
        finalMessage
                .append("Mensagem: [").append(message).append("] ")
                .append("URI: [").append(uri).append("] ")
                .append("Origem: [").append(origin).append("] ");

        log.error(finalMessage.toString(), ex);
    }

    private void logError(final HttpServletRequest request,
                          final Exception ex,
                          final String message,
                          final ExceptionOriginEnum origin) {
        final String uri = request.getRequestURI();
        logError(ex, message, uri, origin);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<PlaylistErrorReturn> handleException(final BaseException ex,
                                                               final HttpServletRequest request) {
        logError(request, ex, getMessage(ex), ex.getOrigin());
        final String uri = request.getRequestURI();
        return ResponseEntity.status(extractHttpStatus(ex))
                .body(new PlaylistErrorReturn(ex.getOrigin(), getMessage(ex), uri));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public PlaylistErrorReturn handleException(final Exception ex, final HttpServletRequest request) {
        logError(request, ex, getMessage(ex), ExceptionOriginEnum.INTERNAL);
        final String uri = request.getRequestURI();
        return new PlaylistErrorReturn(ExceptionOriginEnum.INTERNAL, "Internal server error.", uri);
    }

}
