package com.ToDoList.ToDoList.handler;

import java.time.LocalDateTime;

public class ErrorResponse {

	  private String message;
	  private String resource;
	  private String errorCode;
	  private int status;
	  private LocalDateTime timestamp;

	  public ErrorResponse() {
	    this("","","",0,LocalDateTime.now());
	  }

	  public ErrorResponse(String message, String resource, String errorCode, int status, LocalDateTime timestamp) {
	    this.message = message;
	    this.resource = resource;
	    this.errorCode = errorCode;
	    this.status = status;
	    this.timestamp = timestamp;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	  public String getResource() {
	    return resource;
	  }

	  public void setResource(String resource) {
	    this.resource = resource;
	  }

	  public String getErrorCode() {
	    return errorCode;
	  }

	  public void setErrorCode(String errorCode) {
	    this.errorCode = errorCode;
	  }

	  public int getStatus() {
	    return status;
	  }

	  public void setStatus(int status) {
	    this.status = status;
	  }

	  public LocalDateTime getTimestamp() {
	    return timestamp;
	  }

	  public void setTimestamp(LocalDateTime timestamp) {
	    this.timestamp = timestamp;
	  }

	  @Override
	  public String toString() {
	    return "ErrorResponse{" +
	            "message='" + message + '\'' +
	            ", resource='" + resource + '\'' +
	            ", errorCode='" + errorCode + '\'' +
	            ", status=" + status +
	            ", timestamp=" + timestamp +
	            '}';
	  }
	}
