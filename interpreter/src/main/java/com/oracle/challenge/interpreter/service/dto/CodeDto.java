package com.oracle.challenge.interpreter.service.dto;

/**
 * 
 * @author driss
 *
 */
public class CodeDto {
	
	private String language;
	
	private String code;
	
	private String sessionId;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "CodeDto [language=" + language + ", code=" + code + ", sessionId=" + sessionId + "]";
	}
	
}
