package com.appsdeveloperblog.app.ws.ui.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@GetMapping(path = "/topics", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Topic> getAllTopics() {
		return Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"),
				new Topic("java", "CORE JAVA", "CORE JAVA Description"),
				new Topic("javascript", "JavaScript", "JavaScript Description"));

	}

}
