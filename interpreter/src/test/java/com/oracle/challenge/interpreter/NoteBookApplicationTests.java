package com.oracle.challenge.interpreter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.oracle.challenge.interpreter.api.NoteBookControllerApi;

@SpringBootTest
@AutoConfigureMockMvc
class NoteBookApplicationTests {
	

	@Autowired
	private NoteBookControllerApi controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
