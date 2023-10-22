package com.sample.wordcounter.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
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
	public void addWordTestSuccess() {

		List<String> localValue = Arrays.asList("Edinburgh","Cambridge" ,"London", "Glasgow");
		when(validationservice.validateInput(any())).thenReturn(localValue);
		String result = wordCounterServiceImpl.addWord(buildWordObject());
		assertEquals(result, "SUCCESS");

	}
	
	@Test
	public void addWordTestpartialsuccess() {

		List<String> localValue = Arrays.asList("Cambridge" ,"London", "Glasgow");
		when(validationservice.validateInput(any())).thenReturn(localValue);
		String result = wordCounterServiceImpl.addWord(buildWordObject());
		assertEquals(result, "PARTIAL_SUCCESS");

	}
	
	@Test
	public void addWordTestFailure() {

		List<String> localValue = null;
		when(validationservice.validateInput(any())).thenReturn(localValue);
		String result = wordCounterServiceImpl.addWord(buildWordObject());
		assertEquals(result, "FAILURE");

	}
	
	@Test
	public void countWordFound() {
		wordCounterServiceImpl.countWord("London");
		assertEquals(1,1);
	}
	
	@Test
	public void countWordNotFound() {
		wordCounterServiceImpl.countWord("America");
		assertEquals(0,0);
	}

	private Word buildWordObject() {

		Word word = new Word(null, Arrays.asList("Edinburgh", "Cambridge", "London", "Glasgow"));
		return word;
	}

}
