package com.oracle.challenge.interpreter.utils;

import java.util.UUID;

import org.springframework.util.StringUtils;

import com.oracle.challenge.interpreter.api.dto.Code;
import com.oracle.challenge.interpreter.service.dto.CodeDto;

public class NoteBookUtils {
	
	private static final String SPACE_SEPARATOR = " ";

	private NoteBookUtils() {
		//utils class no need for public constructor
	}
	
	public static String getLanguage(final Code code) {
		
		String language = null;
		if(!StringUtils.isEmpty(code.getCode())){
			language = code.getCode().split(SPACE_SEPARATOR)[0].substring(1);
		}
		return language;
	}
	
	public static String getCode(final Code code) {
		String executableCode = null;
		if(!StringUtils.isEmpty(code.getCode())){
			executableCode = code.getCode()
					.substring(code.getCode().indexOf(SPACE_SEPARATOR)+1);
		}
		return executableCode;
	}
	
	public static String getSessionId(final Code code, String ip) {
		String sessionId = null;
		if(!StringUtils.isEmpty(code.getSessionId())){
			sessionId = code.getSessionId();
		}else if(StringUtils.isEmpty(code.getSessionId()) && !StringUtils.isEmpty(ip)){
			sessionId = ip;
		}else {
			sessionId = UUID.randomUUID().toString();
		}
		return sessionId;
	}

	public static CodeDto extractDto(final Code code, String ip) {
		final CodeDto dto = new CodeDto();
		dto.setSessionId(getSessionId(code, ip));
		dto.setCode(getCode(code));
		dto.setLanguage(getLanguage(code));
		
		return dto;
	}

}
