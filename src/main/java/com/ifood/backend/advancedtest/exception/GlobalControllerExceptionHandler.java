package com.ifood.backend.advancedtest.exception;

import com.ifood.backend.advancedtest.service.spotify.SpotifyErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(SpotifyErrorDecoder.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultErrorHandler(Exception e) throws GeneralException {
        Set<String> messages = new HashSet<>();
        messages.add(e.getMessage());
        logger.error("Unexpected error [{}]",e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GeneralExceptionError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), messages));
    }

    @ExceptionHandler(value = PlaylistClientException.class)
    public ResponseEntity handleClientException(PlaylistClientException e) throws PlaylistClientException {
        Set<String> messages = new HashSet<>();
        messages.add(e.getMessage());
        logger.error("Client error [{}]",e.getMessage(), e);

        return ResponseEntity.status(e.getStatusCode())
                .body(new GeneralExceptionError(e.getStatusCode().value(),
                        e.getStatusCode().getReasonPhrase(), messages));
    }

    @ExceptionHandler(value = PlaylistServerException.class)
    public ResponseEntity handleServerException(PlaylistServerException e) throws PlaylistServerException {
        Set<String> messages = new HashSet<>();
        messages.add(e.getMessage());
        logger.error("Server error [{}]",e.getMessage(), e);

        return ResponseEntity.status(e.getStatusCode())
                .body(new GeneralExceptionError(e.getStatusCode().value(),
                        e.getStatusCode().getReasonPhrase(), messages));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException exception) {
        Set<String> messages = exception.getBindingResult().getFieldErrors()
                .stream()
                .flatMap(c-> Stream.of(c.getField(), c.getDefaultMessage()))
                .collect(Collectors.toSet());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralExceptionError(HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), messages));
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleBadRequest(ConstraintViolationException exception) {
        Set<String> messages = exception.getConstraintViolations()
                .stream()
                .map(c -> String.format("%s value '%s' %s", c.getPropertyPath(),
                        c.getInvalidValue(), c.getMessage()))
                .collect(Collectors.toSet());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralExceptionError(HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), messages));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        Set<String> messages = new HashSet<>();
        String message = String.format("Type error. Parameter '%s', Value: '%s', Expected: %s ",
                e.getName(), e.getValue(), e.getRequiredType());
        messages.add(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralExceptionError(HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), messages));
    }

}
