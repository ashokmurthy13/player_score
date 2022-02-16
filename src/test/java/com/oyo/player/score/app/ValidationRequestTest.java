package com.oyo.player.score.app;

import com.oyo.player.score.app.exception.ScoreBaseException;
import com.oyo.player.score.app.model.AddScoreRequest;
import com.oyo.player.score.app.validation.ValidateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ValidationRequestTest {

    private static final String player = "oyo";
    private static final String time = "2022-02-15";

    ValidateRequest validateRequest = new ValidateRequest();

    @Test
    public void testScoreValidation() {
        AddScoreRequest addScoreRequest = new AddScoreRequest(player, 0, time);
        ScoreBaseException exception = Assertions.assertThrows(ScoreBaseException.class, () -> validateRequest.validateAddScoreRequest(addScoreRequest));
        String expected = "score should be greater than zero";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    public void testNullPlayerValidation() {
        AddScoreRequest addScoreRequest = new AddScoreRequest(null, 10, time);
        ScoreBaseException exception = Assertions.assertThrows(ScoreBaseException.class, () -> validateRequest.validateAddScoreRequest(addScoreRequest));
        String expected = "player name cannot be null or empty";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    public void testDateFormatPlayerValidation() {
        AddScoreRequest addScoreRequest = new AddScoreRequest(player, 10, "02-feb-2022");
        ScoreBaseException exception = Assertions.assertThrows(ScoreBaseException.class, () -> validateRequest.validateAddScoreRequest(addScoreRequest));
        String expected = "Please provide date in yyyy-MM-dd HH:mm:ss format";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    public void testDateValidation() {
        String afterDate = "2022-02-14";
        String beforeDate = "2022-02-10";
        ScoreBaseException exception = Assertions.assertThrows(ScoreBaseException.class, () -> validateRequest.validateDate(beforeDate, afterDate));
        String expected = "afterDate should be less than beforeDate";
        Assertions.assertEquals(expected, exception.getMessage());
    }

}
