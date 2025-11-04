package com.cypherlab.cypher_lab.models;

import jakarta.persistence.*;

@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "difficulty")
    private String difficulty;
    
    @Column(name = "solution_hash")
    private String solutionHash;

    @Column(name = "reward")
    private Integer reward;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modele_id")
    private ChallengeModule category;

    public Challenge() {}
    

    public Challenge(String title, String description, String difficulty, ChallengeModule category, String solutionHash, Integer reward) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.category = category;
        this.solutionHash = solutionHash;
        this.reward = reward;
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

    public ChallengeModule getCategory(){
        return category;
    }

    public void setCategory(ChallengeModule category){
        this.category = category;
    }
}
