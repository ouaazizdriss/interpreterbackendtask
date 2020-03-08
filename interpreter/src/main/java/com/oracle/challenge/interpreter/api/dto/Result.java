package com.oracle.challenge.interpreter.api.dto;

/**
 * Object representing the result of the execution 
 * of a code on the NoteBook server.
 * 
 * @author driss
 *
 */
public class Result {
	
	/**
	 * Execution result.
	 */
	private String result;
	
	/**
	 * Session ID in which the code was executed.
	 */
	private String sessionId;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
