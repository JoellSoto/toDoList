package com.ToDoList.ToDoList.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceNotFoundException extends RuntimeException {

	  private String message;
	  private int status;
	  private String errorCode;

	  private String resource;

	  public ResourceNotFoundException(String resource, int status, String errorCode,String message) {
	    super(String.format("%s not found with error code %s", resource, HttpStatus.INTERNAL_SERVER_ERROR.name()));
	    this.resource = resource;
	    this.status = status;
	    this.message=message;
	    this.errorCode=errorCode;
	  }

	  public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getResource() {
	    return resource;
	  }

	  public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
	    return errorCode;
	  }
	}