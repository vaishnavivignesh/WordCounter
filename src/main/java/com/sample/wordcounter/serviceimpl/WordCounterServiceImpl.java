package com.sample.wordcounter.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.wordcounter.model.Word;
import com.sample.wordcounter.service.WordCounterService;
import com.sample.wordcounter.util.Constants;
import com.sample.wordcounter.util.ValidationService;

@Service
public class WordCounterServiceImpl implements WordCounterService {

	@Autowired
	ValidationService validationService;

	private volatile List<String> wordList = new ArrayList<String>();

	@Override
	public String addWord(Word word) {
        
		List<String> validValues = validationService.validateInput(word);

		if (validValues == null || validValues.size() < 1)
			return Constants.failure;
		/* Thread safety */
		synchronized (WordCounterServiceImpl.class) {
			wordList.addAll(validValues);
		}
		/* Incase any word is not valid */
		if (validValues.size() < word.getValueList().size())
			return Constants.partialSuccess;

		return Constants.success;

	}

	@Override
	public synchronized int countWord(String search) {
		try {
			Map<String, Long> mapOfNames = wordList.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			return mapOfNames.entrySet().stream().filter(word -> word.getKey().equalsIgnoreCase(search)).findAny().get()
					.getValue().intValue();
		} catch (Exception e) {
			return 0;
		}
	}

}
