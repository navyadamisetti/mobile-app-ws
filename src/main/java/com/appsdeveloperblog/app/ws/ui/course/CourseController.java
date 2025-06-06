package com.appsdeveloperblog.app.ws.ui.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.ui.topic.Topic;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping(path = "/topics/{id}/courses", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Course> getAllCourses(@PathVariable String id) {
		return courseService.getAllCourses(id);
	}

	@GetMapping(path = "/topics/{topicId}/courses/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Course getCourse(@PathVariable String id) {
		return courseService.getCourse(id);
	}

	@PostMapping(path = "/topics/{topicId}/courses", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addCourse(@RequestBody Course course, @PathVariable String topicId) {
		course.setTopic(new Topic(topicId, "", ""));
		courseService.addCourse(course);
		
	}

	@PutMapping(path = "/topics/{topicId}/courses/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateCourse(@RequestBody Course course, @PathVariable String topicId, @PathVariable String id) {
		course.setTopic(new Topic(topicId, "", ""));
		courseService.updateCourse(course);
	}
	
	@DeleteMapping(path = "/topics/{topicId}/courses/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteCourse(@PathVariable String id) {
		courseService.deleteCourse(id);
	}
}
