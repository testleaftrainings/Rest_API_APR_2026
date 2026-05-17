package com.testleaf.matschie.constant.utils;

public enum Status {
	
	OK(200, "HTTP/1.1 200 OK"),
	CREATED(201, "HTTP/1.1 201 Created"),
	NO_CONTENT(204, "HTTP/1.1 204 No Content"),
	BAD_REQUEST(400, "HTTP/1.1 400 Bad Request"),
	UNAUTHORIZED(401, "HTTP/1.1 401 Unauthorized"),
	FORBIDDEN(403, "HTTP/1.1 403 Forbidden"),
	NOT_FOUND(404, "HTTP/1.1 404 Not Found"),
	METHOD_NOT_ALLOWED(405, "HTTP/1.1 405 Method Not Allowed"),
	INTERNAL_SERVER_ERROR(500, "HTTP/1.1 500 Internal Server Error"),
	SERVICE_UNAVAILABLE(503, "HTTP/1.1 503 Service Unavailable");
	
	private final int code;
	private final String message;
	
	Status(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

}