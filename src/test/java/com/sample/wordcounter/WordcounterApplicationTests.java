
package com.sample.wordcounter;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.wordcounter.model.Word;

@SpringBootTest(classes = WordcounterApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WordcounterApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test

	public void testWordCountermocksuccess() throws JsonProcessingException, Exception {
		Word word = new Word("Edinburgh Cambridge", Arrays.asList("London", "Glasgow"));

		mockMvc.perform(MockMvcRequestBuilders.post("/addword").content(objectMapper.writeValueAsString(word))
				.contentType("application/json").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test

	public void testWordCountermockpartial() throws JsonProcessingException, Exception {
		Word word = new Word("Edin1burgh Cambridge", Arrays.asList("London", "Glasgow"));
		mockMvc.perform(MockMvcRequestBuilders.post("/addword").content(objectMapper.writeValueAsString(word))
				.contentType("application/json").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isPartialContent());

	}
	
	@Test

	public void testWordCountermockfailure() throws JsonProcessingException, Exception {
		Word word = new Word("Edin1burgh Camb677ridge", Arrays.asList("Lo7777ndon", "Gla777sgow"));
		mockMvc.perform(MockMvcRequestBuilders.post("/addword").content(objectMapper.writeValueAsString(word))
				.contentType("application/json").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}


	@Test

	public void searchwordnotfoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/word/newword").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$").value(0));
	}
	
	@Test

	public void searchwordfoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/word/London").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$").value(2));
	}

}
