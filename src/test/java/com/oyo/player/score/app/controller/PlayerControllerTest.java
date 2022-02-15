package com.oyo.player.score.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oyo.player.score.app.PlayerApplication;
import com.oyo.player.score.app.model.RestResponse;
import com.oyo.player.score.app.model.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;

@SpringBootTest(classes = PlayerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PlayerControllerTest {

    private static final Long scoreId = 1L;
    private static final String PLAYER = "ashok";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetScoreHttpStatus() {
        RestResponse response = restTemplate.getForObject(createURL("score?scoreId=" + scoreId), RestResponse.class);
        Assertions.assertTrue(response.getStatus().is2xxSuccessful());
    }

    @Test
    public void testGetScorePlayer() {
        RestResponse response = restTemplate.getForObject(createURL("score?scoreId=" + scoreId), RestResponse.class);
        String expected = "ashok";
        LinkedHashMap<String, String> scoreResponse = (LinkedHashMap) response.getResult();
        Assertions.assertEquals(expected, scoreResponse.get("player"));
    }

    @Test
    public void testGetPlayerHistory() {
        RestResponse response = restTemplate.getForObject(createURL("history?player=" + PLAYER), RestResponse.class);
        Assertions.assertTrue(response.getStatus().is2xxSuccessful());
    }

    private String createURL(String uri) {
        return "http://localhost:" + port + "/restapi/v1/player/" + uri;
    }
}
