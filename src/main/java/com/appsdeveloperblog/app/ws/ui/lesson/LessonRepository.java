package com.appsdeveloperblog.app.ws.ui.lesson;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, String> {
	//generic types should be entity class type, id type
	
//	These methods, without implementation, done by Spring data JPA, takes "findByProperty" convention to define method
//	public List<Course> findByName(String name);
//	public List<Course> findByDescription(String description);
	
	public List<Lesson> findByCourseId(String courseId);
	
	

}
