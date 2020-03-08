package com.oracle.challenge.interpreter.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Object representing the 
 * Code to be executed by the NoteBook server and the sessionID
 * to keep results in memory for reuse during à duration to be defined
 * as property. 
 * @author driss
 *
 */
public class Code {
	
	@NotBlank
	@NotNull
	@Pattern(regexp="%[a-zA-Z]+[\\W\\w]+", message="Invalid Code (ex: %<interpreter-name><whitespace><code>')")  
	private String code;
	
	private String sessionId;
	

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
}
