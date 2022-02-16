package com.oyo.player.score.app.validation;

import com.oyo.player.score.app.exception.ScoreBaseException;
import com.oyo.player.score.app.model.AddScoreRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ValidateRequest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void validateAddScoreRequest(AddScoreRequest addScoreRequest) {
        if (addScoreRequest.getScore() <= 0) {
            throw new ScoreBaseException("score should be greater than zero");
        }
        if (!StringUtils.hasText(addScoreRequest.getPlayer())) {
            throw new ScoreBaseException("player name cannot be null or empty");
        }
        if (!StringUtils.hasText(addScoreRequest.getTime())) {
            throw new ScoreBaseException("time cannot be null or empty");
        }
        try {
            LocalDateTime.parse(addScoreRequest.getTime(), FORMATTER_TIME);
        } catch (Exception e) {
            throw new ScoreBaseException("Please provide date in yyyy-MM-dd HH:mm:ss format");
        }
    }

    public void validateDateFormat(String date) {
        try {
            if (date != null) {
                if (date.contains(":")) {
                    LocalDateTime.parse(date, FORMATTER_TIME);
                } else {
                    LocalDate.parse(date, FORMATTER);
                }
            }
        } catch (Exception e) {
            throw new ScoreBaseException("Please provide date in yyyy-MM-dd or yyyy-MM-dd HH:mm:ss format");
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
