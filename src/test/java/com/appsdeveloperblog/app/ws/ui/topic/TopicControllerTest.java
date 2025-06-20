package com.appsdeveloperblog.app.ws.ui.topic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD) This is by default we don't have to mention it
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) but default can be changed with PER_CLASS, and @BeforeAll can be non static
@DisplayName("Test class for Topic Controller")
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
		// org.junit.platform.commons.JUnitException: @BeforeAll method 'void
		// com.appsdeveloperblog.app.ws.ui.topic.TopicControllerTest.beforeAllInit()'
		// must be static unless the test class is annotated with
		// @TestInstance(Lifecycle.PER_CLASS).
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
	@DisplayName("Testing All Topics get")
	@EnabledOnOs(OS.WINDOWS)
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

	@Test
	@Disabled
	@DisplayName("TDD method. Should not run")
	@Tag("JUnit5")
	void testDisabled() {
		fail("This test should be failed");
	}

	@Test
	@DisplayName("Test example of assumptions")
	@Tag("JUnit5")
	void testServer() {
		boolean isServerUp = true;
		assumeTrue(isServerUp);

	}

	@Test
	@DisplayName("Test example of assertAll")
	@Tag("JUnit5")
	void testSomething() {
		assertAll(
				() -> assertTrue(true), 
				() -> assertFalse(false), 
				() -> assertNotEquals(0, 1));
	}

	@Nested
	@DisplayName("Group of X Tests")
	@Tag("JUnit5")
	class groupingOfTests {
		
		@Test
		@DisplayName("Test X 1")
		void testX1() {
			assertTrue(true);

		}
		
		@Test
		@DisplayName("Test X 2")
		void testX2() {
			assertFalse(false);

		}

	}
	
	@RepeatedTest(5)
	@DisplayName("Test example of Repeated test")
	@Tag("JUnit5")
	void testingRepeatedTest(RepetitionInfo repetitionInfo) {
		repetitionInfo.getCurrentRepetition(); //this object is initialized using dependency injection by junit5
		assertFalse(false);

	}

}
