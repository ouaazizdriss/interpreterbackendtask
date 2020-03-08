package com.oracle.challenge.interpreter.service.dto;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

import org.graalvm.polyglot.Context;

public class NoteBookContextDto {

	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private ByteArrayOutputStream outErr = new ByteArrayOutputStream();
	private LocalDateTime sessionStartTime;
	private Context graalContext;
	
	
	public NoteBookContextDto(String language) {
		this.graalContext = Context.newBuilder(language).out(out).err(outErr).build();
		this.sessionStartTime = LocalDateTime.now();
	}


	public ByteArrayOutputStream getOut() {
		return out;
	}


	public void setOut(ByteArrayOutputStream out) {
		this.out = out;
	}


	public ByteArrayOutputStream getOutErr() {
		return outErr;
	}


	public void setOutErr(ByteArrayOutputStream outErr) {
		this.outErr = outErr;
	}


	public Context getGraalContext() {
		return graalContext;
	}


	public void setGraalContext(Context graalContext) {
		this.graalContext = graalContext;
	}


	public LocalDateTime getSessionStartTime() {
		return sessionStartTime;
	}


	public void setSessionStartTime(LocalDateTime sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	
}
