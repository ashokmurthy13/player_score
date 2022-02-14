package com.oyo.player.score.app.model;

import java.util.List;

public class PageResult {

    private List<ScoreResponse> scores;
    private long pageNumber;
    private long pageSize;
    private long totalElements;

    public PageResult(List<ScoreResponse> scores, long pageNumber, long pageSize, long totalElements) {
        this.scores = scores;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    public List<ScoreResponse> getScores() {
        return scores;
    }

    public void setScores(List<ScoreResponse> scores) {
        this.scores = scores;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
