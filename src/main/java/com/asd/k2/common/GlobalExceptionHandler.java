package com.asd.k2.common;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ValidationErrorResponse.FieldError> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(this::toFieldError)
				.toList();
		return ResponseEntity.badRequest().body(new ValidationErrorResponse(
				"VALIDATION_ERROR", "请求体参数校验失败", errors));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ValidationErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(new ValidationErrorResponse(
				"BAD_REQUEST", ex.getMessage(), List.of()));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ValidationErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
		List<ValidationErrorResponse.FieldError> errors = ex.getConstraintViolations().stream()
				.map(this::toFieldError)
				.toList();
		return ResponseEntity.badRequest().body(new ValidationErrorResponse(
				"VALIDATION_ERROR", "请求参数校验失败", errors));
	}

	private ValidationErrorResponse.FieldError toFieldError(FieldError error) {
		return new ValidationErrorResponse.FieldError(error.getField(), error.getDefaultMessage());
	}

	private ValidationErrorResponse.FieldError toFieldError(ConstraintViolation<?> violation) {
		String path = violation.getPropertyPath().toString();
		String field = path.contains(".") ? path.substring(path.lastIndexOf('.') + 1) : path;
		return new ValidationErrorResponse.FieldError(field, violation.getMessage());
	}
}
