package com.oracle.challenge.interpreter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteBookControllerApiTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void shouldReturnHello() throws Exception {
		final String content = "{\"code\": \"%python print('Hello')\"}";
		this.mockMvc.perform(
					post("/execute")
					.contentType(MediaType.APPLICATION_JSON)
					.content(content))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("Hello")));
	}
	
	@Test
	public void shouldReturnSumm() throws Exception {
		final String content = "{\"code\": \"%python print(1+1)\"}";
		this.mockMvc.perform(
					post("/execute")
					.contentType(MediaType.APPLICATION_JSON)
					.content(content))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("2")));
	}
	
	@Test
	public void shouldRememberVariableValue() throws Exception {
		String content = "{\"code\": \"%python a=1\"}";
		this.mockMvc.perform(
					post("/execute")
					.contentType(MediaType.APPLICATION_JSON)
					.content(content))
					.andDo(print())
					.andExpect(status().isOk());
		
		
		content = "{\"code\": \"%python print(a)\"}";
		this.mockMvc.perform(
					post("/execute")
					.contentType(MediaType.APPLICATION_JSON)
					.content(content))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("1")));
	}
	
	
	@Test
	public void shouldReturnUnsupportedError() throws Exception {
		final String content = "{\"code\": \"%js print('Hello')\"}";
		this.mockMvc.perform(
					post("/execute")
					.contentType(MediaType.APPLICATION_JSON)
					.content(content))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("not supported for the moment")));
	}
	
	@Test
	public void shouldReturnSyntaxError() throws Exception {
		final String content = "{\"code\": \"%python print(Hello')\"}";
		this.mockMvc.perform(
					post("/execute")
					.contentType(MediaType.APPLICATION_JSON)
					.content(content))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("invalid syntax")));
	}

}
