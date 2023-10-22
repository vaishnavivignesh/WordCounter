package com.sample.wordcounter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.wordcounter.model.Word;

@SpringBootTest
public class ValidatorServiceTest {

	@InjectMocks
	ValidationService validationService;

	@Mock
	Translator translator;

	String success = "SUCCESS_LIST";
	String partialSuccessList = "PARTIAL_SUCCESS_LIST";
	String partialSuccessValue = "PARTIAL_SUCCESS_VALUE";
	String failure = "FAILURE";

	@Test
	public void successValidateInput() {
		Word word = buildWordObject(success);
		when(translator.getEnglishWord(any())).thenReturn(Arrays.asList("London", "Glasgow", "Edinburgh", "Cambridge"));
		List<String> result = validationService.validateInput(word);
		assertEquals(result.size(), 4);
	}

	@Test
	public void failValidateInput() {
		Word word = buildWordObject(failure);
		when(translator.getEnglishWord(anyList())).thenReturn(new ArrayList<String>());
		List<String> result = validationService.validateInput(word);
		assertEquals(result.size(), 0);
	}

	@Test
	public void partialSuccessListuccessValidateInput() {
		Word word = buildWordObject(partialSuccessList);
		when(translator.getEnglishWord(anyList())).thenReturn(Arrays.asList("Edinburgh", "Cambridge"));
		List<String> result = validationService.validateInput(word);
		assertEquals(result.size(), 2);
	}

	@Test
	public void partialSuccessValueValidateInput() {
		Word word = buildWordObject(partialSuccessValue);
		when(translator.getEnglishWord(anyList())).thenReturn(Arrays.asList("London", "Glasgow"));
		List<String> result = validationService.validateInput(word);
		assertEquals(result.size(), 2);
	}


	private Word buildWordObject(String value) {

		Word word = new Word(null, Arrays.asList("Edinburgh", "Cambridge", "London", "Glasgow"));
		return word;
	}

}
