package com.oracle.challenge.interpreter.service.interfaces;

import com.oracle.challenge.interpreter.api.dto.Result;
import com.oracle.challenge.interpreter.service.dto.CodeDto;

/**
 * Interface to abstract implementation of the notebook interpretation 
 * implementations (ex: GraalVM)
 * 
 * @author driss
 *
 */
public interface NoteBookService {
	
	public Result execute(CodeDto codeDto);
	
	public void removeDeadSessions();

}
