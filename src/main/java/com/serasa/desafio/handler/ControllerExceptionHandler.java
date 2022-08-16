package com.serasa.desafio.handler;

import com.serasa.desafio.handler.exception.BusinessException;
import com.serasa.desafio.handler.exception.EmptyResultDataAccessException;
import com.serasa.desafio.handler.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { EmptyResultDataAccessException.class, DataIntegrityViolationException.class,
			NotFoundException.class })
	public StandardError emptyResultNotFound(RuntimeException e, HttpServletRequest request) {
		return StandardError.builder(HttpStatus.NOT_FOUND.value(), e.getMessage(), new Date());
	}

	@ResponseBody
	@ExceptionHandler(value = { BusinessException.class })
	public ResponseEntity<StandardError> emptyResultNotFound(BusinessException e) {
		StandardError error = StandardError.builder(e.getStatus(), e.getMessage(), new Date());
		return new ResponseEntity<>(error, HttpStatus.valueOf(e.getStatus()));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		StandardError error = StandardError.builder(
				HttpStatus.BAD_REQUEST.value(),
				"Error de validação",
				new Date(),
				ex.getBindingResult().getFieldErrors());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(StandardError.builder(status.value(), ex.getLocalizedMessage(), new Date()),
				headers, status);
	}
}
