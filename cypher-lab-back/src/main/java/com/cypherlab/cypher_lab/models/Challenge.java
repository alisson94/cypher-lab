package com.cypherlab.cypher_lab.models;

public class Challenge {
    private long id;
    
    private String title;
    private String description;
    private String difficulty;
    private String category;
    private String solutionHash;
    
    public Challenge() {}
    
    public Challenge(String title, String description, String difficulty, String category, String solutionHash) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.category = category;
        this.solutionHash = solutionHash;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
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
    
    public String getSolutionHash() {
        return solutionHash;
    }
    
    public void setSolutionHash(String solutionHash) {
        this.solutionHash = solutionHash;
    }
}
