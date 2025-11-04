
package com.cypherlab.cypher_lab.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge_modules")
public class ChallengeModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Challenge> listChallenges = new ArrayList<>();

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Challenge> getListChallenges() { return listChallenges; }
    
    public void setListChallenges(List<Challenge> listChallenges) {
        this.listChallenges.clear();
        if (listChallenges != null) listChallenges.forEach(this::addChallenge);
    }

    //metodos helpers pra manter almbos lados da relação sincronizados
    public void addChallenge(Challenge challenge) {
        if (challenge == null) return;
        if (!this.listChallenges.contains(challenge)) {
            this.listChallenges.add(challenge);
            challenge.setCategory(this);
        }
    }

    public void removeChallenge(Challenge c){
        listChallenges.remove(c);
        c.setCategory(null);
    }

    
}