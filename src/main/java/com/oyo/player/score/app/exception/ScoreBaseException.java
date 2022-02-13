package com.oyo.player.score.app.exception;

public class ScoreBaseException extends RuntimeException {

    public ScoreBaseException() {
        super();
    }

    public ScoreBaseException(String message) {
        super(message);
    }

    public ScoreBaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
