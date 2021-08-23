package com.frsummit.HRM.api.server.auth;

public class AuthResult {
	
	private AuthStatus status;
	private String message;
	private AuthErrorCode code;
	
	public AuthResult(AuthStatus status, String message, AuthErrorCode code) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public AuthStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public AuthErrorCode getCode() {
		return code;
	}
}
