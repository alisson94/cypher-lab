package com.cypherlab.cypher_lab.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer pontos;

    //@Column(nullable = false, columnDefinition = "ARRAYLIST<Challenge> DEFAULT TRUE")
    //private ArrayList<Challenge> resolvedChallenges = new ArrayList<>();

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public Integer getPontos() {
        return pontos;
    }
    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}