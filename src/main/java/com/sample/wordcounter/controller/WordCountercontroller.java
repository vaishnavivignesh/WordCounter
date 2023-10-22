package com.sample.wordcounter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.wordcounter.model.Response;
import com.sample.wordcounter.model.Word;
import com.sample.wordcounter.service.WordCounterService;
import com.sample.wordcounter.util.Constants;

@RestController
public class WordCountercontroller {

	@Autowired
	WordCounterService wordCounterService;

	@PostMapping("/word")
	public ResponseEntity<Response> addWord(@RequestBody Word word) {

		String status = wordCounterService.addWord(word);

		if (status.equalsIgnoreCase(Constants.failure))
			return new ResponseEntity<>(new Response(status), HttpStatus.BAD_REQUEST);

		if (status.equalsIgnoreCase(Constants.partialSuccess))
			return new ResponseEntity<>(new Response(status), HttpStatus.PARTIAL_CONTENT);

		return new ResponseEntity<>(new Response(status), HttpStatus.OK);
	}

	@GetMapping("/word/{search}")
	public ResponseEntity<Integer> getWordCount(@PathVariable String search) {
		return new ResponseEntity<>(wordCounterService.countWord(search), HttpStatus.OK);
	}
}
