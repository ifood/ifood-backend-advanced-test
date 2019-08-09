package br.com.ariki.music.suggestion.by.weather.gateway.controller.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDetail {
	
	private String message;
	private String suggestion;

}
