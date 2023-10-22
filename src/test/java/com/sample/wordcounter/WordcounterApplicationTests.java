
package com.sample.wordcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.sample.wordcounter.model.Response;
import com.sample.wordcounter.model.Word;

@SpringBootTest(classes = WordcounterApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class WordcounterApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void successTestWordCounter() {
		Word word = new Word("Edinburgh Cambridge", Arrays.asList("London", "Glasgow"));

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

		ResponseEntity<Response> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/word",
				word, Response.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void partialSuccessTestWordCounter() {
		Word word = new Word("Edinburgh Ca1mbridge", Arrays.asList("Lo1ndon", "Glasgow"));

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

		ResponseEntity<Response> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/word",
				word, Response.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void failTestWordCounter() {
		Word word = new Word("Edin1burgh Cam2bridge", Arrays.asList("Lo3ndon", "Gla4sgow"));

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

		ResponseEntity<Response> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/word",
				word, Response.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}
}
