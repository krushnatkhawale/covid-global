package com.practice.covid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String basePath;

    @BeforeEach
    public void setUp() {
        basePath = "http://localhost:" + port;
    }

    @DisplayName("Happy path - get stats by country")
    @Test
    void getStatsByCountry() {
        final String getUrl = basePath + "/byCountry";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(getUrl, String.class);
        assertEquals(forEntity.getStatusCode(), HttpStatus.OK);
    }
}