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
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "solution_hash")
    private String solutionHash;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modele_id")
    private ChallengeModule module;

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

    public ChallengeModule getModule(){
        return module;
    }

    public void setModule(ChallengeModule module){
        this.module = module;
    }
}
