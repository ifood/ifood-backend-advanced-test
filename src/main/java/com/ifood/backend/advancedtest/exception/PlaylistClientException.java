package com.ifood.backend.advancedtest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public class PlaylistClientException extends HttpClientErrorException {

    public PlaylistClientException(HttpStatus statusCode) {
        super(statusCode);
    }

    public PlaylistClientException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public PlaylistClientException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public PlaylistClientException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }
}
