package com.appsdeveloperblog.app.ws.ui.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	public CourseRepository courseRepository;

//	private List<Course> topics = new ArrayList<>(
//			Arrays.asList(new Course("spring", "Spring Framework", "Spring Framework Description"),
//					new Course("java", "CORE JAVA", "CORE JAVA Description"),
//					new Course("javascript", "JavaScript", "JavaScript Description")));

	public List<Course> getAllCourses(String topicId) {
//		return topics;
		List<Course> courses = new ArrayList<>();
		courseRepository.findByTopicId(topicId).forEach(courses::add);
		return courses;
	}

	public Course getCourse (String id) {
		return courseRepository.findById(id).get();
	}

	public void addCourse(Course course) {
		courseRepository.save(course);

	}

	public void updateCourse(Course course) {
		courseRepository.save(course);

	}

	public void deleteCourse(String id) {
		courseRepository.deleteById(id);
	}

}
