package com.appsdeveloperblog.app.ws.ui.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;

	@GetMapping(path = "/topics", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}

}
