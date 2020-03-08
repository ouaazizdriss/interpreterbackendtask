package com.oracle.challenge.interpreter.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.challenge.interpreter.api.dto.Code;
import com.oracle.challenge.interpreter.api.dto.Result;
import com.oracle.challenge.interpreter.service.interfaces.NoteBookService;
import com.oracle.challenge.interpreter.utils.NoteBookUtils;

@RestController
@CrossOrigin("*")
public class NoteBookControllerApi {
	
	@Autowired
	private NoteBookService noteBookService;

	/**
	 * Endpoint for executing code on the NoteBook Server.
	 * 
	 * @param code code to be executed
	 * 
	 * @return result of the execution of the code on the server
	 */
	@PostMapping("/execute")
	public Result execute (@Valid @RequestBody Code code, HttpServletRequest request) { 
		return noteBookService.execute(NoteBookUtils.extractDto(code, request.getRemoteAddr()));
	}
	
}
