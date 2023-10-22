package com.sample.wordcounter.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.wordcounter.model.Word;

@Service
public class ValidationService {

	@Autowired
	Translator translator;

	public List<String> validateInput(Word word) {

		List<String> validValues = filterOnlyAlphabet(word);
 /* Assuming there is an external class for translator */
  		return translator.getEnglishWord(validValues);
	}

	public List<String> filterOnlyAlphabet(Word word) {
		if (word.getValueList() == null)
			word.setValueList(new ArrayList<String>());
		if (word.getValue() != null && !word.getValue().isEmpty()) {
			word.getValueList().addAll(Arrays.asList(word.getValue().split("\\s+")));
		}
		return word.getValueList().stream().filter(w -> StringUtils.isAlpha(w)).collect(Collectors.toList());
	}

}
