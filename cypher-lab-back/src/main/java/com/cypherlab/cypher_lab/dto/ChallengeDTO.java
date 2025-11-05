package com.cypherlab.cypher_lab.dto;

public class ChallengeDTO {
    private String title;
    private String description;
    private String difficulty;
    private String solutionHash;
    private Integer reward;
    private Long categoryId; 

    public ChallengeDTO() {}

    public ChallengeDTO(String title, String description, String difficulty, String solutionHash, Integer reward, Long categoryId) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.solutionHash = solutionHash;
        this.reward = reward;
        this.categoryId = categoryId;
    }

    // Getters e Setters
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

    public String getSolutionHash() {
        return solutionHash;
    }

    public void setSolutionHash(String solutionHash) {
        this.solutionHash = solutionHash;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
