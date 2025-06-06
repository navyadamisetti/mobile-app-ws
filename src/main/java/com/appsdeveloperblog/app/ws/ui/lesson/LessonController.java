package com.appsdeveloperblog.app.ws.ui.lesson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.ui.course.Course;
import com.appsdeveloperblog.app.ws.ui.topic.Topic;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LessonController {

	@Autowired
	private LessonService lessonService;

	@GetMapping(path = "/topics/{topicId}/courses/{courseId}/lessons", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Lesson> getAllLessons(@PathVariable String courseId) {
		return lessonService.getAllLessons(courseId);
	}

	@GetMapping(path = "/topics/{topicId}/courses/{courseId}/lessons/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Lesson getLesson(@PathVariable String id) {
		return lessonService.getLesson(id);
	}

	@PostMapping(path = "/topics/{topicId}/courses/{courseId}/lessons", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addLesson(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId) {
		lesson.setCourse(new Course(courseId, "", "", topicId));
		lessonService.addLesson(lesson);
		
	}

	@PutMapping(path = "/topics/{topicId}/courses/{courseId}/lessons/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateLesson(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId, @PathVariable String id) {
		lesson.setCourse(new Course(courseId, "", "", topicId));
		lessonService.updateLesson(lesson);
	}
	
	@DeleteMapping(path = "/topics/{topicId}/courses/{courseId}/lessons/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteLesson(@PathVariable String id) {
		lessonService.deleteLesson(id);
	}
}
