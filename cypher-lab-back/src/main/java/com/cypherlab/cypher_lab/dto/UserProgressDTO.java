package com.cypherlab.cypher_lab.dto;

import java.time.LocalDateTime;

public class UserProgressDTO {
    private Long id;
    private Long challengeId;
    private String challengeTitle;
    private String challengeDifficulty;
    private Boolean solved;
    private Integer attempts;
    private Integer pointsEarned;
    private LocalDateTime solvedAt;
    private LocalDateTime lastAttemptAt;

    public UserProgressDTO() {}

    public UserProgressDTO(Long id, Long challengeId, String challengeTitle, 
                          String challengeDifficulty, Boolean solved, Integer attempts,
                          Integer pointsEarned, LocalDateTime solvedAt, LocalDateTime lastAttemptAt) {
        this.id = id;
        this.challengeId = challengeId;
        this.challengeTitle = challengeTitle;
        this.challengeDifficulty = challengeDifficulty;
        this.solved = solved;
        this.attempts = attempts;
        this.pointsEarned = pointsEarned;
        this.solvedAt = solvedAt;
        this.lastAttemptAt = lastAttemptAt;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public void setChallengeTitle(String challengeTitle) {
        this.challengeTitle = challengeTitle;
    }

    public String getChallengeDifficulty() {
        return challengeDifficulty;
    }

    public void setChallengeDifficulty(String challengeDifficulty) {
        this.challengeDifficulty = challengeDifficulty;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Integer getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(Integer pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public LocalDateTime getSolvedAt() {
        return solvedAt;
    }

    public void setSolvedAt(LocalDateTime solvedAt) {
        this.solvedAt = solvedAt;
    }

    public LocalDateTime getLastAttemptAt() {
        return lastAttemptAt;
    }

    public void setLastAttemptAt(LocalDateTime lastAttemptAt) {
        this.lastAttemptAt = lastAttemptAt;
    }
}