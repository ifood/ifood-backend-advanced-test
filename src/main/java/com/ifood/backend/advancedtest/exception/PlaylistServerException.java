package com.ifood.backend.advancedtest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.nio.charset.Charset;

public class PlaylistServerException extends HttpServerErrorException {

    public PlaylistServerException(HttpStatus statusCode) {
        super(statusCode);
    }

    public PlaylistServerException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public PlaylistServerException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public PlaylistServerException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }
}
