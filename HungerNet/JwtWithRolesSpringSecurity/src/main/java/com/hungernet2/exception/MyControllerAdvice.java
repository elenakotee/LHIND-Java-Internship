package com.hungernet2.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	private final Logger logger = LogManager.getLogger();

	@ExceptionHandler(ChangeSetPersister.NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException notFoundException) {
		logger.error("NotFoundException \n");
		return new ResponseEntity<>("Handle not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException) {
		logger.error("Null Pointer Exception \n");
		return new ResponseEntity<>("null pointer exception", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
		logger.error("Constraint Violation Exception \n");
		return new ResponseEntity<>("Constraint violation", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {
		logger.error("Empty input field \n");
		return new ResponseEntity<>("Input field is empty", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ServletException.class)
	public ResponseEntity<String> handleServletException(ServletException servletException) {
		logger.error("Servlet exception \n");
		return new ResponseEntity<>("Servlet Exception", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(IOException ioException) {
		logger.error("IOException exception \n");
		return new ResponseEntity<>("IOException Exception", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException expiredJwtException) {
		logger.error("Token is expired \n");
		return new ResponseEntity<>("Your token is expired", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
		logger.error("Illegal argument exception \n");
		return new ResponseEntity<>("Illegal argument exception", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
		logger.error("Username isnt valid \n");
		return new ResponseEntity<>("Username not valid", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<String> handleDisabledException(DisabledException disabledException) {
		logger.error("User has been disabled \n");
		return new ResponseEntity<>("User has been disabled", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException badCredentialsException) {
		logger.error("Bad credentials from user \n");
		return new ResponseEntity<>("Bad credentials from user", HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		logger.error("Not such element exception \n");
		return new ResponseEntity<>("No data found in database", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException) {
		logger.error("Can't delete inexistent data exception \n");
		return new ResponseEntity<>("Can't delete inexistent data", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoCredentialsException.class)
	public ResponseEntity<String> handleNoCredentialsException(NoCredentialsException noCredentialsException) {
		logger.error("No credentials \n");
		return new ResponseEntity<>("You don't have the credentials for this action", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
		logger.error("Access denied \n");
		return new ResponseEntity<>("Access has been denied", HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<String> handleValidationException(ValidationException validationException) {
		logger.error("Validation exception \n");
		return new ResponseEntity<>("Could not validate handle", HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Unsupported method \n");
		return new ResponseEntity<>("Please change you http method type", HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Missing path variable \n");
		return new ResponseEntity<>("You are missing a path variable", HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Http message not readable \n");
		return new ResponseEntity<>("Your message is not readable", HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Invalid method argument \n");
		return new ResponseEntity<>("Your method argument is invalid", HttpStatus.BAD_REQUEST);
	}



}