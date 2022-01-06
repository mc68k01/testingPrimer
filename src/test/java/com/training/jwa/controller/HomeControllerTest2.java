package com.training.jwa.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URL;

import static org.assertj.core.api.Fail.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest2 {

    @LocalServerPort
    private String port;
    private String baseURL = "http://localhost";
    private URL url;

    @Autowired
    private RestTemplate restTemplate; // must be annotated or will create null pointer exception

    @BeforeEach
    void setup() throws Exception {
        url = new URL(baseURL+":"+port+"/home");
    }

    @Test
    @DisplayName("Testing home REST API")
    void testHome() {
        ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
        String actual = response.getBody();
        String expected = "--Hello and Warm Welcome to JWA PRIMER Session-- Revature";
        assertEquals(expected, actual);
        //        Assertions.fail("Not yet implemented");
    }
}
