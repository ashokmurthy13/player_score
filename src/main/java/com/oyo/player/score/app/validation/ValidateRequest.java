package com.oyo.player.score.app.validation;

import com.oyo.player.score.app.exception.ScoreBaseException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ValidateRequest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void checkScore(Integer score) {
        if (score <= 0) {
            throw new ScoreBaseException("score should be greater than zero");
        }
    }

    public void validateDate(String beforeDate, String afterDate) {
        LocalDate before;
        LocalDate after;
        LocalDateTime beforeDateTime;
        LocalDateTime afterDateTime;
        if (beforeDate.contains(":") || afterDate.contains(":")) {
            beforeDateTime = LocalDateTime.parse(beforeDate, FORMATTER_TIME);
            afterDateTime = LocalDateTime.parse(afterDate, FORMATTER_TIME);
            if (beforeDateTime.isBefore(afterDateTime)) {
                throw new ScoreBaseException("afterDate should be less than beforeDate");
            }
        } else {
            before = LocalDate.parse(beforeDate, FORMATTER);
            after = LocalDate.parse(afterDate, FORMATTER);
            if (before.isBefore(after)) {
                throw new ScoreBaseException("afterDate should be less than beforeDate");
            }
        }
    }
}
