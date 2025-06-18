package com.appsdeveloperblog.app.ws.ui.topic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD) This is by default we don't have to mention it
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) but default can be changed with PER_CLASS, and @BeforeAll can be non static
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TopicRepository topicRepository;
    
    @BeforeAll
    static void beforeAllInit() {
    	//org.junit.platform.commons.JUnitException: @BeforeAll method 'void com.appsdeveloperblog.app.ws.ui.topic.TopicControllerTest.beforeAllInit()' 
    	//must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
    	System.out.println("This need to run before all tests. ");
    }
    
    @AfterAll
    static void afterAllInit() {
    	System.out.println("This need to run after all tests. ");
    }

    @BeforeEach
    void setUp() {
    	// this method runs before each test method run
    	System.out.println("This need to run before each test. ");
        topicRepository.deleteAll();
        topicRepository.save(new Topic("javaee", "Enterprise Java", "Enterprise Java Description"));
        topicRepository.save(new Topic("java", "Java", "Java Description"));
        topicRepository.save(new Topic("javascript", "JavaScript", "JavaScript Description"));
    }
    
    @AfterEach
    void cleanUp() {
    	// this method runs after each test method run
    	System.out.println("Cleaning Up...");
    }

    @Test
    void testGetAllTopics() {
        String url = "http://localhost:" + port + "/topics";

        ResponseEntity<Topic[]> response = restTemplate.getForEntity(url, Topic[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getBody()).hasSize(3);

        // Validate the first item
        assertThat(response.getBody()[0].getId()).isIn("javaee", "java", "javascript");
        assertThat(response.getBody()[0].getName()).isNotEmpty();
        assertThat(response.getBody()[0].getDescription()).isNotEmpty();
    }
}
