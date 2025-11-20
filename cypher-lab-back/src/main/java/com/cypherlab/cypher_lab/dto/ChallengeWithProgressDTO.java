package com.cypherlab.cypher_lab.dto;

import java.time.LocalDateTime;

public class ChallengeWithProgressDTO {
    private Long id;
    private String title;
    private String description;
    private String difficulty;
    private String category;
    private Integer reward;
    
    // Informações de progresso
    private Boolean isSolved;
    private Integer attempts;
    private Integer pointsEarned;
    private LocalDateTime solvedAt;
    private LocalDateTime lastAttemptAt;

    public ChallengeWithProgressDTO(Long id, String title, String description, String difficulty, 
                                   String category, Integer reward, Boolean isSolved, Integer attempts, 
                                   Integer pointsEarned, LocalDateTime solvedAt, LocalDateTime lastAttemptAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.category = category;
        this.reward = reward;
        this.isSolved = isSolved;
        this.attempts = attempts;
        this.pointsEarned = pointsEarned;
        this.solvedAt = solvedAt;
        this.lastAttemptAt = lastAttemptAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Boolean isSolved) {
        this.isSolved = isSolved;
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
