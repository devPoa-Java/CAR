package com.drivercar.democar.domain.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

@RestControllerAdvice
public class DefaultErrorHandler {
	
	@ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
	public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ErrorData> messages = ex
				.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fe -> new ErrorData(fe.getDefaultMessage()))
				.collect(Collectors.toList());
	
		return new ErrorResponse(messages);
	}

}
