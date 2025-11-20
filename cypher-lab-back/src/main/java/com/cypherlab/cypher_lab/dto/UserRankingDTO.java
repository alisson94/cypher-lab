package com.cypherlab.cypher_lab.dto;

public class UserRankingDTO {
    private Long id;
    private String email;
    private Integer pontos;
    private Integer posicao;

    public UserRankingDTO(Long id, String email, Integer pontos, Integer posicao) {
        this.id = id;
        this.email = email;
        this.pontos = pontos;
        this.posicao = posicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }
}
