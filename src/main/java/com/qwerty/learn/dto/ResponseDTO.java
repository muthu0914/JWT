package com.qwerty.learn.dto;

public class ResponseDTO<T> {

	private Object payload;
	private String message;
	private int statusCode;
	private Boolean isError;
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	
	public ResponseDTO() {
		
	}
	
	public ResponseDTO(Boolean isError,Integer statusCode,String message,Object payload) {
		this.isError = isError;
		this.statusCode = statusCode;
		this.message = message;
		this.payload = payload;
	}
}
