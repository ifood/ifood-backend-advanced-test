package br.com.ifood.exceptions;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public abstract class HandlerError {

	public Map<Object, String> getFieldsErrors(Errors errors) {
		return errors.getFieldErrors().stream()
				.collect(Collectors.toMap(erro -> erro.getField(), erro -> erro.getDefaultMessage()));
	}

}