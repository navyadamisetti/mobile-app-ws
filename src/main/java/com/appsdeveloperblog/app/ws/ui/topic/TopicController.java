package com.appsdeveloperblog.app.ws.ui.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping(path = "/topics", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}

	@GetMapping(path = "/topics/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}

	@PostMapping(path = "/topics", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
		
	}

	@PutMapping(path = "/topics/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(id, topic);
	}
	
	@DeleteMapping(path = "/topics/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
}
