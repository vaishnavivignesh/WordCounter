package com.sample.wordcounter.service;

import com.sample.wordcounter.model.Word;

public interface WordCounterService {

	public String addWord(Word word);

	public int countWord(String search);

}
