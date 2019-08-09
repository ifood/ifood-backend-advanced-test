package br.com.ariki.music.suggestion.by.weather.gateway.controller.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponseEntity {

	private LocalDateTime timestamp;
	private String error;
	private ErrorDetail errorDetail;
}
