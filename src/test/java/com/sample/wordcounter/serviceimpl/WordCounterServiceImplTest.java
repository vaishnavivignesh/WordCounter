package com.sample.wordcounter.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.wordcounter.model.Word;
import com.sample.wordcounter.util.ValidationService;

@SpringBootTest
public class WordCounterServiceImplTest {

	@InjectMocks
	WordCounterServiceImpl wordCounterServiceImpl;

	@Mock
	ValidationService validationservice;

	@Test
	public void addWordTest() {

		List<String> localValue = Arrays.asList("London", "Glasgow");
		when(validationservice.validateInput(any())).thenReturn(localValue);
		String result = wordCounterServiceImpl.addWord(buildWordObject());
		assertEquals(result, "SUCCESS");

	}

	private Word buildWordObject() {

		Word word = new Word("Edinburgh Cambridge", Arrays.asList("London", "Glasgow"));
		return word;
	}

	/*
	 * @BeforeEach public void setup() {
	 * validationservice.validateInput(buildWordObject());
	 * 
	 * }
	 */

}
