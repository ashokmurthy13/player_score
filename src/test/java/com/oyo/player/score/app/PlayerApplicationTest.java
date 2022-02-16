package com.oyo.player.score.app;

import com.oyo.player.score.app.controller.PlayerController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = PlayerApplication.class)
@ExtendWith(SpringExtension.class)
public class PlayerApplicationTest {

    @Autowired
    private PlayerController playerController;

    @Test
    public void contextLoads() {
        Assertions.assertThat(playerController).isNotNull();
    }
}
