### Mobile application WebService
##### A RESTful Web Service application with Spring Framework

Resources: Topic, Course, Lesson

##### Topics:
	GET		/topics		Get all Topics
	GET		/topics/id	Get the Topic
	POST		/topics		Create new Topic
	PUT		/topics/id	Updates the Topic
	DELETE		/topics/id	Deletes the Topic

##### Courses:

	GET		/topics/topicId/courses		Get all Courses for the given topic
	GET		/topics/topicId/courses/id	Get the Course of the given topic
	POST		/topics/topicId/courses		Create a new Course for the given topic
	PUT		/topics/topicId/courses/id	Updates the Course of the given topic
	DELETE		/topics/topicId/courses/id	Deletes the Course of the given topic
	
##### Lessons:
	GET		/topics/topicId/courses/courseId/lessons	Get all Lessons of the given topic's course
	GET		/topics/topicId/courses/courseId/lessons/id	Get the Lesson of the given topic's course
	POST		/topics/topicId/courses/courseId/lessons	Create new Lesson for the given topic's course
	PUT		/topics/topicId/courses/courseId/lessons/id	Updates the Lesson of the given topic's course
	DELETE		/topics/topicId/courses/courseId/lessons/id	Deletes the Lesson of the given topic's course


####Tests
	testGetAllTopics
	
