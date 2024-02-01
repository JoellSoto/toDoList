package com.ToDoList.ToDoList.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ToDoList.ToDoList.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionsHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handleUsernameNotFoundException(){
		return ResponseEntity.badRequest().body("Incorrect user/Password!");
	}
	

	  @ExceptionHandler(ResourceNotFoundException.class)
	  @ResponseBody
	  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
	    ErrorResponse errorResponse = new ErrorResponse();
	    errorResponse.setMessage(ex.getMessage());
	    errorResponse.setResource(ex.getResource());
	    errorResponse.setErrorCode(ex.getErrorCode());
	    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	    errorResponse.setTimestamp(LocalDateTime.now());
	    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	  }	
}
