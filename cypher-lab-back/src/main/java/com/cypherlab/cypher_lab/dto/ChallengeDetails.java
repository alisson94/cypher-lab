package com.cypherlab.cypher_lab.dto;

public class ChallengeDetails {
    private Long id;
    private String title;
    private String description;
    private String difficulty;
    private String category;
    private Integer reward;

    public ChallengeDetails(Long id, String title, String description, String difficulty, String category, Integer reward) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.category = category;
        this.reward = reward;
    }
    
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

}
