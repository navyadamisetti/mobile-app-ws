package com.appsdeveloperblog.app.ws.ui.topic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        topicRepository.deleteAll();

        topicRepository.save(new Topic("javaee", "Enterprise Java", "Enterprise Java Description"));
        topicRepository.save(new Topic("java", "Java", "Java Description"));
        topicRepository.save(new Topic("javascript", "JavaScript", "JavaScript Description"));
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
