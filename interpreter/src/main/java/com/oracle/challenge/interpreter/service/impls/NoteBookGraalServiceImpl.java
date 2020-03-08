package com.oracle.challenge.interpreter.service.impls;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.oracle.challenge.interpreter.api.dto.Result;
import com.oracle.challenge.interpreter.service.dto.CodeDto;
import com.oracle.challenge.interpreter.service.dto.NoteBookContextDto;
import com.oracle.challenge.interpreter.service.interfaces.NoteBookService;

/**
 * GraalVM implementation for notebook interpretation
 * 
 * @author driss
 *
 */
@Service
public class NoteBookGraalServiceImpl implements NoteBookService{
	
	private final Map<String, NoteBookContextDto> sessions = new TreeMap<>();
	
	@Value("${notebook.server.timeout}")
	private String serverTimeout;
	
	@Value("${notebook.server.session.ttl}")
	private String sessionTtl;
	
	@Value("${notebook.server.language.supported}")
	private String supportedLanguages;
	
	
	@Override
	public Result execute(final CodeDto code) {
		
		final Result result = new Result();
		result.setSessionId(code.getSessionId());
		
		if(!supportedLanguages.toLowerCase().contains(code.getLanguage().toLowerCase())) {
			result.setResult(code.getLanguage() + " is not supported for the moment.");
			return result;
		}
		
		final NoteBookContextDto graalContext;
		
		if(sessions.containsKey(code.getSessionId())) {
			graalContext = sessions.get(code.getSessionId());
		}else {
			graalContext = new NoteBookContextDto(code.getLanguage());
			sessions.put(code.getSessionId(), graalContext);
		}
		graalContext.setSessionStartTime(LocalDateTime.now());
		
		try {
			
			final Runnable exec = () -> {
				graalContext.getGraalContext().eval(code.getLanguage(), code.getCode());
				result.setResult(graalContext.getOut().toString());
				graalContext.getOut().reset();
			};
			
			final ExecutorService executor = Executors.newSingleThreadExecutor();
			final Future<?> future = executor.submit(exec);
			executor.shutdown();

			try { 
			  future.get(Long.parseLong(serverTimeout), TimeUnit.SECONDS); 
			}catch (TimeoutException e) { 
			  result.setResult("Code is taking too long to execute.");
			}
			
			if (!executor.isTerminated())
			    executor.shutdownNow();
				
		}catch(Exception e) {
			result.setResult(e.getMessage());
		}
		return result;
	}
	
	@Scheduled(fixedDelayString = "${notebook.server.session.job.exec}")
	@Override
	@Async
	public void removeDeadSessions() {
		System.out.println("this.sessionTtl: " + this.sessionTtl);
		System.out.println("Session remove start: " + LocalDateTime.now()+", sessions: " + sessions.keySet());
		this.sessions.entrySet().removeIf(e->  ChronoUnit.MILLIS.between(
			e.getValue().getSessionStartTime(), LocalDateTime.now()) > Integer.parseInt(this.sessionTtl) 
		);
		System.out.println("Session remove end: " + LocalDateTime.now()+", sessions: " + sessions.keySet());
	}

}
